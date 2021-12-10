import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.stream.Materializer

import scala.concurrent.ExecutionContext

object AkkaBuilder {
   implicit val actorSystem: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")

  def buildActorSystem(): ActorSystem[Nothing] =
    actorSystem

  def buildMaterializer(): Materializer =
    Materializer(actorSystem)

  def buildExecutionContext(): ExecutionContext =
    actorSystem.executionContext
}