import akka.stream.scaladsl.{Keep, RunnableGraph}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers._

import scala.concurrent.Future

class ExampleAppTest extends AnyFunSpec with Akka with ScalaFutures {
  import MySinks._
  import MySources._

  it("should run the test") {
    withMaterializer { implicit mat =>
      val runnable: RunnableGraph[Future[Int]] = simpleSource.toMat(productSink)(Keep.right)

      whenReady(runnable.run()) { sum =>
        sum shouldBe 55
      }
    }
  }
}
