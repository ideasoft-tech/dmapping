package uy.ideasoft.o3tng.metadata.bi

case class MeasureSpc(
  name: String,
  description: Option[String] = None,
  aggregation: String,
  measureSrc: MeasureSrcSpc,
)


object MeasureSpc {
  val SUM_AGGREGATION = "SUM"
  val MAX_AGGREGATION = "MAX"
  val MIN_AGGREGATION = "MIN"
  val AVG_AGGREGATION = "AVG"
  val COUNT_AGGREGATION = "COUNT"
}

sealed trait MeasureSrcSpc;

case class BasicMeasureSrcSpc(
  measureAttr: String
) extends MeasureSrcSpc

case class DerivedMeasureSrcSpc(
  calculate: String,
  derivation: DerivationSpc
) extends MeasureSrcSpc

object DerivedMeasureSrcSpc {
  val BEFORE_CALCULATE = "before"
  val AFTER_CALCULATE = "after"
}

sealed trait DerivationSpc;

case class ExpressionDerivationSpc(
  expression: String
) extends DerivationSpc

case class TwoMeasureDerivationSpc(
  measure1: String,
  operator: String,
  measure2: String
) extends DerivationSpc

case class DateDerivationSpc(
  measure: String,
  dateDimension: String,
  accumulationLevel: Option[String] = None,
  smooth: Option[SmoothSerie] = None
) extends DerivationSpc

case class SmoothSerie(periods: Int)