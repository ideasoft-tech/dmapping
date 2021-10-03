//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: MeasureSpec.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.BasicMeasureTypeSpec;

/**
 * @author ignacio
 * @version $Revision:$
 * @castor.class
 */
public class BasicMeasureTypeImpl implements BasicMeasureTypeSpec {
	
	public BasicMeasureTypeImpl() {		
	}
	
	public BasicMeasureTypeImpl(String measureAttribute) {
		this.measureAttribute = measureAttribute;
	}
	
	/**
	 * @castor.field set-method="setMeasureAttribute"
	 * @castor.field-xml name="measureAttribute" node="attribute"
	 */
	public String getMeasureAttribute() {
		return measureAttribute;
	}
	public void setMeasureAttribute(String measureAttribute) {
		this.measureAttribute = measureAttribute;
	}
	
	@Override
	public int hashCode() {
		return measureAttribute.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		BasicMeasureTypeImpl other = (BasicMeasureTypeImpl) obj;
		
		return equals(measureAttribute, other.measureAttribute);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String measureAttribute;

}
