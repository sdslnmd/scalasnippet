package dispatch

import java.util.concurrent.{BlockingQueue, CountDownLatch}

import scala.util.Random

object Dispatcher {

}


class WorkItem(value: String)

class WorkProducer(numItemsToProduce: Int, queue: BlockingQueue[WorkItem], terminationLatch: CountDownLatch) extends Runnable {

  val r = new Random()

  override def run(): Unit = {

    for {_ <- 1 to numItemsToProduce} {

      queue.add(new WorkItem(r.nextString(5)))
    }

    terminationLatch.countDown()

  }
}


