// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl.castor;

import com.ideasoft.o3.metadata.api.MeasureSpec;

import com.ideasoft.o3.metadata.impl.BasicMeasureTypeImpl;

/**
 * @author ignacio
 * @version $Revision:$
 * @castor.class
 */
public class MeasureImpl extends com.ideasoft.o3.metadata.impl.MeasureImpl implements MeasureSpec {
	
	/**
	 * @castor.field set-method="setBasicMeasureTypeImpl"
	 * @castor.field-xml name="basic" node="element"
	 */
	public BasicMeasureTypeImpl getBasicMeasureTypeImpl() {
		return getType() instanceof BasicMeasureTypeImpl ? (BasicMeasureTypeImpl) getType() : null;
	}
	public void setBasicMeasureTypeImpl(BasicMeasureTypeImpl basicMeasureTypeImpl) {
		setType(basicMeasureTypeImpl);
	}
	
	/**
	 * @castor.field set-method="setDerivedMeasureTypeImpl"
	 * @castor.field-xml name="derived" node="element"
	 */
	public DerivedMeasureTypeImpl getDerivedMeasureTypeImpl() {
		return getType() instanceof DerivedMeasureTypeImpl ? (DerivedMeasureTypeImpl) getType() : null;
	}
	public void setDerivedMeasureTypeImpl(DerivedMeasureTypeImpl derivedMeasureTypeImpl) {
		setType(derivedMeasureTypeImpl);
	}

}
