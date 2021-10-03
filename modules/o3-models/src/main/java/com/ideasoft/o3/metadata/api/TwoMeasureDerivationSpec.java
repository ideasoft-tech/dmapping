// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TwoMeasureDerivationSpec.java,v 1.1 2005/12/05 20:19:15 ignacio Exp $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This is the two measures derivation specification
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface TwoMeasureDerivationSpec extends MeasureDerivationSpec {

	/**
	 * @return The name of the first measure
	 */
	public String getMeasure1();
	
	/**
	 * @return The operator to be used between measures
	 */
	public String getOperator();
	
	/**
	 * @return The name of the second measure
	 */
	public String getMeasure2();
	
}
