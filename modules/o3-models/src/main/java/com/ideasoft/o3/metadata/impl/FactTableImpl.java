//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: FactTableImpl.java,v 1.5 2005/10/17 16:57:10 marcelo Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideasoft.o3.metadata.api.DimensionReferenceSpec;
import com.ideasoft.o3.metadata.api.FactTableSpec;
import com.ideasoft.o3.metadata.api.MeasureSpec;

/**
 * @author ignacio
 * @version $Revision: 1.5 $
 */
public class FactTableImpl implements FactTableSpec {
	
	public FactTableImpl() {
	}
	
	public FactTableImpl(String name, String tableName, String edfSchemaName) {
		this.name = name;
		this.tableName = tableName;
		this.edfSchemaName = edfSchemaName;
	}
	
	/**
	 * @castor.field set-method="setName"
	 * @castor.field-xml name="name" node="attribute"
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @castor.field set-method="setDescription"
	 * @castor.field-xml name="description" node="element"
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

	public List<MeasureSpec> getMeasures() {
		return measures;
	}
	public void setMeasures(List<MeasureSpec> measures) {
		this.measures = measures;
	}
	
	public List<DimensionReferenceSpec> getDimensionReferences() {
		return dimensionReferences;
	}
	public void setDimensionReferences(List<DimensionReferenceSpec> dimensionReferences) {
		this.dimensionReferences = dimensionReferences;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		FactTableImpl other = (FactTableImpl) obj;
		
		return equals(name, other.name) &&
			equals(description, other.description) &&
			equals(tableName, other.tableName) &&
			equals(edfSchemaName, other.edfSchemaName) &&
			equals(measures, other.measures) &&
			other.qualify == qualify &&
			equals(dimensionReferences, other.dimensionReferences);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String name;
	private String description;
	private String tableName;
	private String edfSchemaName;
	private boolean qualify;
	private List<MeasureSpec> measures = new ArrayList<MeasureSpec>();
	private List<DimensionReferenceSpec> dimensionReferences = new ArrayList<DimensionReferenceSpec>();

}
