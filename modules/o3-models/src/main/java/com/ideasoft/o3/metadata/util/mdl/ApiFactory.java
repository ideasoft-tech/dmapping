//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: ApiFactory.java,v 1.25 2006/05/19 14:35:45 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl;

import com.ideasoft.o3.metadata.EdfFieldNotFoundException;
import com.ideasoft.o3.metadata.EdfMetadataNotFoundException;
import com.ideasoft.o3.metadata.MetadataException;
import ideasoft.util.log.ILog;
import ideasoft.util.log.ILogFactory;

import com.ideasoft.app.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.ideasoft.edf.core.env.SessionContext;
import com.ideasoft.edf.data.api.SchemaConnectionDescriptor;
import com.ideasoft.edf.data.metadata.impl.FieldName;
import com.ideasoft.edf.data.metadata.impl.ForeignKey;
import com.ideasoft.edf.data.metadata.impl.KeysSequence;
import com.ideasoft.edf.data.spec.DataField;
import com.ideasoft.edf.data.spec.Metadata;
import com.ideasoft.o3.metadata.api.BasicMeasureTypeSpec;
import com.ideasoft.o3.metadata.api.DateDimensionDefinitionSpec;
import com.ideasoft.o3.metadata.api.DerivedMeasureTypeSpec;
import com.ideasoft.o3.metadata.api.DimensionReferenceSpec;
import com.ideasoft.o3.metadata.api.FactTableSpec;
import com.ideasoft.o3.metadata.api.ForeignKeyDimensionReferenceSpec;
import com.ideasoft.o3.metadata.api.StarDimensionReferenceSpec;
import com.ideasoft.o3.metadata.api.StarDimensionSpec;
import com.ideasoft.o3.metadata.api.StarModelSpec;
import com.ideasoft.o3.metadata.api.TableDimensionDefinitionSpec;
import com.ideasoft.o3.metadata.util.mdl.api.BasicMeasureSpec;
import com.ideasoft.o3.metadata.util.mdl.api.ConnectionSourceSpec;
import com.ideasoft.o3.metadata.util.mdl.api.DateAcumulationDerivationSpec;
import com.ideasoft.o3.metadata.util.mdl.api.DateDimensionSpec;
import com.ideasoft.o3.metadata.util.mdl.api.DateLevelSpec;
import com.ideasoft.o3.metadata.util.mdl.api.DerivedMeasureSpec;
import com.ideasoft.o3.metadata.util.mdl.api.DimensionScopeSpec;
import com.ideasoft.o3.metadata.util.mdl.api.DimensionSpec;
import com.ideasoft.o3.metadata.util.mdl.api.MeasureDerivationSpec;
import com.ideasoft.o3.metadata.util.mdl.api.MeasureSpec;
import com.ideasoft.o3.metadata.util.mdl.api.O3ModelSpec;
import com.ideasoft.o3.metadata.util.mdl.api.QuerySourceFieldSpec;
import com.ideasoft.o3.metadata.util.mdl.api.QuerySourceSpec;
import com.ideasoft.o3.metadata.util.mdl.api.SmoothSpec;
import com.ideasoft.o3.metadata.util.mdl.api.SourceSpec;
import com.ideasoft.o3.metadata.util.mdl.api.StandardDimensionSpec;
import com.ideasoft.o3.metadata.util.mdl.api.StandardLevelSpec;

/**
 * This class buils mdl api components from an star model
 * @author ignacio
 * @version $Revision: 1.25 $
 */
public class ApiFactory {

	public ApiFactory() {
		this(null);
	}
	public ApiFactory(String propertiesFile) {
		loadProperties(propertiesFile);
	}
	
	/**
	 * @param context Is context to be added to the execution
	 */
	public void setContext(Map<String, Object> context) {
		this.context = context;
	}
	
	/**
	 * @return the context to be added to the execution
	 */
	public Map<String, Object> getContext() {
		return context;
	}
	
