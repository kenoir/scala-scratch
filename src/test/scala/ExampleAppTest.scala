import akka.stream.scaladsl.{Keep, RunnableGraph}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._
import akka.http.scaladsl.unmarshalling.Unmarshaller

import scala.concurrent.Future

class ExampleAppTest extends AnyFunSpec with Akka with ScalaFutures with ScalatestRouteTest {
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  import AppRoutes._

  describe("POST /reverse") {
    it("reverses Greeting") {
      val myGreeting = Greeting("hello there")

      Post("/reverse", myGreeting) ~> reverse ~> check {
        status shouldEqual StatusCodes.Created
        contentType shouldEqual ContentTypes.`application/json`

        responseAs[Greeting] shouldEqual Greeting("ereht olleh")
      }
    }
  }

  describe("GET /hello") {
    it("returns hello") {
      Get("/hello") ~> hello ~> check {
        status shouldEqual StatusCodes.OK
        contentType shouldEqual ContentTypes.`application/json`
        responseAs[Greeting] shouldEqual Greeting("hello")
      }
    }

    it("returns bye") {
      // This is required otherwise the Circe implicit tries to unmarshall a string and fails
      implicit val responseBodyUnmarshaller =
        Unmarshaller.strict[HttpResponse, HttpEntity](_.entity)
          .andThen(Unmarshaller.stringUnmarshaller)

      Get("/bye") ~> bye ~> check {
        status shouldEqual StatusCodes.OK
        contentType shouldEqual ContentTypes.`text/html(UTF-8)`

        responseAs[String] shouldEqual "bye"
      }
    }
  }
}
