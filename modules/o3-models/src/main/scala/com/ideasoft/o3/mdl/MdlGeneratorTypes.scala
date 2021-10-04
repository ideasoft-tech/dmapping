package com.ideasoft.o3.mdl

import com.ideasoft.edf.core.env.SessionContext
import com.ideasoft.edf.data.api.SchemaConnectionDescriptor
import com.ideasoft.edf.data.metadata.impl.Table
import com.ideasoft.edf.data.spec.{DataField, Metadata}
import com.ideasoft.o3.metadata.api._
import uy.ideasoft.o3tng.metadata.bi._
import uy.ideasoft.o3tng.metadata.ctx.{ConnectionSpc, ContextSpc}
import uy.ideasoft.o3tng.metadata.rdb.RelationalSpc

import java.util
import scala.jdk.CollectionConverters._
import scala.language.implicitConversions

object MdlGeneratorTypes {

  implicit def mdlCtx2SessionCtx(ctx: ContextSpc): SessionContext = new SessionContext {
    override def getMetadata: Metadata = ctx.relationalSpc

    override def getConnectionDescriptor: SchemaConnectionDescriptor = ctx.schConnection
  }

  implicit def relationalSpc2Metadata(relationalSpc: RelationalSpc): Metadata = new Metadata {
    override def getTable(sch: String, name: String): Table = null

    override def getFieldbyFieldName(var1: String): DataField = null
  }

  implicit def connectionSpc2Connection(connSpc: ConnectionSpc): SchemaConnectionDescriptor = new SchemaConnectionDescriptor {
    override def getEngine: String = connSpc.engine.getOrElse("")

    override def getDriver: String = connSpc.driver.getOrElse("")

    override def getURL: String = connSpc.url.getOrElse("")

    override def getUser: String = connSpc.user.getOrElse("")

    override def getPassword: String = connSpc.password.getOrElse("")

    override def getDataSource: String = connSpc.dataSource.getOrElse("")

    override def getParameters: util.Map[_, _] = connSpc.parameters.asJava
  }

  implicit def starSpc2StarModelSpec(starSpc: StarSchemaSpc): StarModelSpec = new StarModelSpec {
    override def getName: String = starSpc.name
    override def getDescription: String = starSpc.description.getOrElse("")
    override def getDimensions: util.List[StarDimensionSpec] = starSpc.dimensions
    override def getFactTables: util.List[FactTableSpec] = starSpc.facts
    override def getCubes: util.List[CubeSpec] = starSpc.cubes
  }

  implicit def s2j[A, B](l: Seq[A])(implicit f: A => B): util.List[B] = l.map(f(_)).asJava

  implicit def starDimensionSpc2Spec(d: StarDimensionSpc): StarDimensionSpec = new StarDimensionSpec {
    override def getName: String = d.name
    override def getDescription: String = d.description.orNull
    override def getDimensionDefinition: DimensionDefinitionSpec = d.dimSpc
  }

  implicit def dimSpc2Spec(d: DimensionSpc): DimensionDefinitionSpec = d match {
    case AttrDimensionSpc(table, schema, hierarchies, uniqueLevel) => new TableDimensionDefinitionSpec {
      override def getTableName: String = table
      override def getEdfSchemaName: String = schema
      override def getUniqueLevel: String = uniqueLevel.orNull
      override def getHierarchies: util.List[TableHierarchySpec] = hierarchies
    }
    case DateDimensionSpc(hierarchies) => new DateDimensionDefinitionSpec {
      override def getHierarchies: util.List[DateHierarchySpec] = hierarchies
    }
  }

  implicit def attrHierarchySpc2Spec(h: AttrHierarchySpc): TableHierarchySpec = new TableHierarchySpec {
    override def getLevels: util.List[TableLevelSpec] = h.levels
  }

