package com.ideasoft.o3.metadata.spc

case class StarSchemaSpc(
  name: String,
  description: Option[String] = None,
  dimensions: Seq[StarDimensionSpc],
  factsTables: Seq[StarFactsSpc],
  cubes: Seq[CubeSpc]
)

case class StarDimensionSpc(
  name: String,
  description: Option[String],
  definition: DimensionSpc
)

case class StarFactsSpc(
  name: String,
  description: Option[String] = None,
  table: String,
  schema: String,
  joinDimension: StartJoin,
  measures: Seq[MeasureSpc]
)

case class StartJoin(
  dimension: String
)

case class CubeSpc(
  name: String,
  description: Option[String] = None,
  factTables: Seq[String]
)


