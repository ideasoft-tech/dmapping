package uy.ideasoft.o3.lib.dmapping.mapper

import com.fasterxml.jackson.core.{JsonParser, JsonToken}
import uy.ideasoft.o3.lib.dmapping.mapper.MappingJson2Xml.TriggeredAction

import javax.xml.stream.XMLStreamWriter
import scala.collection.mutable

object StreamingMappingJson2XmlEngine {

  case class PathElement(
    jt: JsonToken,
    var name: String,
    var isObject: Boolean = false,
    var isArray: Boolean = false,
    var _isField: Boolean = false,
    var index: Option[Int] = None,
    val sWriter: XMLStreamWriter,
    val reorder: Boolean = true
  ) {

    var bufferedStream: Option[BufferedXMLStreamWriter] = if (reorder) Some(new BufferedXMLStreamWriter()) else None

//    var bufferedStream: Option[BufferedXMLStreamWriter] = Some(new BufferedXMLStreamWriter())

//    var bufferedStream: Option[BufferedXMLStreamWriter] = None

    def writer: XMLStreamWriter = bufferedStream.getOrElse(sWriter)

    def close(): Unit = bufferedStream.foreach(b => BufferedXMLStreamWriter.writeInto(b, sWriter))

    def asString: String =
      if (name == "$") name
      else if (_isField) "." + name
      else if (index.isDefined) s"[${index.get}]"
      else "x"

    def isField: Boolean = _isField && !isObject && !isArray

    def setIsObject(): Unit = isObject = true

    def setIsArray(): Unit = isArray = true
  }

  object PathElement {
    def root(sWriter: XMLStreamWriter) = new PathElement(JsonToken.NOT_AVAILABLE, name = "$", _isField = true, sWriter = sWriter)

    def array(sWriter: XMLStreamWriter) = new PathElement(JsonToken.START_ARRAY, name = "[]", sWriter = sWriter)

    def obj(ndx: Option[Int], sWriter: XMLStreamWriter, reorder: Boolean = false) = new PathElement(JsonToken.START_OBJECT, name = "", index = ndx, sWriter = sWriter, reorder = reorder)

    def field(name: String, sWriter: XMLStreamWriter) = new PathElement(JsonToken.FIELD_NAME, name = name, _isField = true, sWriter = sWriter)
  }

  class ParseContext(jsonParser: JsonParser, sWriter: XMLStreamWriter) {
    val root = PathElement.root(sWriter)
    val deque: mutable.ArrayDeque[PathElement] = mutable.ArrayDeque().append(root)

    def jsonPath: String = deque.map(_.asString).mkString

    def current: PathElement = deque.last
    def currentWriter: XMLStreamWriter = current.writer

    def append(e: PathElement): Unit = {
      deque.append(e)
    }

    def removeLast(): Unit = {
      current.close()
      deque.removeLast()
    }

    def processToken(jToken: JsonToken): Unit = {
      val last = current
      var index: Option[Int] = None
      if (last.isArray) {
        last.index = last.index.map(_ + 1).orElse(Some(0))
        index = last.index
      }
      jToken match {
        case JsonToken.START_OBJECT => last match {
          case last if last.isField => last.setIsObject()
          case _ => append(PathElement.obj(index, currentWriter))
        }
        case JsonToken.END_OBJECT => removeLast()

        case JsonToken.START_ARRAY => last match {
          case last if last._isField => last.setIsArray()
          case _ => append(PathElement.array(currentWriter))
        }
        case JsonToken.END_ARRAY => removeLast()

        case JsonToken.FIELD_NAME => append(PathElement.field(jsonParser.getCurrentName, currentWriter))

        case JsonToken.VALUE_STRING |
             JsonToken.VALUE_TRUE |
             JsonToken.VALUE_FALSE |
             JsonToken.VALUE_NULL |
             JsonToken.VALUE_NUMBER_FLOAT |
             JsonToken.VALUE_NUMBER_INT
        => last match {
          case last if last._isField => removeLast()
          case _ =>
        }

      }
    }
  }

  /**
   * Convierte un stream json en un stream xml, aplicando un conjunto de acciones de transformación
   */
  def json2Xml(actions: Seq[TriggeredAction], jParser: JsonParser, xmlWriter: XMLStreamWriter): Unit = {

    val parseContext = new ParseContext(jParser, xmlWriter)

    def value(t: JsonToken): Unit = {
      // println(t, parseContext.jsonPath)
      actions.find(_.fireOn(parseContext.jsonPath)).foreach(action => {
        TriggeredAction.execStart(parseContext, action, jParser, parseContext.currentWriter)
      })
      parseContext.processToken(t)
    }

    jParser.nextToken()
    while (jParser.currentToken() != null) {
      val t = jParser.currentToken()
      t match {
        case JsonToken.START_OBJECT =>
          parseContext.processToken(t)
          // println(t, parseContext.jsonPath)

          actions.find(_.fireOn(parseContext.jsonPath)).foreach(action => {
            TriggeredAction.execStart(parseContext, action, jParser, parseContext.currentWriter)
          })

        case JsonToken.END_OBJECT =>
          actions.find(_.fireOn(parseContext.jsonPath)).foreach(action => {
            TriggeredAction.execEnd(parseContext, action, jParser, parseContext.currentWriter)
          })
          // println(t, parseContext.jsonPath)
          parseContext.processToken(t)

        case JsonToken.START_ARRAY =>
          parseContext.processToken(t)
          // println(t, parseContext.jsonPath)

          actions.find(_.fireOn(parseContext.jsonPath)).foreach(action => {
            TriggeredAction.execStart(parseContext, action, jParser, parseContext.currentWriter)
          })

        case JsonToken.END_ARRAY =>
          actions.find(_.fireOn(parseContext.jsonPath)).foreach(action => {
            TriggeredAction.execEnd(parseContext, action, jParser, parseContext.currentWriter)
          })

          // println(t, parseContext.jsonPath)
          parseContext.processToken(t)

        case JsonToken.FIELD_NAME =>
          parseContext.processToken(t)
        // println(t, parseContext.jsonPath)

        case JsonToken.VALUE_STRING => value(t)
        case JsonToken.VALUE_FALSE => value(t)
        case JsonToken.VALUE_TRUE => value(t)
        case JsonToken.VALUE_NULL => value(t)
        case JsonToken.VALUE_NUMBER_FLOAT => value(t)
        case JsonToken.VALUE_NUMBER_INT => value(t)

        case _ =>
        // println("Event", t, parseContext.jsonPath)
      }
      jParser.nextToken()
    }
    xmlWriter.flush()
  }
}
