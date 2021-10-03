//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DerivedMeasureSpec.java,v 1.1 2005/12/05 20:24:53 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl.api;

/**
 * This is the derived measure specification
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface DerivedMeasureSpec extends MeasureSpec {
	
	/**
	 * @return When the derivated measure is calculated
	 * @see #BEFORE_CALCULATE
	 * @see #AFTER_CALCULATE
	 */
	public String getCalculate();
	public MeasureDerivationSpec getMeasureDerivation();
	
	public static final String BEFORE_CALCULATE = "before";
	public static final String AFTER_CALCULATE = "after";	

}
