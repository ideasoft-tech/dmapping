//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DimensionReferenceImpl.java,v 1.2 2005/10/05 14:19:41 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.DimensionReferenceSpec;

/**
 * @author ignacio
 * @version $Revision: 1.2 $
 */
public abstract class DimensionReferenceImpl implements DimensionReferenceSpec {	

	public DimensionReferenceImpl() {		
	}
	
	public DimensionReferenceImpl(String dimension) {
		this.dimension = dimension;
	}
	
	/**
	 * @castor.field set-method="setDimension"
	 * @castor.field-xml name="dimension" node="attribute"
	 */
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	@Override
	public int hashCode() {
		return dimension.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		DimensionReferenceImpl other = (DimensionReferenceImpl) obj;
		
		return equals(dimension, other.dimension);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String dimension;
	
}
