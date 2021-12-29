import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives.{complete, entity, get, onSuccess, path, post}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Thinger {
  def doThing(greeting: Greeting): Future[Greeting] = Future {
    greeting.copy(bar = greeting.bar.reverse )
  }
}

object AppRoutes {
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._
  import akka.http.scaladsl.server.Directives._

  val hello =
    path("hello") {
      get {
        complete(Greeting("hello"))
      }
    }

  // You can test this by running:
  // curl -X POST http://localhost:8080/reverse -H "Content-Type: application/json" -d '{"bar":"oof"}'
  val reverse =
    path("reverse") {
      post {
        entity(as[Greeting]) { greeting =>
          onSuccess(Thinger.doThing(greeting)) { reversed =>
            complete((StatusCodes.Created, reversed))
          }
        }
      }
    }

  val bye =
    path("bye") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "bye"))
      }
    }
}