	/**
	 * @param sessionContext Is edf session context
	 * @param starModel Is the star model to get the model
	 * @return An O3ModelSpec instance based on a starModel instance
	 */
	public O3ModelSpec getO3Model(SessionContext sessionContext, final StarModelSpec starModel)
		throws EdfMetadataNotFoundException, EdfFieldNotFoundException {
		Metadata edfMetadata = sessionContext.getMetadata();
		O3ModelSpec o3ModelSpec = new O3ModelSpec() {
			public String getName() { return starModel.getName(); }
			public String getDescription() { return starModel.getDescription(); }
			public List<DimensionSpec> getDimensions() { return dimensions; }
			public List<MeasureSpec> getMeasures() { return measures; }
			public List<SourceSpec> getSources() { return sources; }
			
			private ArrayList<DimensionSpec> dimensions = new ArrayList<DimensionSpec>();
			private ArrayList<MeasureSpec> measures = new ArrayList<MeasureSpec>();
			private ArrayList<SourceSpec> sources = new ArrayList<SourceSpec>();
		};		
		
		// Recorro las dimensions (StarModel) para crear las dimensiones (O3Model)
		for (final StarDimensionSpec dimension : starModel.getDimensions()) {
			o3ModelSpec.getDimensions().add(getDimension(starModel, edfMetadata, dimension));			
		}
		
		// Recorro las tablas de hechos (StarModel) para crear medidas
		for (FactTableSpec factTable : starModel.getFactTables()) {
			for (com.ideasoft.o3.metadata.api.MeasureSpec measure : factTable.getMeasures()) {
				o3ModelSpec.getMeasures().add(getMeasure(starModel, factTable, measure));
			}
		}
		
		// Aggrego las conexiones y consultas
		o3ModelSpec.getSources().addAll(getSources(sessionContext, starModel));
		
		return o3ModelSpec;
	}
	
	/**
	 * @param sessionContext Is edf session context
	 * @param starModel Is the star model associated
	 * @return A list of connections with queries to be used for star schema objects
	 */
	private List<SourceSpec> getSources(SessionContext sessionContext, final StarModelSpec starModel)
		throws EdfMetadataNotFoundException, EdfFieldNotFoundException {
		Metadata edfMetadata = sessionContext.getMetadata();
		List<SourceSpec> connections = new ArrayList<SourceSpec>();
		
		// Object connections
		Map<String,List<Object>> objectsBySchema = new HashMap<String,List<Object>>();
		
		// Recorro las dimensiones (StarModel)
		for (final StarDimensionSpec dimension : starModel.getDimensions()) {
			String edfSchemaName = null;
			
			// Find Schema
			if (dimension.getDimensionDefinition() instanceof TableDimensionDefinitionSpec) {
				edfSchemaName = ((TableDimensionDefinitionSpec) dimension.getDimensionDefinition()).getEdfSchemaName();
			} else if (dimension.getDimensionDefinition() instanceof DateDimensionDefinitionSpec && dateMeasuresMode.equals(MEASURES_MODE_DEFAULT)) {
				outerLoop: for (FactTableSpec factTable : starModel.getFactTables()) {
					for (DimensionReferenceSpec reference : factTable.getDimensionReferences()) {
						if (reference.getDimension().equals(dimension.getName())) {
							edfSchemaName = factTable.getEdfSchemaName();
							break outerLoop;
						}
					}
				}
			}
			
			if (edfSchemaName == null) {
				continue;
			}
			
			// Put object
			if (! objectsBySchema.containsKey(edfSchemaName)) {
				objectsBySchema.put(edfSchemaName, new ArrayList<Object>());
			}
			
			List<Object> schemaObjects = objectsBySchema.get(edfSchemaName);
			schemaObjects.add(dimension);
		}
		
		// Recorro las tablas de hechos (StarModel)
		for (FactTableSpec factTable : starModel.getFactTables()) {
			if (! objectsBySchema.containsKey(factTable.getEdfSchemaName())) {
				objectsBySchema.put(factTable.getEdfSchemaName(), new ArrayList<Object>());
			}
			
			List<Object> schemaTables = objectsBySchema.get(factTable.getEdfSchemaName());
			schemaTables.add(factTable);
		}
		
		// Recorro los objetos identificados
		for (final String schema : objectsBySchema.keySet()) {
			final SchemaConnectionDescriptor connectionDescription = sessionContext.getConnectionDescriptor();
			
			ConnectionSourceSpec connection = null;
			if (connectionDescription == null) {
				connection = new ConnectionSourceSpec() {
					public boolean isVerified() { return true; }
					public String getUser() { return DEFAULT_CONN_USER; }
					public String getPassword() { return DEFAULT_CONN_PASSWD; }
					public String getDriver() { return DEFAULT_CONN_DRIVER; }
					public String getProtocol() { return DEFAULT_CONN_PROTOCOL; }
					public String getParameters() { return DEFAULT_CONN_PARAMETERS; }
					public String getName() { return schema; }
					public String getDescription() { return ""; }
					public List<QuerySourceSpec> getQueries() { return queries; }
					
					private List<QuerySourceSpec> queries = new ArrayList<QuerySourceSpec>();
				};
			} else {
				connection = new ConnectionSourceSpec() {
					public String getName() { return schema; }
					public String getDescription() { return ""; }
					public boolean isVerified() { return true; }
					public String getUser() { return connectionDescription.getUser(); }
					public String getPassword() { return connectionDescription.getPassword(); }
					public String getDriver() { return connectionDescription.getDriver(); }
					public String getProtocol() {
						String url = connectionDescription.getURL();
						int firstIndex = url.indexOf(":");
						int lastIndex = url.indexOf(":", firstIndex + 1);					
						return url.substring(firstIndex + 1, lastIndex);
					}
					public String getParameters() {
						String url = connectionDescription.getURL();
						int firstIndex = url.indexOf(":");
						int lastIndex = url.indexOf(":", firstIndex + 1);
						return url.substring(lastIndex + 1);
					}
					public List<QuerySourceSpec> getQueries() { return queries; }
					
					private List<QuerySourceSpec> queries = new ArrayList<QuerySourceSpec>();
					
				};
			}
			
			for (Object object : objectsBySchema.get(schema)) {
				if (object instanceof FactTableSpec) {
					connection.getQueries().add(getQuerySource(edfMetadata, starModel, (FactTableSpec) object));
				} else if (object instanceof StarDimensionSpec) {
					StarDimensionSpec dimension = (StarDimensionSpec) object;
					if (dimension.getDimensionDefinition() instanceof TableDimensionDefinitionSpec) {
						connection.getQueries().add(getQuerySource(edfMetadata, starModel, dimension,
							(TableDimensionDefinitionSpec) dimension.getDimensionDefinition()));
					} else if (dimension.getDimensionDefinition() instanceof DateDimensionDefinitionSpec && dateMeasuresMode.equals(MEASURES_MODE_DEFAULT)) {
						connection.getQueries().add(getQuerySource(edfMetadata, starModel, dimension));
					}
				}
			}
			
			connections.add(connection);
		}
		
		return connections;
	}
	