  implicit def attrLevelSpc2Spec(level: AttrLevelSpc): TableLevelSpec = new TableLevelSpec {
    override def getName: String = level.name
    override def getDescription: String = level.description.orNull
    override def getUniqueLevel: Boolean = level.isUnique
    override def getKeyAttribute: String = level.keyAttribute
    override def getHelpSchemaName: String = level.helpSchemaName.orNull
    override def getHelpTableName: String = level.helpTableName.orNull
    override def getLabelAttribute: String = level.labelAttr.orNull
    override def getDescriptionAttribute: String = level.longDescriptionAttr.orNull
    override def getLongDescriptionAttribute: String = level.longDescriptionAttr.orNull
  }

  implicit def dateHierarchySpc2Spec(h: DateHierarchySpc): DateHierarchySpec = new DateHierarchySpec {
    override def getLevels: util.List[DateLevelSpec] = h.levels
  }

  implicit def dateHierarchySpc2Spec(level: DateLevelSpc): DateLevelSpec = new DateLevelSpec {
    override def getName: String = level.name
    override def getDescription: String = level.description.orNull
    override def getUniqueLevel: Boolean = level.isUnique
    override def getGranularity: String = level.granularity
  }

  implicit def starFactsSpc2Spec(f: StarFactsSpc):FactTableSpec = new FactTableSpec {
    override def getName: String = f.name
    override def getDescription: String = f.description.orNull
    override def getTableName: String = f.table
    override def getEdfSchemaName: String = f.schema
    override def getDimensionReferences: util.List[DimensionReferenceSpec] = f.joins
    override def getMeasures: util.List[MeasureSpec] = f.measures
  }

  implicit def starJoinSpc2Spec(j: StarJoinSpc): DimensionReferenceSpec = j match {
    case ForeignKeyLeftJoinSpc(dimension, foreignKey) => new ForeignKeyDimensionReferenceSpec {
      override def getDimension: String = dimension
      override def getForeignKey: String = foreignKey
    }
    case AttrLeftJoinSpc(dimension, attributes) => new StarDimensionReferenceSpec {
      override def getDimension: String = dimension
      override def getFactAttributes: util.List[String] = attributes.asJava
    }
  }

  implicit def measureSpc2Spec(m: MeasureSpc): MeasureSpec = new MeasureSpec {
    override def getName: String = m.name
    override def getDescription: String = m.description.orNull
    override def getType: MeasureTypeSpec = m.measureSrc
    override def getAggregationFunction: String = m.aggregation
  }

  implicit def measureSrcSpc2Spec(mSrc: MeasureSrcSpc): MeasureTypeSpec = mSrc match {
    case BasicMeasureSrcSpc(measureAttr) => new BasicMeasureTypeSpec {
      def getMeasureAttribute: String = measureAttr
    }
    case DerivedMeasureSrcSpc(calculate, derivation) => new DerivedMeasureTypeSpec {
      def getCalculate: String = calculate
      def getMeasureDerivation: MeasureDerivationSpec = derivation match {
        case ExpressionDerivationSpc(expression) => new ExpressionDerivationSpec {
          def getExpression: String = expression
        }
        case DateDerivationSpc(measure, dateDimension, accumulationLevel, smooth) => new DateAcumulationDerivationSpec {
          def getMeasure: String = measure
          def getDateDimension: String = dateDimension
          def getAcumulationLevel: String = accumulationLevel.orNull
          def getSmooth: SmoothSpec = smooth.map(s => new SmoothSpec {
            def getPeriods: Int = s.periods
          }).orNull
        }
        case TwoMeasureDerivationSpc(measure1, operator, measure2) => new TwoMeasureDerivationSpec {
          def getMeasure1: String = measure1
          def getOperator: String = operator
          def getMeasure2: String = measure2
        }
      }
    }
  }

  implicit def cubeSpc2Spec(cubeSpc: CubeSpc): CubeSpec = new CubeSpec {
    def getName: String = cubeSpc.name
    def getDescription: String = cubeSpc.description.orNull
    def getFactTables: util.List[String] = cubeSpc.factTables
  }
}
