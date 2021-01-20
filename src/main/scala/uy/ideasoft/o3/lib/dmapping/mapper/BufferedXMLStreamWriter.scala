package uy.ideasoft.o3.lib.dmapping.mapper

import javax.xml.namespace.NamespaceContext
import javax.xml.stream.XMLStreamWriter
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class Element(name: String, attributes: mutable.HashMap[String, String] = mutable.HashMap(), var value: Option[String] = None, children: mutable.ListBuffer[Element] = mutable.ListBuffer())

class BufferedXMLStreamWriter extends XMLStreamWriter {

  val fragment: Element = Element("")

  var current: mutable.Stack[Element] = mutable.Stack(fragment)

  private def add(e: Element): Unit = {
    current.top.children.append(e)
    current.push(e)
  }

  override def writeStartElement(localName: String): Unit = add(Element(localName))

  override def writeStartElement(namespaceURI: String, localName: String): Unit = ???

  override def writeStartElement(prefix: String, localName: String, namespaceURI: String): Unit = ???

  override def writeEmptyElement(namespaceURI: String, localName: String): Unit = ???

  override def writeEmptyElement(prefix: String, localName: String, namespaceURI: String): Unit = ???

  override def writeEmptyElement(localName: String): Unit = {
    add(Element(localName))
  }

  override def writeEndElement(): Unit = current.pop()

  override def writeEndDocument(): Unit = {}

  override def close(): Unit = ???

  override def flush(): Unit = ???

  override def writeAttribute(localName: String, value: String): Unit = current.top.attributes.put(localName, value)

  override def writeAttribute(prefix: String, namespaceURI: String, localName: String, value: String): Unit = ???

  override def writeAttribute(namespaceURI: String, localName: String, value: String): Unit = ???

  override def writeNamespace(prefix: String, namespaceURI: String): Unit = ???

  override def writeDefaultNamespace(namespaceURI: String): Unit = ???

  override def writeComment(data: String): Unit = ???

  override def writeProcessingInstruction(target: String): Unit = ???

  override def writeProcessingInstruction(target: String, data: String): Unit = ???

  override def writeCData(data: String): Unit = ???

  override def writeDTD(dtd: String): Unit = ???

  override def writeEntityRef(name: String): Unit = ???

  override def writeStartDocument(): Unit = ???

  override def writeStartDocument(version: String): Unit = ???

  override def writeStartDocument(encoding: String, version: String): Unit = ???

  override def writeCharacters(text: String): Unit = current.top.value = Some(text)

  override def writeCharacters(text: Array[Char], start: Int, len: Int): Unit = ???

  override def getPrefix(uri: String): String = ???

  override def setPrefix(prefix: String, uri: String): Unit = ???

  override def setDefaultNamespace(uri: String): Unit = ???

  override def setNamespaceContext(context: NamespaceContext): Unit = ???

  override def getNamespaceContext: NamespaceContext = ???

  override def getProperty(name: String): AnyRef = ???
}

object BufferedXMLStreamWriter {

  def writeInto(b: BufferedXMLStreamWriter, sWriter: XMLStreamWriter): Unit = {

    def attributesToXmlStream(attrs: mutable.Map[String, String], sWriter: XMLStreamWriter): Unit = {
      attrs.foreach({case (name, value) => sWriter.writeAttribute(name, value)})
    }

    def elementToXmlStream(e: Element,  sWriter: XMLStreamWriter): Unit = {
      if (e.value.isEmpty && e.children.isEmpty) {
        sWriter.writeStartElement(e.name)
        sWriter.writeEndElement()
    } else {
        sWriter.writeStartElement(e.name)
        attributesToXmlStream(e.attributes, sWriter)
        if (e.value.isDefined)
          sWriter.writeCharacters(e.value.get)
        else
          e.children.foreach(elementToXmlStream(_, sWriter))
        sWriter.writeEndElement()
      }
    }

    attributesToXmlStream(b.fragment.attributes, sWriter)
    b.fragment.children.foreach(elementToXmlStream(_, sWriter))
  }

}