	/**
	 * @return The dimension asociated to a star dimension
	 */
	private DimensionSpec getDimension(final StarModelSpec starModel, final Metadata edfMetadata, final StarDimensionSpec dimension) {
		if (dimension.getDimensionDefinition() instanceof DateDimensionDefinitionSpec) {			
			DateDimensionSpec dateDimension = new DateDimensionSpec() {
				public String getName() { return dimension.getName(); }
				public String getDescription() { return dimension.getDescription(); }
				public String getMeasuresMode() { return dateMeasuresMode; }
				public String getDateField() {
					List<String> fields = getFactTableFields(starModel, edfMetadata, dimension.getName());
					// Aca estoy pensando que si la dimension es fecha aca esta el campo date, si hay error alguien antes tuvo que detectarlo no yo
					return fields.get(0);
				}
				public List<DateLevelSpec> getLevels() { return levels; }

				private List<DateLevelSpec> levels = new ArrayList<DateLevelSpec>();				
			};
			
			DateDimensionDefinitionSpec definition = (DateDimensionDefinitionSpec) dimension.getDimensionDefinition();
			for (final com.ideasoft.o3.metadata.api.DateLevelSpec level : definition.getHierarchies().get(0).getLevels()) {
				dateDimension.getLevels().add(new DateLevelSpec() {
					public String getName() { return level.getName(); }
					public String getDescription() { return level.getDescription(); }
					public String getGranularity() { return level.getGranularity(); }	// Estoy suponiendo que los nombres son iguales (de hecho ahora lo son)
				});
			}
			return dateDimension;
		} else if (dimension.getDimensionDefinition() instanceof TableDimensionDefinitionSpec) {
			StandardDimensionSpec standarDimension = new StandardDimensionSpec() {
				public String getName() { return dimension.getName(); }
				public String getDescription() { return dimension.getDescription(); }
				public String getMeasuresMode() { return measuresMode; }
				public List<StandardLevelSpec> getLevels() { return levels; }

				private List<StandardLevelSpec> levels = new ArrayList<StandardLevelSpec>();
				
			};

			int index = 0;
			TableDimensionDefinitionSpec definition = (TableDimensionDefinitionSpec) dimension.getDimensionDefinition();
			for (final com.ideasoft.o3.metadata.api.TableLevelSpec level : definition.getHierarchies().get(0).getLevels()) {
				final int count = index;
				standarDimension.getLevels().add(new StandardLevelSpec() {
					public String getName() { return level.getName(); }
					public String getDescription() { return level.getDescription(); }
					public String getKeyField() {
						List<String> factTableFields = getFactTableFields(starModel, edfMetadata, dimension.getName());
						if (factTableFields == null) {
							throw new RuntimeException("Fact table fields not found for dimension " + dimension.getName());
						}
						return factTableFields.get(count); 
					}
					public String getLabelField() { return level.getLabelAttribute() != null ? level.getKeyAttribute() + "_label" : null; }
					public String getLongLabelField() { return level.getLongDescriptionAttribute() != null ? level.getKeyAttribute() + "_long_label" : null; }
					public String getDescriptionField() { return level.getDescriptionAttribute() != null ? level.getKeyAttribute() + "_description" : null; }
					public boolean getUniqueLevel() { return level.getUniqueLevel(); }
				});
				index++;
			}
			
			return standarDimension;
		}
		
		return null;
	}
	
