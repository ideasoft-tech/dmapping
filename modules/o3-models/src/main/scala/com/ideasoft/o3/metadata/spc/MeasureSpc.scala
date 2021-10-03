package com.ideasoft.o3.metadata.spc

case class MeasureSpc(
  name: String,
  description: Option[String] = None,
  measureSrc: MeasureSrcSpc,
  aggregation: String,
)


object MeasureSpc {
  val SUM_AGGREGATION_FUNCTION = "SUM"
  val MAX_AGGREGATION_FUNCTION = "MAX"
  val MIN_AGGREGATION_FUNCTION = "MIN"
  val AVG_AGGREGATION_FUNCTION = "AVG"
  val COUNT_AGGREGATION_FUNCTION = "COUNT"
}

sealed trait MeasureSrcSpc;

case class AttrMeasureSrcSpc(measureAttr: String) extends MeasureSrcSpc

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
  accumulationLevel: String,
  smooth: Option[SmoothSerie] = None
) extends DerivationSpc

case class SmoothSerie(periods: Int)