package uy.ideasoft.o3.lib.dmapping.mapper

import com.fasterxml.jackson.core.JsonGenerator
import uy.ideasoft.o3.lib.dmapping.mapper.MappingXml2Json.TriggeredAction

import javax.xml.stream.{XMLStreamConstants, XMLStreamReader}
import scala.collection.mutable

object StreamingMappingXml2JsonEngine {

  /**
   * Convierte un stream xml en un stream json, aplicando un conjunto de acciones de transformación
   */
  def xml2json(actions: Seq[TriggeredAction], sReader: XMLStreamReader, jGenerator: JsonGenerator): Unit = {

    val currentPath: mutable.ArrayDeque[String] = mutable.ArrayDeque.empty
    var currentPathStr: String = "/"

    while (sReader.hasNext) {
      sReader.next()
      val t = sReader.getEventType
      t match {
        case XMLStreamConstants.START_ELEMENT =>
          currentPath.append(sReader.getLocalName)
          currentPathStr = "/" + currentPath.mkString("/")
          // println("Start", t, currentPathStr, sReader.getAttributeCount)

          actions.find(_.fireOn(currentPathStr)).foreach(action => {
            // println("matching: ", action)
            TriggeredAction.execStart(currentPathStr, action, sReader, jGenerator)
            if (action.isText) {
              currentPath.removeLast()
              currentPathStr = "/" + currentPath.mkString("/")

            }
          })

        case XMLStreamConstants.END_ELEMENT =>
          actions.find(_.fireOn(currentPathStr)).foreach(action => {
            // println("matching: ", action)
            TriggeredAction.execEnd(currentPathStr, action, sReader, jGenerator)
          })
          // println("End", t, currentPathStr)
          currentPath.removeLast()
          currentPathStr = "/" + currentPath.mkString("/")

        case XMLStreamConstants.CHARACTERS =>
        // println("Characters", t, currentPathStr, sReader.getText)

        case _ =>
        // println("Event", t, currentPathStr)
      }
    }
    sReader.close()
    jGenerator.flush()
  }

}
