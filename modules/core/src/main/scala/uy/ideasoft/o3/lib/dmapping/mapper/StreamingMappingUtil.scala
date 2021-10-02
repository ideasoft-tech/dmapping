package uy.ideasoft.o3.lib.dmapping.mapper

import com.fasterxml.jackson.core.{JsonEncoding, JsonFactory, JsonGenerator}
import com.fasterxml.jackson.dataformat.xml.XmlMapper

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import javax.xml.stream.{XMLInputFactory, XMLOutputFactory, XMLStreamReader}

object StreamingMappingUtil {

  def xml2Json(actions: Seq[MappingXml2Json.TriggeredAction], xmlStr: String): String = {

    val xmlMapper = new XmlMapper

    val f: XMLInputFactory = XMLInputFactory.newInstance()
    val sr: XMLStreamReader = f.createXMLStreamReader(new ByteArrayInputStream(xmlStr.getBytes()))

    val jFactory = new JsonFactory
    val generatedStream = new ByteArrayOutputStream()
    val jGenerator: JsonGenerator = jFactory.createGenerator(generatedStream, JsonEncoding.UTF8)

    StreamingMappingXml2JsonEngine.xml2json(actions, sr, jGenerator)
    jGenerator.close()
    generatedStream.toString
  }


  def json2Xml(actions: Seq[MappingJson2Xml.TriggeredAction], json: String): String = {

    import com.fasterxml.jackson.core.JsonParser

    val jFactory = new JsonFactory
    val jParser: JsonParser = jFactory.createParser(json)

    val f: XMLOutputFactory = XMLOutputFactory.newInstance()
    val generatedXml = new ByteArrayOutputStream()
    val sWriter = f.createXMLStreamWriter(generatedXml)

//    println("json = ", json)

    StreamingMappingJson2XmlEngine.json2Xml(actions, jParser, sWriter)

    sWriter.close()
    generatedXml.toString
  }

}
