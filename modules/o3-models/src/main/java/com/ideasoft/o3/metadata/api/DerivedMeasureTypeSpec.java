//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DerivedMeasureTypeSpec.java,v 1.2 2005/12/06 19:34:51 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This is the derived measure type opposite to basic measure type
 * @author ignacio
 * @version $Revision: 1.2 $
 */
public interface DerivedMeasureTypeSpec extends MeasureTypeSpec {
	
	/**
	 * @return When the derivated measure is calculated
	 * @see #BEFORE_CALCULATE
	 * @see #AFTER_CALCULATE
	 */
	public String getCalculate();
	
	/**
	 * @return The measure derivation (expression, two mesaures, date accumulation, etc.)
	 */
	public MeasureDerivationSpec getMeasureDerivation();
	
	/**
	 * Those are the possible values for derivation time
	 */
	public static final String BEFORE_CALCULATE = "before", AFTER_CALCULATE = "after";	

}
