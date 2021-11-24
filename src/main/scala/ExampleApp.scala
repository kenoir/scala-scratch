import akka.NotUsed
import akka.actor.{ActorSystem, Cancellable}
import akka.stream.scaladsl.{Flow, Keep, RunnableGraph, Sink, Source}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.util.Random


object MySources {
  val simpleSource: Source[Int, NotUsed] = Source(1 to 10)
  val tickSource: Source[Int, Cancellable] = Source
    .tick(
      initialDelay = 250.millis, // delay of first tick
      interval = 250.millis, // delay of subsequent ticks
      tick = 1 // element emitted each tick
    )
    .take(10)
}

object MyFlows {
  val doublerFlow = Flow[Int].map(_ * 2)
  val triplerFlow = Flow[Int].map(_ * 3)
}

object MySinks {
  val sumSink = Sink.fold[Int, Int](0)(_ + _)
  val productSink = Sink.fold[Int, Int](1)(_ * _)
  val printSink = Sink.foreach[Int](println)
}


object ExampleApp extends App {
  import MySinks._
  import MyFlows._
  import MySources._

  implicit val ec: ExecutionContext =
    AkkaBuilder.buildExecutionContext()

  implicit val actorSystem: ActorSystem =
    AkkaBuilder.buildActorSystem()

  val doubleThenTripleFlow = doublerFlow.via(triplerFlow)

  val runnable: RunnableGraph[Future[Int]] = tickSource
    .via(doubleThenTripleFlow)
    .map(_ + Random.nextInt())
    .wireTap(printSink)
    .toMat(productSink)(Keep.right)

  // materialize the flow and get the value
  val sum: Future[Int] = runnable.run()

  val result = Await.result(sum, Duration.Inf)

  println(result)
}
