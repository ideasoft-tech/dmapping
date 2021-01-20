package uy.ideasoft.o3.lib.mapping

import uy.ideasoft.o3.lib.dmapping.mapper.MappingJson2Xml
import uy.ideasoft.o3.lib.dmapping.mapper.MappingJson2Xml._
import uy.ideasoft.o3.lib.dmapping.mapper.StreamingMappingUtil.json2Xml


object MappingJson2XmlApp {

//  val json: String = MappingXml2JsonApp.generatedJson

  val json : String =
    """
      |     {
      |        "name": null,
      |        "label": "Funcionarios",
      |        "columns": [
      |            {
      |                "id": "a"
      |            },
      |            {
      |                "id": "data1"
      |            }
      |        ],
      |        "rows": [
      |             {
      |                "calculated": "1",
      |                "data": "2",
      |                "dimensionName": "3",
      |                "data0": {},
      |                "data1": {
      |                    "FmtValue": "108",
      |                    "Value": "108"
      |                },
      |                "operation": "update"
      |            }
      |        ]
      |
      |    }
      |""".stripMargin

  val json1 : String =
    """
      |     {
      |        "name": null,
      |        "label": "Funcionarios",
      |        "rows": [
      |            {
      |                "calculated": "",
      |                "data": "",
      |                "dimensionName": "",
      |                "data0": {},
      |                "data1": {
      |                    "FmtValue": "108",
      |                    "Value": "108"
      |                },
      |                "data2": {
      |                    "FmtValue": "108 - Funcionario",
      |                    "Value": "108 - Funcionario"
      |                },
      |                "data3": {
      |                    "FmtValue": "2.5000",
      |                    "Value": "2.5000"
      |                },
      |                "data4": {
      |                    "Value": true,
      |                    "FmtValue": true
      |                },
      |                "data5": {},
      |                "data6": {},
      |                "data7": {
      |                    "FmtValue": "1",
      |                    "Value": "1"
      |                },
      |                "data8": {},
      |                "operation": "update"
      |            }
      |        ],
      |        "columns": [
      |            {
      |                "id": null,
      |                "valueType": "Int",
      |                "visible": false,
      |                "editable": false,
      |                "sortable": false,
      |                "draggable": false,
      |                "headerTextAlign": null,
      |                "width": 0,
      |                "name": "data0",
      |                "cellTextAlign": null,
      |                "calculated": null,
      |                "formula": null,
      |                "label": "Id"
      |            },
      |            {
      |                "id": "data1",
      |                "valueType": "Int",
      |                "visible": true,
      |                "editable": false,
      |                "sortable": true,
      |                "draggable": false,
      |                "headerTextAlign": "left",
      |                "width": 0,
      |                "name": "data1",
      |                "cellTextAlign": "left",
      |                "calculated": null,
      |                "formula": null,
      |                "label": "Funcionario"
      |            },
      |            {
      |                "id": "data2",
      |                "valueType": "String",
      |                "visible": true,
      |                "editable": false,
      |                "sortable": false,
      |                "draggable": false,
      |                "headerTextAlign": "left",
      |                "width": 0,
      |                "name": "data2",
      |                "cellTextAlign": "left",
      |                "calculated": null,
      |                "formula": null,
      |                "label": "Nombre"
      |            },
      |            {
      |                "id": "data3",
      |                "valueType": "Int",
      |                "visible": true,
      |                "editable": true,
      |                "sortable": false,
      |                "draggable": false,
      |                "headerTextAlign": null,
      |                "width": 0,
      |                "name": "data3",
      |                "cellTextAlign": null,
      |                "calculated": null,
      |                "formula": null,
      |                "label": "Apertura"
      |            },
      |            {
      |                "id": "data4",
      |                "valueType": "Boolean",
      |                "visible": true,
      |                "editable": true,
      |                "sortable": false,
      |                "draggable": false,
      |                "headerTextAlign": null,
      |                "width": 100,
      |                "name": "data4",
      |                "cellTextAlign": "center",
      |                "calculated": null,
      |                "formula": null,
      |                "label": "Pertenece?"
      |            },
      |            {
      |                "id": "data5",
      |                "valueType": "Double",
      |                "visible": true,
      |                "editable": true,
      |                "sortable": false,
      |                "draggable": false,
      |                "headerTextAlign": null,
      |                "width": 0,
      |                "name": "data5",
      |                "cellTextAlign": "center",
      |                "calculated": null,
      |                "formula": null,
      |                "label": "Nueva Apertura"
      |            },
      |            {
      |                "id": "data6",
      |                "valueType": "Int",
      |                "visible": true,
      |                "editable": true,
      |                "sortable": false,
      |                "draggable": false,
      |                "headerTextAlign": null,
      |                "width": 0,
      |                "name": "data6",
      |                "cellTextAlign": null,
      |                "calculated": null,
      |                "formula": null,
      |                "label": "Mes Cambio"
      |            },
      |            {
      |                "id": "data7",
      |                "valueType": "Int",
      |                "visible": true,
      |                "editable": true,
      |                "sortable": false,
      |                "draggable": false,
      |                "headerTextAlign": "right",
      |                "width": 0,
      |                "name": "data7",
      |                "cellTextAlign": "Left",
      |                "calculated": null,
      |                "formula": null,
      |                "label": "Baja",
      |                "items": [
      |                    {
      |                        "value": "1",
      |                        "label": "Activo",
      |                        "url": null
      |                    },
      |                    {
      |                        "value": "2",
      |                        "label": "Cambio de sector",
      |                        "url": null
      |                    },
      |                    {
      |                        "value": "3",
      |                        "label": "Cese",
      |                        "url": null
      |                    },
      |                    {
      |                        "value": "4",
      |                        "label": "Cese por Jubliacion",
      |                        "url": null
      |                    }
      |                ]
      |            },
      |            {
      |                "id": "data8",
      |                "valueType": "Int",
      |                "visible": true,
      |                "editable": true,
      |                "sortable": false,
      |                "draggable": false,
      |                "headerTextAlign": null,
      |                "width": 0,
      |                "name": "data8",
      |                "cellTextAlign": null,
      |                "calculated": null,
      |                "formula": null,
      |                "label": "Mes Baja"
      |            }
      |        ]
      |    }
      |""".stripMargin
  def generatedXml: String = json2Xml(createdActions, json)

