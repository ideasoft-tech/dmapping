// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl.api;

/**
 * This is the two measures derivation specification
 * @author ignacio
 * @version $Revision:$
 */
public interface TwoMeasureDerivationSpec extends MeasureDerivationSpec {

	public String getMeasure1();
	public String getOperator();
	public String getMeasure2();
	
}