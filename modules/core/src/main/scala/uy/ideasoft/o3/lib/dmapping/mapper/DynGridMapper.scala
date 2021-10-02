package uy.ideasoft.o3.lib.dmapping.mapper

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object DynGridMapper {
  val xmlMapper: ObjectMapper = (new XmlMapper())
    .setSerializationInclusion(Include.NON_NULL)
    .registerModule(DefaultScalaModule)
    //.registerModule(module)
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

  //
}
