package com.ideasoft.o3.mdl

import com.ideasoft.edf.core.env.SessionContext
import com.ideasoft.edf.data.metadata.impl.Table
import com.ideasoft.edf.data.spec.{DataField, Metadata}
import com.ideasoft.edf.data.metadata.impl.Field
import com.ideasoft.edf.types.datatype.DatatypeEvaluator
import uy.ideasoft.o3tng.dataset.schema.RecordSchema.{DatasetsSchemas, RecordDatasetColumn}

import scala.language.implicitConversions

object DatasetTypes {

  import DatasetTypes._

//  implicit def dsCol2Field(dsCol: RecordDatasetColumn): Field = {
//    val f: Field = new Field()
//    f.setName(dsCol.name)
//    f
//  }

  implicit def dsCol2Field(dsCol: RecordDatasetColumn): DataField = new DataField {
    def setSessionContext(var1: SessionContext): Unit = ???

    def getValues: Array[String] = ???

    def getLabels: Array[String] = ???

    def getDescriptions: Array[String] = ???

    def getLabel: String = ???

    def getDescription: String = ???

    def getHelpText: String = ???

    def getErrorMessage(var1: String): String = ???

    def getLabelforValue(var1: String): String = ???

    def getDescriptionforValue(var1: String): String = ???

    def getDatatype: DatatypeEvaluator = new DatatypeEvaluator {
      def getValue(var1: String): AnyRef = ???

      def getLexicalValue(var1: Any): String = ???

      def isValid(var1: String): Boolean = ???

      def getTypeName: String = dsCol.valueType

      def getType: Int = ???

      def hasListType: Boolean = ???

      def getBasicTypeEvaluator: DatatypeEvaluator = ???

      def normalizeValue(var1: Any): AnyRef = ???
    }

    def getName: String = dsCol.name

    def getQName: String = ???

    def isNullable: Boolean = ???

    def getErrorMessage(var1: Int): String = ???

    def getErrorMessageCount: Int = ???
  }

  implicit def relationalSpc2Metadata(d: DatasetsSchemas): Metadata = new Metadata {
    override def getTable(sch: String, name: String): Table = d.recordDatasets.find(_.name == name).map( d => {
      val table = new Table
      table.setName(d.name)
      d.label.foreach(table.setLabel)
      table
      }).orNull

    def getFieldbyFieldName(var1: String): DataField = {
      val fieldRegExpr = """([a-zA-Z-0-9_]+).([a-zA-Z-0-9_]+).([a-zA-Z-0-9_]+)""".r
      var1 match {
        case fieldRegExpr(schema, table, fieldName) =>
          val mayDS = d.recordDatasets.find(_.name == table)
          val x = mayDS.flatMap(ds => ds.cols.find(_.name == fieldName)).map(dsCol2Field)
          x.orNull

        case _ => null
      }
    }
  }

}
