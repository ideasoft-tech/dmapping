package uy.ideasoft.o3tng.metadata.bi

case class StarSchemaSpc(
  name: String,
  description: Option[String] = None,
  dimensions: Seq[StarDimensionSpc],
  facts: Seq[StarFactsSpc],
  cubes: Seq[CubeSpc]
)

case class StarDimensionSpc(
  name: String,
  description: Option[String] = None,
  dimSpc: DimensionSpc
)

case class StarFactsSpc(
  name: String,
  description: Option[String] = None,
  table: String,
  schema: String,
  joins: Seq[StarJoinSpc],
  measures: Seq[MeasureSpc]
)

class AttrSpc(name: String)

sealed trait StarJoinSpc

case class ForeignKeyLeftJoinSpc(
  dimension: String,
  foreignKey: String
) extends StarJoinSpc


case class AttrLeftJoinSpc(
  dimension: String,
  attributes: Seq[String]
) extends StarJoinSpc

case class CubeSpc(
  name: String,
  description: Option[String] = None,
  factTables: Seq[String]
)


