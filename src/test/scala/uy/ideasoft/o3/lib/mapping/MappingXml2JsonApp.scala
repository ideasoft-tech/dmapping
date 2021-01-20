package uy.ideasoft.o3.lib.mapping

import uy.ideasoft.o3.lib.dmapping.mapper.MappingXml2Json._
import uy.ideasoft.o3.lib.dmapping.mapper.StreamingMappingUtil.xml2Json


object MappingXml2JsonApp {

  val xmlStr: String =
    s"""
       |
       |<grid dataCount="0" elementCount="9">
       |    <name/>
       |    <label>ljjkdfsdlkjfsl1</label>
       |    <columns>
       |        <col name="data0" label="ID" columnId="ID" member="true"></col>
       |        <col name="data1" label="EXDEFFUNCO" columnId="EXDEFFUNCO" member="true"></col>
       |        <col name="data2" label="EXFUNC" columnId="EXFUNC" member="true"></col>
       |        <col name="data3" label="APERTURA" columnId="APERTURA" member="true"></col>
       |        <col name="data4" label="CONF" columnId="CONF" member="true"></col>
       |        <col name="data5" label="NAPERTURA" columnId="NAPERTURA" member="true"></col>
       |        <col name="data6" label="MESAPERTURA" columnId="MESAPERTURA" member="true"></col>
       |        <col name="data7" label="BAJA" columnId="BAJA" member="true"></col>
       |        <col name="data8" label="MESBAJA" columnId="MESBAJA" member="true"></col>
       |    </columns>
       |    <rows>
       |        <row dimensionName="A1" data="" calculated="C1">
       |            <data0></data0>
       |            <data1>
       |                <FmtValue>107</FmtValue>
       |                <Value>107</Value>
       |            </data1>
       |        </row>
       |        <row dimensionName="A2" data="" calculated="C2">
       |            <data0></data0>
       |            <data1>
       |                <FmtValue>107</FmtValue>
       |                <Value>107</Value>
       |            </data1>
       |            <data2>
       |                <Value>En data2</Value>
       |            </data2>
       |        </row>
       |    </rows>
       |</grid>
       |""".stripMargin


  def createActions: Seq[TriggeredAction] = {

    val a1: TriggeredAction = TriggeredAction(path = Some("/grid"), action = Emit(
      (currentPath, triggeredAction, is, os) => {
        // println("  -> StartObject")
        os.writeStartObject()
        val attrs = loadAttributes(is)
        attrs.get("dataCount").foreach(v => os.writeStringField("dataCount", v))
        attrs.get("elementCount").foreach(v => os.writeStringField("elementCount", v))
      },
      (currentPath, triggeredAction, is, os) => {
        os.writeEndObject()
        // println("  -> EndObject")
        },
    ), isText = false)

    val a2: TriggeredAction = TriggeredAction(path = Some("/grid/name"), action = Emit(
      (currentPath, triggeredAction, is, os) => {
        val text = is.getElementText
        os.writeStringField("name", text)
      },
      (currentPath, triggeredAction, is, os) => (),
    ))

    val a3: TriggeredAction = TriggeredAction(path = Some("/grid/label"), action = Emit(
      (currentPath, triggeredAction, is, os) => {
        val text = is.getElementText
        os.writeStringField("label", text)
      },
      (currentPath, triggeredAction, is, os) => (),
    ))

    val a4: TriggeredAction = TriggeredAction(path = Some("/grid/columns"), action = Emit(
      (currentPath, triggeredAction, is, os) => {
        os.writeFieldName("columns")
        os.writeStartArray()
        // println("  -> StartArray")
      },
      (currentPath, triggeredAction, is, os) => {
        os.writeEndArray()
        // println("  -> EndArray")

      },
    ), isText = false)


    val a5: TriggeredAction = TriggeredAction(path = Some("/grid/columns/col"), action = Emit(
      (currentPath, triggeredAction, is, os) => {
        os.writeStartObject()
        val attrs = loadAttributes(is)
        attrs.foreach({ case (n, v) => os.writeStringField(n, v) })
      },
      (currentPath, triggeredAction, is, os) => {
        os.writeEndObject()
      },
    ), isText = false)



    def directElement(path: String): TriggeredAction = {
      TriggeredAction(Some(path), action = Emit(
        (currentPath, triggeredAction, is, os) => {
          val text = is.getElementText
          os.writeStringField(is.getLocalName, text)
        },
        (currentPath, triggeredAction, is, os) => (),
      ))
    }

    val actions = Seq(a1, a2, a3, a4, a5,

      TriggeredAction(path = Some("/grid/rows"), action = Emit(
        (currentPath, triggeredAction, is, os) => {
          os.writeFieldName("rows")
          os.writeStartArray()
        },
        (currentPath, triggeredAction, is, os) => {
          os.writeEndArray()
        },
      ), isText = false),

      TriggeredAction(path = Some("/grid/rows/row"), action = Emit(
        (currentPath, triggeredAction, is, os) => {
          // println("  -> StartObject")
          os.writeStartObject()
          val attrs = loadAttributes(is)
          attrs.foreach({ case (n, v) => os.writeStringField(n, v) })
        },
        (currentPath, triggeredAction, is, os) => {
          os.writeEndObject()
          // println("  -> EndObject")
        },
      ), isText = false),


      TriggeredAction(path = None, rExp = Some("/grid/rows/row/([a-zA-Z0-9]+)$".r), action = Emit(
        (currentPath, triggeredAction, is, os) => {
          // println("  -> StartObject")
          val pattern = triggeredAction.rExp.get
          val pattern(name) = currentPath
          os.writeFieldName(name)
          os.writeStartObject()
          val attrs = loadAttributes(is)
          attrs.foreach({ case (n, v) => os.writeStringField(n, v) })
        },
        (currentPath, triggeredAction, is, os) => {
          os.writeEndObject()
          // println("  -> EndObject")
        },
      ), isText = false),

      directElementR("/grid/rows/row/[a-zA-Z0-9]+/FmtValue".r),
      directElementR("/grid/rows/row/[a-zA-Z0-9]+/Value".r)

    )

    actions
  }

  def generatedJson: String = xml2Json(createActions, xmlStr)

  def main(args: Array[String]): Unit = {

    val actions = createActions

//    actions.foreach { action =>
//      println(action.path)
//    }

    val json = xml2Json(actions, xmlStr)
    println(s"  -> $json")
  }

}