	/**
	 * @return The a measure associated to a fact table
	 */
	private MeasureSpec getMeasure(final StarModelSpec starModel, final FactTableSpec factTable,
		final com.ideasoft.o3.metadata.api.MeasureSpec measure) {
		if (measure.getType() instanceof BasicMeasureTypeSpec) {		
			return new BasicMeasureSpec() {
				public String getName() { return measure.getName(); }
				public String getDescription() { return measure.getDescription(); }
				public String getAggregationFunction() { return measure.getAggregationFunction(); }
				public String getField() { return ((BasicMeasureTypeSpec) measure.getType()).getMeasureAttribute(); }
				public List<DimensionScopeSpec> getDimensionScopes() {
					List<DimensionScopeSpec> result = new ArrayList<DimensionScopeSpec>();
					int count = 0;
					// Para cada dimension, crear la scope spec.
					for (final StarDimensionSpec dimension : starModel.getDimensions()) {
						final int index = count;
						final boolean isDimensionAssociated = isDimensionAssociated(factTable, dimension);
						DimensionScopeSpec dimensionScopeSpec = new DimensionScopeSpec() {
							public String getDimensionName() { return dimension.getName(); };
							public int getDimensionIndex() { return index; };
							public String getType() { return isDimensionAssociated ? "INAGGR_TYPE" : "UNDEF_TYPE"; }
							public List<String> getLevelScopes() { return levelScopes; };
							
							private List<String> levelScopes = new ArrayList<String>();
						};
						
						if (dimension.getDimensionDefinition() instanceof DateDimensionDefinitionSpec) {
							DateDimensionDefinitionSpec dateDimensionDefinition = (DateDimensionDefinitionSpec) dimension.getDimensionDefinition();
							for (com.ideasoft.o3.metadata.api.DateLevelSpec dateLevel : dateDimensionDefinition.getHierarchies().get(0).getLevels()) {
								dimensionScopeSpec.getLevelScopes().add("AGGREGATED_SCOPE");
							}
						} else {
							TableDimensionDefinitionSpec tableDimensionDefinition = (TableDimensionDefinitionSpec) dimension.getDimensionDefinition();
							for (com.ideasoft.o3.metadata.api.TableLevelSpec tableLevel : tableDimensionDefinition.getHierarchies().get(0).getLevels()) {
								dimensionScopeSpec.getLevelScopes().add("AGGREGATED_SCOPE");
							}
						}
						
						count++;
						result.add(dimensionScopeSpec);
					}
					return result;
				}
			};
		} else {
			final DerivedMeasureTypeSpec derivedMeasureType = (DerivedMeasureTypeSpec) measure.getType();
			final com.ideasoft.o3.metadata.api.DateAcumulationDerivationSpec dateAcumulationDerivation =
				(com.ideasoft.o3.metadata.api.DateAcumulationDerivationSpec) derivedMeasureType.getMeasureDerivation(); 
			return new DerivedMeasureSpec() {
				public String getName() { return measure.getName(); }
				public String getDescription() { return measure.getDescription(); }
				public String getAggregationFunction() { return measure.getAggregationFunction(); }
				public String getCalculate() { return derivedMeasureType.getCalculate(); }
				public MeasureDerivationSpec getMeasureDerivation() {
					return new DateAcumulationDerivationSpec() {
						public String getMeasure() { return dateAcumulationDerivation.getMeasure(); }
						public String getDateDimension() { return dateAcumulationDerivation.getDateDimension(); }
						public String getAcumulationLevel() { return dateAcumulationDerivation.getAcumulationLevel(); }
						public SmoothSpec getSmooth() { return null; }								
					};
				}
				public List<DimensionScopeSpec> getDimensionScopes() {
					List<DimensionScopeSpec> result = new ArrayList<DimensionScopeSpec>();
					int count = 0;
					// Para cada dimension, crear la scope spec.
					for (final StarDimensionSpec dimension : starModel.getDimensions()) {
						final int index = count;
						final boolean isDimensionAssociated = isDimensionAssociated(factTable, dimension);
						DimensionScopeSpec dimensionScopeSpec = new DimensionScopeSpec() {
							public String getDimensionName() { return dimension.getName(); };
							public int getDimensionIndex() { return index; };
							public String getType() {return isDimensionAssociated ? "INAGGR_TYPE" : "UNDEF_TYPE"; };
							public List<String> getLevelScopes() { return levelScopes; };
							
							private List<String> levelScopes = new ArrayList<String>();
						};
						
						if (dimension.getDimensionDefinition() instanceof DateDimensionDefinitionSpec) {
							DateDimensionDefinitionSpec dateDimensionDefinition = (DateDimensionDefinitionSpec) dimension.getDimensionDefinition();
							for (com.ideasoft.o3.metadata.api.DateLevelSpec dateLevel : dateDimensionDefinition.getHierarchies().get(0).getLevels()) {
								dimensionScopeSpec.getLevelScopes().add("AGGREGATED_SCOPE");
							}
						} else {
							TableDimensionDefinitionSpec tableDimensionDefinition = (TableDimensionDefinitionSpec) dimension.getDimensionDefinition();
							for (com.ideasoft.o3.metadata.api.TableLevelSpec tableLevel : tableDimensionDefinition.getHierarchies().get(0).getLevels()) {
								dimensionScopeSpec.getLevelScopes().add("AGGREGATED_SCOPE");
							}
						}
						
						count++;
						result.add(dimensionScopeSpec);
					}
					return result;
				}
			};
		}
	}
	
