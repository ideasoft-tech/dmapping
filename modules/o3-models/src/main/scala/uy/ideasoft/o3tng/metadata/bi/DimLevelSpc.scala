package uy.ideasoft.o3tng.metadata.bi

sealed trait DimLevelSpc {
  def name: String
  def description: Option[String]
  def isUnique: Boolean
}

case class AttrLevelSpc(
  name: String,
  description: Option[String] = None,
  isUnique: Boolean = false,
  keyAttribute: String,
  helpSchemaName: Option[String] = None,
  helpTableName: Option[String] = None,
  labelAttr: Option[String] = None,
  longDescriptionAttr: Option[String] = None,
  descriptionAttr: Option[String] = None
) extends DimLevelSpc


case class DateLevelSpc(
  name: String,
  description: Option[String] = None,
  isUnique: Boolean = false,
  granularity: String
) extends DimLevelSpc

object DateLevelSpc {
  /**
   * Those are the possible granularities for the date level
   */
  val YEAR_GRANULARITY = "LEVEL_YEAR"
  val SEMESTER_GRANULARITY = "LEVEL_SEMESTER"
  val QUARTER_GRANULARITY = "LEVEL_QUARTER"
  val MONTH_GRANULARITY = "LEVEL_MONTH"
  val WEEK_GRANULARITY = "LEVEL_WEEK"
  val DAY_GRANULARITY = "LEVEL_DAY"
}
