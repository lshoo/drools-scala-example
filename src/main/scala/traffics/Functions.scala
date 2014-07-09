package traffics

import org.drools.runtime.StatefulKnowledgeSession
import org.drools.runtime.rule.RuleContext

import scala.beans.BeanInfo

/**
 * Created by Administrator on 2014/7/9.
 */
object Functions {

  def insertTrafficResponse(kcontext: RuleContext, traffic: Traffic, action: String): Unit = {
    val session = kcontext.getKnowledgeRuntime.asInstanceOf[StatefulKnowledgeSession]
    session.insert(TrafficResponse(action))

    val ruleName = kcontext.getRule.getName
    val cityLocator = session.getGlobal("cityLocator").asInstanceOf[CityLocator]
    val city = cityLocator.city(traffic)
    println("Rule[%s]: Traffic(%s at %s) => %s".format(ruleName, traffic.light, city, action))
  }

  def insertDrivingStyle(kcontext: RuleContext, drivingStyle: String): Unit = {
    val session = kcontext.getKnowledgeRuntime.asInstanceOf[StatefulKnowledgeSession]
    println("Driving Style: %s".format(drivingStyle))
    session.insert(DrivingStyle(drivingStyle))
  }
}

@BeanInfo
case class Traffic(light: String, cid: Int)

@BeanInfo
case class DrivingStyle(style: String)

@BeanInfo
case class TrafficResponse(action: String)

class CityLocator {
  def city(traffic: Traffic): String = if (traffic.cid == 0) "Boston" else "New York"
}

