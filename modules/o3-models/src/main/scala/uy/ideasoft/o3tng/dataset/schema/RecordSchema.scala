package uy.ideasoft.o3tng.dataset.schema

/**
 * Reference Data
 */
object RecordSchema {

  type SchemaRef = String

  final case class DatasetsSchemas(
    recordDatasets: Seq[RecordDatasetSchema]
  )

  final case class RecordDatasetSchema(
    name: String,
    label: Option[String] = None,
    description: Option[String] = None,
    cols: Seq[RecordDatasetColumn],
    key: Seq[String]
  )

  final case class RecordDatasetColumn(
    name: SchemaRef,
    label: String,
    valueType: String,
    order: Int,
    required: Boolean = true,
    editable: Boolean = true,
    visible: Boolean = true,
    inValue: Option[InFacet] = None
  )

  sealed trait InFacet
  case class InDataset(datasetRef: String, valueColumn: String, labelColumn: String) extends InFacet
  case class InEnum(enum: Seq[EnumValue]) extends InFacet
  case class EnumValue(value: String, label: String)

}
