package traffics

import org.drools.KnowledgeBaseFactory
import org.drools.builder.{ResourceType, KnowledgeBuilderFactory}
import org.drools.io.ResourceFactory
import org.drools.runtime.StatefulKnowledgeSession

import scala.collection.JavaConversions._

/**
 * Created by Administrator on 2014/7/9.
 */
object TrafficApp {

  val kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder()
  kbuilder.add(ResourceFactory.newClassPathResource("traffic.drl"), ResourceType.DRL)

  if (kbuilder.hasErrors)
    throw new RuntimeException(kbuilder.getErrors.toString)

  val kbase = KnowledgeBaseFactory.newKnowledgeBase()
  kbase.addKnowledgePackages(kbuilder.getKnowledgePackages)

  def testRedInBoston(): TrafficResponse = {
    runTest(Traffic("red", 0))
  }

  def testRedInNewYork(): Unit = {
    runTest(Traffic("red", 1))
  }
  def runTest(traffic: Traffic): TrafficResponse = {
    val session = kbase.newStatefulKnowledgeSession()
    session.setGlobal("cityLocator", new CityLocator())
    session.insert(traffic)
    session.fireAllRules()

    val trafficResponse = getResults(session, "TrafficResponse") match {
      case Some(x) => x.asInstanceOf[TrafficResponse]
      case None => null
    }

    session.dispose()
    trafficResponse
  }

  def getResults(session: StatefulKnowledgeSession, className: String): Option[Any] = {
    val fsess = session.getObjects().filter(o => o.getClass.getName().endsWith(className))
    if (fsess.size > 0) Some(fsess.toList.head)
    else None
  }
  def main(args: Array[String]): Unit = {
     val r1 = testRedInNewYork()
     println(r1)


  }
}