  def createdActions: Seq[MappingJson2Xml.TriggeredAction] = Seq(

    asElement("\\$$".r, "grid"),
    asAttribute("\\$.dataCount$".r, "dataCount"),
    asAttribute("\\$.elementCount$$".r, "elementCount"),

    asElementValue("\\$.name$".r, "name"),
    asElementValue("\\$.label$".r, "label"),

    asElement("\\$.columns$".r, "columns"),
    asElement("\\$.columns\\[[0-9+]\\]$".r, "col"),

    asAttribute("\\$.columns\\[[0-9+]\\].name$".r, "name"),
    asAttribute("\\$.columns\\[[0-9+]\\].label$".r, "label"),
    asAttribute("\\$.columns\\[[0-9+]\\].columnId$".r, "columnId"),
    asAttribute("\\$.columns\\[[0-9+]\\].member$".r, "member"),

    asElement("\\$.rows$".r, "rows"),
    asElement("\\$.rows\\[[0-9+]\\]$".r, "row"),

    asAttribute("\\$.rows\\[[0-9+]\\].dimensionName$".r, "dimensionName"),
    asAttribute("\\$.rows\\[[0-9+]\\].data$".r, "data"),
    asAttribute("\\$.rows\\[[0-9+]\\].calculated$".r, "calculated"),
    asAttribute("\\$.rows\\[[0-9+]\\].operation$".r, "operation"),


    // Ojo: esta extrae datos mediante la regex del currentPath
    TriggeredAction("\\$.rows\\[[0-9+]\\].(data[0-9+])$".r, Emit(
      (context, triggeredAction, jsonParser, xmlWriter) => {
        val triggeredAction.rExp(name) = context.jsonPath
        xmlWriter.writeStartElement(name)
      },
      (currentPath, triggeredAction, jsonParser, xmlWriter) => {
        xmlWriter.writeEndElement()
      })
    ),

    asElementValue("\\$.rows\\[[0-9+]\\].data[0-9+].Value$".r, "Value"),
    asElementValue("\\$.rows\\[[0-9+]\\].data[0-9+].FmtValue$".r, "FmtValue"),
  )

  def main(args: Array[String]): Unit = {

    val actions = createdActions

    actions.foreach { action =>
      println(action.rExp)
    }

    val xmlStr = json2Xml(actions, json1)
    println(s"  -> $xmlStr")
  }

}