	/**
	 * 
	 * @return true iff the given dimension is associated to the measure
	 */
	private boolean isDimensionAssociated(FactTableSpec factTable, StarDimensionSpec dimension)
	{
		List<DimensionReferenceSpec> dimSpecs = factTable.getDimensionReferences();
		List<String> names = new ArrayList<String>();
		for (DimensionReferenceSpec drs : dimSpecs) {
			names.add(drs.getDimension());
		}
		return names.contains(dimension.getName());
	}
	
	/**
	 * @param edfMetadata The metadata to be used
	 * @param starModel The star model involved
	 * @param dimension Is the dimension to get the query
	 * @return The source associated to a DateDimensionDefinition
	 */
	private QuerySourceSpec getQuerySource(final Metadata edfMetadata, final StarModelSpec starModel, final StarDimensionSpec dimension) {
		QuerySourceSpec query = new QuerySourceSpec() {
			public String getName() { return dimension.getName(); }
			public String getDescription() { return dimension.getDescription(); }
			public boolean getQualify() { return true; };
			public String getCode() {
				String factTableName = null;
				outerLoop: for (FactTableSpec factTable : starModel.getFactTables()) {
					for (DimensionReferenceSpec reference : factTable.getDimensionReferences()) {
						if (reference.getDimension().equals(dimension.getName())) {
							factTableName = factTable.getTableName();
							break outerLoop;
						}
					}
				}
				String query = "select ";
				query += this.getFields().get(0).getName();
				return query + " from " + factTableName;				
			}
			public List<QuerySourceFieldSpec> getFields() { return fields; }
			
			private List<QuerySourceFieldSpec> fields = new ArrayList<QuerySourceFieldSpec>();
		};
		query.getFields().add(new QuerySourceFieldSpec() {
			public String getName() { return getFactTableFields(starModel, edfMetadata, dimension.getName()).get(0); }
			public String getType() { return "TYPE_DATE"; }				
		});
		return query;
	}
	
