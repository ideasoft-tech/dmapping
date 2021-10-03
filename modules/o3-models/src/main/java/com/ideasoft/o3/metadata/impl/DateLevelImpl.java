//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DateLevelImpl.java,v 1.2 2005/10/05 14:19:41 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.DateLevelSpec;

/**
 * @author ignacio
 * @version $Revision: 1.2 $
 * @castor.class
 */
public class DateLevelImpl extends LevelImpl implements DateLevelSpec {
	
	public DateLevelImpl() {		
	}
	
	public DateLevelImpl(String name, String granularity) {
		super(name);
		
		this.granularity = granularity;
	}
	
	/**
	 * @castor.field set-method="setGranularity"
	 * @castor.field-xml name="granularity" node="attribute"
	 */
	public String getGranularity() {
		return granularity;
	}
	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}
	
	@Override
	public boolean equals(Object obj) {
		DateLevelImpl other = (DateLevelImpl) obj;
		
		return super.equals(obj) && 		
			equals(granularity, other.granularity);
	}
	
	private String granularity;

}
