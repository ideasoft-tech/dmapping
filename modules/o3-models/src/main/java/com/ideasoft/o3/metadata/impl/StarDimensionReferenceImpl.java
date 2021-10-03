//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: StarDimensionReferenceImpl.java,v 1.2 2005/10/05 14:19:41 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideasoft.o3.metadata.api.StarDimensionReferenceSpec;

/**
 * @author ignacio
 * @version $Revision: 1.2 $
 */
public class StarDimensionReferenceImpl extends DimensionReferenceImpl implements StarDimensionReferenceSpec {

	public StarDimensionReferenceImpl() {		
	}
	
	public StarDimensionReferenceImpl(String dimension) {
		super(dimension);
	}
	
	public List<String> getFactAttributes() {
		return factAttributes;
	}
	public void setFactAttributes(List<String> factAttributes) {
		this.factAttributes = factAttributes;
	}
	
	@Override
	public boolean equals(Object obj) {
		StarDimensionReferenceImpl other = (StarDimensionReferenceImpl) obj;
		
		return super.equals(obj) &&
			equals(factAttributes, other.factAttributes);
	}
	
	private List<String> factAttributes = new ArrayList<String>();
	
}