	/**
	 * @return The source asociated to a TableDimensionDefinition
	 */
	private QuerySourceSpec getQuerySource(final Metadata edfMetadata, final StarModelSpec starModel,
		final StarDimensionSpec dimension, final TableDimensionDefinitionSpec tableDimensionDefinition) {
		QuerySourceSpec source = new QuerySourceSpec() {
			public String getName() { return dimension.getName(); }
			public String getDescription() { return dimension.getDescription(); }
			public boolean getQualify() { return true; };
			public String getCode() {
				String select = "select ";
				String from = " from " + tableDimensionDefinition.getTableName();
				String whereKey = TABLE_WHERE_CLAUSULE + tableDimensionDefinition.getEdfSchemaName() + "." + tableDimensionDefinition.getTableName();
				String where = context.containsKey(whereKey) ? (" " + context.get(whereKey)) : "";
				for (final com.ideasoft.o3.metadata.api.TableLevelSpec level : tableDimensionDefinition.getHierarchies().get(0).getLevels()) {
					select += (select.equals("select ") ? "" : ", ") + tableDimensionDefinition.getTableName() + "." + level.getKeyAttribute();
					
					if (level.getHelpSchemaName() != null && ! tableDimensionDefinition.getEdfSchemaName().equals(level.getHelpSchemaName())) {
						iLog.error("Different schema names are not supported. !!!!!!!!!");
					}
					if (level.getHelpTableName() != null && ! tableDimensionDefinition.getTableName().equals(level.getHelpTableName())) {
						KeysSequence keysSequence = edfMetadata.getTable(tableDimensionDefinition.getEdfSchemaName(), tableDimensionDefinition.getTableName()).getKeys().getKeysSequence();
						for (ForeignKey foreignKey : keysSequence.getForeignKey()) {
							String referencedSchemaName = foreignKey.getIndex().substring(0, foreignKey.getIndex().indexOf('.'));
							String referencedTableName = foreignKey.getIndex().substring(foreignKey.getIndex().indexOf('.') + 1, foreignKey.getIndex().lastIndexOf('.'));
							if ((level.getHelpSchemaName() == null || level.getHelpSchemaName().equals(referencedSchemaName)) && level.getHelpTableName().equals(referencedTableName)) {
								FieldName[] keyFieldNames = edfMetadata.getTable(level.getHelpSchemaName() != null ? level.getHelpSchemaName() : tableDimensionDefinition.getEdfSchemaName(),
									level.getHelpTableName()).getKeys().getPrimaryKey().getFieldName();
								int index = 0;

								// Solo tiene que haber un campo en la FK para poder saber cual es ID
								// Tiene que haber un solo nivel, porque sino hay que hacer el join con la jerarquia
								if (foreignKey.getFieldName().length == 1 &&
									tableDimensionDefinition.getHierarchies().get(0).getLevels().size() == 1) {
									// Antes solo se puso el ID
									select = "select " + level.getHelpTableName() + "." + keyFieldNames[0].getName();
									// Se esta cambiando la tabla
									from = " from " + level.getHelpTableName();
									// Notar que no es necesario el where
								} else {
									// Se usa el mismo ID de antes
									// Se agrega la tabla del nivel
									from += ", " + level.getHelpTableName();
									// Se genera el join
									for (FieldName fieldName : foreignKey.getFieldName()) {
										where += (where.equals("") ? " where " : " and ") +
											tableDimensionDefinition.getTableName() + "." + fieldName.getName() + " = " +
											level.getHelpTableName() + "." + keyFieldNames[index].getName();
										index++;
									}
								}
								break;
							}
						}
					}
					
					if (level.getLabelAttribute() != null) {
						select += ", " + (level.getHelpTableName() != null ? level.getHelpTableName() : tableDimensionDefinition.getTableName()) +
							"." + level.getLabelAttribute() + " as " + level.getKeyAttribute() + "_label";
					}
					if (level.getLongDescriptionAttribute() != null) {
						select += ", " + (level.getHelpTableName() != null ? level.getHelpTableName() : tableDimensionDefinition.getTableName()) +
							"." + level.getLongDescriptionAttribute() + " as " + level.getKeyAttribute() + "_long_label";
					}
					if (level.getDescriptionAttribute() != null) {
						select += ", " + (level.getHelpTableName() != null ? level.getHelpTableName() : tableDimensionDefinition.getTableName()) +
							"." + level.getDescriptionAttribute() + " as " + level.getKeyAttribute() + "_description";
					}
				}
				
				return select + from + where;
			}
			public List<QuerySourceFieldSpec> getFields() { return fields; }
			
			private List<QuerySourceFieldSpec> fields = new ArrayList<QuerySourceFieldSpec>();
		};
		
		int index = 0;
		for (final com.ideasoft.o3.metadata.api.TableLevelSpec level : tableDimensionDefinition.getHierarchies().get(0).getLevels()) {
			final int count = index;
			source.getFields().add(new QuerySourceFieldSpec() {
				public String getName() { return getFactTableFields(starModel, edfMetadata, dimension.getName()).get(count); }
				public String getType() throws MetadataException {
					String edfFieldName = tableDimensionDefinition.getEdfSchemaName() + "." +
						tableDimensionDefinition.getTableName() + "." + level.getKeyAttribute();
					
					if (edfMetadata == null) {
						throw new EdfMetadataNotFoundException("The EDF metadata used in the query generation was not found.");
					}
					DataField edfField = edfMetadata.getFieldbyFieldName(edfFieldName);
					if (edfField == null) {
						throw new EdfFieldNotFoundException("The EDF field " + edfFieldName + " was not found in the metadata", edfMetadata, edfFieldName);
					}
					return getO3TypeFromEDFType(edfField.getDatatype().getTypeName());
				}				
			});
			if (level.getLabelAttribute() != null) {
				source.getFields().add(new QuerySourceFieldSpec() {
					public String getName() { return level.getKeyAttribute() + "_label"; }
					public String getType() { return "TYPE_STRING"; }				
				});
			}
			if (level.getLongDescriptionAttribute() != null) {
				source.getFields().add(new QuerySourceFieldSpec() {
					public String getName() { return level.getKeyAttribute() + "_long_label"; }
					public String getType() { return "TYPE_STRING"; }				
				});
			}
			if (level.getDescriptionAttribute() != null) {
				source.getFields().add(new QuerySourceFieldSpec() {
					public String getName() { return level.getKeyAttribute() + "_description"; }
					public String getType() { return "TYPE_STRING"; }				
				});
			}
			
			index ++;
		}
		
		return source;
	}
	
