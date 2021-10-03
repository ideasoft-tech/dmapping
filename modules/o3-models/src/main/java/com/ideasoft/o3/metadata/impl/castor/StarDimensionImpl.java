//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ParameterScopeImpl.java,v 1.2 2005/09/21 20:47:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl.castor;

/**
 * This is the castor customization of the cube implementation
 * @author ignacio
 * @version $Revision:$
 * @castor.class
 */
public class StarDimensionImpl extends com.ideasoft.o3.metadata.impl.StarDimensionImpl {
	
	/**
	 * @castor.field set-method="setTableDimensionDefinition" type="com.ideasoft.o3.metadata.impl.castor.TableDimensionDefinitionImpl"
	 * @castor.field-xml name="tableDimensionDefinition" node="element"
	 */
	public TableDimensionDefinitionImpl getTableDimensionDefinition() {
		return getDimensionDefinition() instanceof TableDimensionDefinitionImpl ? (TableDimensionDefinitionImpl) getDimensionDefinition() : null;
	}
	public void setTableDimensionDefinition(TableDimensionDefinitionImpl dimensionDefinition) {
		setDimensionDefinition(dimensionDefinition);
	}
	
	/**
	 * @castor.field set-method="setDateDimensionDefinition" type="com.ideasoft.o3.metadata.impl.castor.DateDimensionDefinitionImpl"
	 * @castor.field-xml name="dateDimensionDefinition" node="element"
	 */
	public DateDimensionDefinitionImpl getDateDimensionDefinition() {
		return getDimensionDefinition() instanceof DateDimensionDefinitionImpl ? (DateDimensionDefinitionImpl) getDimensionDefinition() : null;
	}
	public void setDateDimensionDefinition(DateDimensionDefinitionImpl dimensionDefinition) {
		setDimensionDefinition(dimensionDefinition);
	}

}
