package uy.ideasoft.o3tng.metadata.bi

sealed trait DimensionSpc;

/**
 * AttrDimension
 */
case class AttrDimensionSpc(
  table: String,
  schema: String,
  hierarchies: Seq[AttrHierarchySpc],
  uniqueLevel: Option[String] = None
) extends DimensionSpc

case class AttrHierarchySpc(
  levels: Seq[AttrLevelSpc]
)

/**
 * DateDimension
 */
case class DateDimensionSpc(
  hierarchies: Seq[DateHierarchySpc]
) extends DimensionSpc

case class DateHierarchySpc(
  levels: Seq[DateLevelSpc]
)