	/**
	 * @return The source asociated to a fact table
	 */
	private QuerySourceSpec getQuerySource(final Metadata edfMetadata, final StarModelSpec starModel,
		final FactTableSpec factTable) throws EdfMetadataNotFoundException, EdfFieldNotFoundException {		
		QuerySourceSpec source = new QuerySourceSpec() {
			public String getName() { return factTable.getName(); }
			public String getDescription() { return factTable.getDescription(); }
			public boolean getQualify() { return false/*factTable.getQualify()*/; }
			public String getCode() {
				String query = "select ";
				// select [referencias a dimensiones], [medidas] from [tabla de hechos]
				for (DimensionReferenceSpec reference : factTable.getDimensionReferences()) {
					if (reference instanceof StarDimensionReferenceSpec) {
						for (final String field : ((StarDimensionReferenceSpec) reference).getFactAttributes()) {
							query += (query.equals("select ") ? "" : ", ") + field;
						}
					}					
				}
				for (final com.ideasoft.o3.metadata.api.MeasureSpec measure : factTable.getMeasures()) {					
					if (measure.getType() instanceof BasicMeasureTypeSpec) {
						// Derived measures has not included in the factTable source
						query += (query.equals("select ") ? "" : ", ") + ((BasicMeasureTypeSpec) measure.getType()).getMeasureAttribute();
					}
				}
				return query + " from " + factTable.getTableName();				
			}

			public List<QuerySourceFieldSpec> getFields() { return fields; }
			
			private List<QuerySourceFieldSpec> fields = new ArrayList<QuerySourceFieldSpec>();			
		};
		
		// Recorro las claves foraneas a dimensiones
		for (final DimensionReferenceSpec reference : factTable.getDimensionReferences()) {
			if (reference instanceof StarDimensionReferenceSpec) {			
				// Las otras (ForeignKeyDimensionReferenceSpec) no tienen los atributos, hay que buscarlos en la Metadata relacional
				for (final String field : ((StarDimensionReferenceSpec) reference).getFactAttributes()) {
					source.getFields().add(new QuerySourceFieldSpec() {
						public String getName() { return field; }
						public String getType() throws MetadataException {
							String edfFieldName = factTable.getEdfSchemaName() + "." + factTable.getTableName() + "." + field;
							if (edfMetadata == null) {
								throw new EdfMetadataNotFoundException("The EDF metadata used in the query generation was not found.");
							}							
							DataField edfField = edfMetadata.getFieldbyFieldName(edfFieldName);
							if (edfField == null) {
								throw new EdfFieldNotFoundException("The EDF field " + edfFieldName + " was not found in the metadata", edfMetadata, edfFieldName);
							}
							return getO3TypeFromEDFType(edfField.getDatatype().getTypeName());
						}
					});
				}
			}			
		}
		
		// Recorro las medidas 
		for (final com.ideasoft.o3.metadata.api.MeasureSpec measure : factTable.getMeasures()) {
			if (measure.getType() instanceof BasicMeasureTypeSpec) {
				// Derived measures has not included in the factTable source
				source.getFields().add(new QuerySourceFieldSpec() {
					public String getName() { return ((BasicMeasureTypeSpec) measure.getType()).getMeasureAttribute(); }				
					public String getType() throws MetadataException {				
						String edfFieldName = factTable.getEdfSchemaName() + "." + factTable.getTableName() + "." + ((BasicMeasureTypeSpec) measure.getType()).getMeasureAttribute();
						if (edfMetadata == null) {
							throw new EdfMetadataNotFoundException("The EDF metadata used in the query generation was not found.");
						}
						DataField edfField = edfMetadata.getFieldbyFieldName(edfFieldName);
						if (edfField == null) {
							throw new EdfFieldNotFoundException("The EDF field " + edfFieldName + " was not found in the metadata", edfMetadata, edfFieldName);
						}
						return getO3TypeFromEDFType(edfField.getDatatype().getTypeName());
					}
				});
			}
		}
		
		return source;
	}
	
