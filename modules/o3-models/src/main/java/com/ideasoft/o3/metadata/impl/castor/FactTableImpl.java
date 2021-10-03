//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: FactTableImpl.java,v 1.2 2005/11/29 12:57:50 npalombo Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl.castor;

import java.util.ArrayList;

import com.ideasoft.o3.metadata.api.DimensionReferenceSpec;
import com.ideasoft.o3.metadata.impl.ForeignKeyDimensionReferenceImpl;

/**
 * This is the castor customization
 * @author ignacio
 * @version $Revision: 1.2 $
 * @castor.class
 */
public class FactTableImpl extends com.ideasoft.o3.metadata.impl.FactTableImpl {
	
	/**
	 * @castor.field set-method="setArrayListMeasures" type="com.ideasoft.o3.metadata.impl.castor.MeasureImpl" collection="arraylist"
	 * @castor.field-xml name="measure" node="element"
	 */
	public ArrayList getArrayListMeasures() {
		return (ArrayList) getMeasures();
	}
	public void setArrayListMeasures(ArrayList measures) {
		setMeasures(measures);
	}
	
	/**
	 * @castor.field set-method="setArrayListForeignKeyDimensionReferences" type="com.ideasoft.o3.metadata.impl.ForeignKeyDimensionReferenceImpl" collection="arraylist"
	 * @castor.field-xml name="foreignKeyDimensionReference" node="element"
	 */
	public ArrayList getArrayListForeignKeyDimensionReferences() {
		return (ArrayList) getDimensionReferences();
	}
	public void setArrayListForeignKeyDimensionReferences(ArrayList dimensionReferences) {
		// Clean olds
		for (DimensionReferenceSpec dimensionReference : getDimensionReferences()) {
			if (dimensionReference instanceof ForeignKeyDimensionReferenceImpl) {
				getDimensionReferences().remove(dimensionReference);
			}
		}
		
		getDimensionReferences().addAll(dimensionReferences);
	}
	
	/**
	 * @castor.field set-method="setArrayListStarDimensionReferences" type="com.ideasoft.o3.metadata.impl.castor.StarDimensionReferenceImpl" collection="arraylist"
	 * @castor.field-xml name="starDimensionReference" node="element"
	 */
	public ArrayList getArrayListStarDimensionReferences() {
		return (ArrayList) getDimensionReferences();
	}
	public void setArrayListStarDimensionReferences(ArrayList dimensionReferences) {
		// Clean olds
		for (DimensionReferenceSpec dimensionReference : getDimensionReferences()) {
			if (dimensionReference instanceof StarDimensionReferenceImpl) {
				getDimensionReferences().remove(dimensionReference);
			}
		}
		
		getDimensionReferences().addAll(dimensionReferences);
	}

}
