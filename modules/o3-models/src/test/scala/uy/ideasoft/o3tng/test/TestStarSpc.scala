package uy.ideasoft.o3tng.test

import uy.ideasoft.o3tng.metadata.bi.DerivedMeasureSrcSpc.AFTER_CALCULATE
import uy.ideasoft.o3tng.metadata.bi.{AttrDimensionSpc, AttrHierarchySpc, AttrLeftJoinSpc, AttrLevelSpc, BasicMeasureSrcSpc, CubeSpc, DateDerivationSpc, DateDimensionSpc, DateHierarchySpc, DateLevelSpc, DerivedMeasureSrcSpc, MeasureSpc, StarDimensionSpc, StarFactsSpc, StarSchemaSpc}

object TestStarSpc {

  val preciosStar: StarSchemaSpc =
    StarSchemaSpc(
      name = "Precios",
      dimensions = Seq(
        StarDimensionSpc(
          name = "Date",
          dimSpc = DateDimensionSpc(
            hierarchies = Seq(
              DateHierarchySpc(
                levels = Seq(
                  DateLevelSpc(name = "Year", granularity = "LEVEL_YEAR"),
                  DateLevelSpc(name = "Month", granularity = "LEVEL_MONTH"),
                )
              )
            )
          )
        ),
        StarDimensionSpc(
          name = "Scenario",
          dimSpc = AttrDimensionSpc(
            table = "simScene",
            schema = "simulation",
            hierarchies = Seq(
              AttrHierarchySpc(
                levels = Seq(
                  AttrLevelSpc(
                    name = "scene",
                    keyAttribute = "scene_id",
                    labelAttr = Some("label"),
                    descriptionAttr = Some("description")
                  )
                )
              )
            )
          )
        ),
        StarDimensionSpc(
          name = "products",
          dimSpc = AttrDimensionSpc(
            table = "products",
            schema = "ancel2",
            hierarchies = Seq(
              AttrHierarchySpc(
                levels = Seq(
                  AttrLevelSpc(
                    name = "Producto",
                    keyAttribute = "code",
                    helpSchemaName = Some("ancel2"),
                    helpTableName = Some("products"),
                    labelAttr = Some("name")
                  )
                )
              )
            )
          )
        )
      ),
      facts = Seq(
        StarFactsSpc(
          name = "Precios",
          table = "prices",
          schema = "ancel2-simulation",
          joins = Seq(
            AttrLeftJoinSpc(
              dimension = "Date",
              attributes = Seq("simulation_time")
            ),
            AttrLeftJoinSpc(
              dimension = "Scenario",
              attributes = Seq("scene_id")
            ),
            AttrLeftJoinSpc(
              dimension = "Productos",
              attributes = Seq("product_code")
            ),
          ),
          measures = Seq(
            MeasureSpc(
              name = "Precio",
              aggregation = "SUM",
              measureSrc = BasicMeasureSrcSpc(
                measureAttr = "value"
              ),
            ),
            MeasureSpc(
              name = "Precio Acumulado",
              aggregation = "SUM",
              measureSrc = DerivedMeasureSrcSpc(
                calculate = AFTER_CALCULATE,
                derivation = DateDerivationSpc(
                  measure = "Precio",
                  dateDimension = "Date"
                )
              )
            )
          )
        )
      ),
      cubes = Seq(
        CubeSpc(
          name = "Precios",
          factTables = Seq("Precios")
        )
      )
    )
}
