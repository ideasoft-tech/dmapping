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

import com.ideasoft.o3.metadata.api.DateAcumulationDerivationSpec;

import com.ideasoft.o3.metadata.impl.SmoothImpl;

/**
 * @author ignacio
 * @version $Revision:$
 * @castor.class
 */
public class DateAcumulationDerivationImpl extends com.ideasoft.o3.metadata.impl.DateAcumulationDerivationImpl implements DateAcumulationDerivationSpec {
	
	/**
	 * @castor.field set-method="setSmoothImpl"
	 * @castor.field-xml name="smooth" node="element"
	 */
	public SmoothImpl getSmoothImpl() {
		return (SmoothImpl) super.getSmooth();
	}
	public void setSmoothImpl(SmoothImpl smooth) {
		super.setSmooth(smooth);
	}

}
