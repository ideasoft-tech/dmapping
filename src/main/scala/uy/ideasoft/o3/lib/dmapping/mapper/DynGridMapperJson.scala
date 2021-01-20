package uy.ideasoft.o3.lib.dmapping.mapper

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

class DynGridMapperJson {
  val jsonMapper: ObjectMapper = (new JsonMapper()).setSerializationInclusion(Include.NON_EMPTY)
    .setSerializationInclusion(Include.NON_NULL)
    .setSerializationInclusion(Include.NON_ABSENT)
    .registerModule(DefaultScalaModule)
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
}
