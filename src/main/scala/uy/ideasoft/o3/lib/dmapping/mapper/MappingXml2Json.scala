package uy.ideasoft.o3.lib.dmapping.mapper

import com.fasterxml.jackson.core.JsonGenerator

import javax.xml.stream.XMLStreamReader
import scala.util.matching.Regex

object MappingXml2Json {

  type TransformStep = (String, TriggeredAction, XMLStreamReader, JsonGenerator) => Unit

  sealed trait StreamAction

  case class Emit(onEnter: TransformStep, onExit: TransformStep) extends StreamAction

  object TriggeredAction {
    def execStart(currentPath: String, triggeredAction: TriggeredAction, sr: XMLStreamReader, jsonGenerator: JsonGenerator): Unit = triggeredAction.action match {
      case Emit(onStart, _) => onStart(currentPath, triggeredAction, sr, jsonGenerator)
    }

    def execEnd(currentPath: String, triggeredAction: TriggeredAction, sr: XMLStreamReader, jsonGenerator: JsonGenerator): Unit = triggeredAction.action match {
      case Emit(_, onEnd) => onEnd(currentPath, triggeredAction, sr, jsonGenerator)
    }
  }

  case class TriggeredAction(path: Option[String] = None, rExp: Option[Regex] = None, action: StreamAction, isText: Boolean = true) {

    def fireOn(currentPath: String): Boolean = (path, rExp) match {
      case (Some(p), _) if p == currentPath => true
      case (_, Some(rExp)) if rExp.matches(currentPath) =>
        true
      case _ => false
    }
  }

  def loadAttributes(sr: XMLStreamReader): Map[String, String] = {
    var map: Map[String, String] = Map()
    for (i <- 0 until sr.getAttributeCount) {
      val attr = sr.getAttributeLocalName(i)
      map = map + (attr -> sr.getAttributeValue(i))
    }
    map
  }

  def directElementR(r: Regex): TriggeredAction = {
    TriggeredAction(rExp = Some(r), action = Emit(
      (currentPath, triggeredAction, is, os) => {
        val text = is.getElementText
        os.writeStringField(is.getLocalName, text)
      },
      (currentPath, triggeredAction, is, os) => (),
    ))
  }


}
