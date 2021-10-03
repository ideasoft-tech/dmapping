//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TableDimensionDefinitionImpl.java,v 1.3 2005/10/05 20:08:46 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideasoft.o3.metadata.api.TableDimensionDefinitionSpec;
import com.ideasoft.o3.metadata.api.TableHierarchySpec;

/**
 * @author ignacio
 * @version $Revision: 1.3 $
 */
public class TableDimensionDefinitionImpl extends DimensionDefinitionImpl implements TableDimensionDefinitionSpec {
	
	public TableDimensionDefinitionImpl() {		
	}
	
	public TableDimensionDefinitionImpl(String tableName, String edfSchemaName) {
		this.tableName = tableName;
		this.edfSchemaName = edfSchemaName;
	}
	
	/**
	 * @castor.field set-method="setTableName"
	 * @castor.field-xml name="tableName" node="attribute"
	 */
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * @castor.field set-method="setEdfSchemaName"
	 * @castor.field-xml name="edfSchemaName" node="attribute"
	 */
	public String getEdfSchemaName() {
		return edfSchemaName;
	}
	public void setEdfSchemaName(String edfSchemaName) {
		this.edfSchemaName = edfSchemaName;
	}
	
	/**
	 * @castor.field set-method="setUniqueLevel"
	 * @castor.field-xml name="uniqueLevel" node="attribute"
	 */
	public String getUniqueLevel() {
		return uniqueLevel;
	}
	public void setUniqueLevel(String uniqueLevel) {
		this.uniqueLevel = uniqueLevel;
	}
	
	public List<TableHierarchySpec> getHierarchies() {
		return hierarchies;
	}
	public void setHierarchies(List<TableHierarchySpec> hierarchies) {
		this.hierarchies = hierarchies;
	}
	
	@Override
	public int hashCode() {
		return hierarchies.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		TableDimensionDefinitionImpl other = (TableDimensionDefinitionImpl) obj;
		
		return equals(tableName, other.tableName) &&
			equals(edfSchemaName, other.edfSchemaName) &&
			equals(uniqueLevel, other.uniqueLevel) &&
			equals(hierarchies, other.hierarchies);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String tableName;
	private String edfSchemaName;
	private String uniqueLevel;
	private List<TableHierarchySpec> hierarchies = new ArrayList<TableHierarchySpec>();

}
