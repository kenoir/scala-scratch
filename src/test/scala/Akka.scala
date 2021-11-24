import Fixtures.{Fixture, TestWith, fixture}
import akka.actor.ActorSystem
import akka.stream.Materializer

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

trait Akka {
  def withActorSystem[R]: Fixture[ActorSystem, R] = fixture[ActorSystem, R](
    create = ActorSystem(),
    destroy = system => Await.ready(system.terminate(), 10 seconds)
  )

  private def withMaterializer[R](
                                   actorSystem: ActorSystem): Fixture[Materializer, R] =
    fixture[Materializer, R](
      create = Materializer(actorSystem),
      destroy = _.shutdown()
    )

  def withMaterializer[R](testWith: TestWith[Materializer, R]): R =
    withActorSystem { actorSystem =>
      withMaterializer(actorSystem) { materializer =>
        testWith(materializer)
      }
    }
}