	/**
	 * @return The fact table fields used to refer a dimension
	 */
	private List<String> getFactTableFields(StarModelSpec starModel, Metadata edfMetadata, String dimensionName) {
		for (FactTableSpec factTable : starModel.getFactTables()) {
			for (DimensionReferenceSpec reference : factTable.getDimensionReferences()) {
				if (reference.getDimension().equals(dimensionName)) {
					if (reference instanceof StarDimensionReferenceSpec) {
						return ((StarDimensionReferenceSpec) reference).getFactAttributes();
					} else if (reference instanceof ForeignKeyDimensionReferenceSpec) {
						/* ((ForeignKeyDimensionReferenceSpec) reference).getForeignKey() */
						//TODO: Suerte, hay que saltar a la metadata de EDF a buscar que campos se usan para apuntar
						return null;
					}
				}
			}
		}
		
		return null;
	}
	
	private void loadProperties(String propertiesFile) {
		if (propertiesFile == null) {
			return;
		}
		Properties properties = new Properties();
		try {
			InputStream is = new FileInputStream(propertiesFile);
			properties.load(is);
			is.close();
		} catch (FileNotFoundException fnfe) {
			iLog.error("Cannot find properties file " + propertiesFile);
			return;
		} catch (IOException ioe) {
			iLog.error("Cannot read properties file " + propertiesFile);
			return;
		}
		String p = (String)properties.get("measuresMode");
		if (p != null) {
			if (isValidModeValue(p)) {
				measuresMode = p;
			} else {
				iLog.error("Invalid value '" + p + "' for property 'measuresMode'.");
			}
		}
		p = (String)properties.get("dateMeasuresMode");
		if (p != null) {
			if (isValidModeValue(p)) {
				dateMeasuresMode = p;
			} else {
				iLog.error("Invalid value '" + p + "' for property 'dateMeasuresMode'.");
			}
		}
	}
	
	private boolean isValidModeValue(String value) {
		return value.equals(MEASURES_MODE_DEFAULT) || value.equals(MEASURES_MODE_APPEND);
	}
	
	private String getO3TypeFromEDFType(String edfType) {
		if (edfType.equals("int")) {
			return "TYPE_INTEGER";
		} else if (edfType.equals("decimal")) {
			return "TYPE_DOUBLE";
		} else if (edfType.equals("long")) {
			return "TYPE_STRING";
		}
		
		return "TYPE_" + edfType.toUpperCase();
	}

	private Map<String, Object> context = new HashMap<String, Object>();
	
	public static final String TABLE_WHERE_CLAUSULE = "WhereClausuleFor:";
	
	private static final String MEASURES_MODE_DEFAULT = "DEFAULT_MODE";
	private static final String MEASURES_MODE_APPEND = "APPEND_MODE";
	private String measuresMode = MEASURES_MODE_DEFAULT;
	private String dateMeasuresMode = MEASURES_MODE_APPEND;

	protected static final String DEFAULT_CONN_USER_VALUE = "sa";
	protected static final String DEFAULT_CONN_PASSWD_VALUE = "";
	protected static final String DEFAULT_CONN_DRIVER_VALUE = "org.hsqldb.jdbcDriver";
	protected static final String DEFAULT_CONN_PROTOCOL_VALUE = "hsqldb";
	protected static final String DEFAULT_CONN_PARAMETERS_VALUE = "hsql://localhost:1701";

	protected static final String DEFAULT_CONN_USER = Context.getProperty("o3.mdl.generator.db.user", DEFAULT_CONN_USER_VALUE);
	protected static final String DEFAULT_CONN_PASSWD = Context.getProperty("o3.mdl.generator.db.passwd", DEFAULT_CONN_PASSWD_VALUE);
	protected static final String DEFAULT_CONN_DRIVER = Context.getProperty("o3.mdl.generator.db.driver", DEFAULT_CONN_DRIVER_VALUE);
	protected static final String DEFAULT_CONN_PROTOCOL = Context.getProperty("o3.mdl.generator.db.protocol", DEFAULT_CONN_PROTOCOL_VALUE);
	protected static final String DEFAULT_CONN_PARAMETERS = Context.getProperty("o3.mdl.generator.db.parameters", DEFAULT_CONN_PARAMETERS_VALUE);
	
	private static ILog iLog = ILogFactory.getILog(ApiFactory.class);
}
