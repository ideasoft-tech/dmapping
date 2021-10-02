package uy.ideasoft.o3.lib.dmapping.mapper

import com.fasterxml.jackson.core.{JsonParser, JsonToken}
import uy.ideasoft.o3.lib.dmapping.mapper.StreamingMappingJson2XmlEngine.ParseContext

import javax.xml.stream.XMLStreamWriter
import scala.util.matching.Regex

object MappingJson2Xml {

  type TransformAtom = (ParseContext, TriggeredAction, JsonParser, XMLStreamWriter) => Unit

  def asElement(r: Regex, name: String, cache: Boolean = false): TriggeredAction =
    TriggeredAction(r, Emit(
      (_, _, _, xmlWriter) => {
        xmlWriter.writeStartElement(name)
      },
      (_, _, _, xmlWriter) => {
        xmlWriter.writeEndElement()
      }), cache = cache
    )

  def asElementValue(r: Regex, name: String): TriggeredAction =
    TriggeredAction(r, Emit(
      (_, _, jsonParser, xmlWriter) => {
        if (jsonParser.currentToken != JsonToken.VALUE_NULL) {
          xmlWriter.writeStartElement(name)
          xmlWriter.writeCharacters(jsonParser.getValueAsString)
          xmlWriter.writeEndElement()
        }
      },
      (currentPath, triggeredAction, jsonParser, xmlWriter) => {
      })
    )

  def asAttribute(r: Regex, name: String): TriggeredAction =
    TriggeredAction(r, Emit(
      (_, _, jsonParser, xmlWriter) => {
        xmlWriter.writeAttribute(name, jsonParser.getValueAsString)
      },
      (_, _, _, _) => {
      })
    )

  sealed trait StreamAction

  case class Emit(onEnter: TransformAtom, onExit: TransformAtom) extends StreamAction

  case class TriggeredAction(rExp: Regex, action: StreamAction, cache: Boolean = false) {

    def fireOn(currentPath: String): Boolean = rExp.matches(currentPath)
  }

  object TriggeredAction {
    def execStart(context: ParseContext, triggeredAction: TriggeredAction, jParser: JsonParser, sWriter: XMLStreamWriter): Unit = triggeredAction.action match {
      case Emit(onStart, _) => onStart(context, triggeredAction, jParser, sWriter)
    }

    def execEnd(context: ParseContext, triggeredAction: TriggeredAction, sr: JsonParser, jsonGenerator: XMLStreamWriter): Unit = triggeredAction.action match {
      case Emit(_, onEnd) => onEnd(context, triggeredAction, sr, jsonGenerator)
    }
  }

}
