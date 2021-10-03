package com.ideasoft.o3.metadata.spc

sealed trait DimensionSpc;

/**
 * AttrDimension
 */
case class AttrDimensionSpc(
  table: String,
  schema: String,
  hierarchy: Seq[AttrHierarchySpc]
) extends DimensionSpc

case class AttrHierarchySpc(
  levels: Seq[AttrDimLevelSpc]
)

/**
 * DateDimension
 */
case class DateDimensionSch(
  hierarchy: Seq[DateHierarchySch]
) extends DimensionSpc

case class DateHierarchySch(
  levels: Seq[DateDimLevelSpc]
) extends DimensionSpc



