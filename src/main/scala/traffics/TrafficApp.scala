package traffics

import org.drools.KnowledgeBaseFactory
import org.drools.builder.{ResourceType, KnowledgeBuilderFactory}
import org.drools.io.ResourceFactory

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

  def testRedInBoston(): Unit = {
    val response =
  }
  def main(args: Array[String]): Unit = {

  }
}
