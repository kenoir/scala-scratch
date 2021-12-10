import akka.stream.scaladsl.{Keep, RunnableGraph}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers._


import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._

import scala.concurrent.Future

class ExampleAppTest extends AnyFunSpec with Akka with ScalaFutures with ScalatestRouteTest {

  import ExampleRoutes._

  describe("GET /hello") {
    it("returns hello") {
      Get("/hello") ~> hello ~> check {
        responseAs[String] shouldEqual "hello"
      }
    }
  }
}
