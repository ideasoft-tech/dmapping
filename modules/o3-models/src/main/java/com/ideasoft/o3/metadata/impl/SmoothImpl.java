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

import com.ideasoft.o3.metadata.api.SmoothSpec;

/**
 * @author ignacio
 * @version $Revision:$
 * @castor.class
 */
public class SmoothImpl implements SmoothSpec {
	
	/**
	 * @castor.field set-method="setPeriods"
	 * @castor.field-xml name="periods" node="attribute"
	 */
	public int getPeriods() {
		return periods;
	}
	public void setPeriods(int periods) {
		this.periods = periods;
	}
	
	@Override
	public int hashCode() {
		return periods;
	}
	
	@Override
	public boolean equals(Object obj) {
		SmoothImpl other = (SmoothImpl) obj;
		
		return periods == other.periods;
	}
	
	private int periods;

}
