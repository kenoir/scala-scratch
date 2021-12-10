import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext
import scala.io.StdIn


object ExampleRoutes {
  val hello =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "hello"))
      }
    }

  val bye =
    path("bye") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "bye"))
      }
    }
}

object ExampleApp extends App {
  implicit val ec: ExecutionContext =
    AkkaBuilder.buildExecutionContext()

  implicit val actorSystem =
    AkkaBuilder.buildActorSystem()

  val routes = ExampleRoutes.hello ~ ExampleRoutes.bye

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes)

  println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")

  StdIn.readLine() // let it run until user presses return

  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => actorSystem.terminate()) // and shutdown when done
}
