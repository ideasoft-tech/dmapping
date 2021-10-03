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
 * This is the tow measure derivation specification
 * @author ignacio
 * @version $Revision:$
 */
public interface DateAcumulationDerivationSpec extends MeasureDerivationSpec {

	public String getMeasure();
	public String getDateDimension();	
	public String getAcumulationLevel();
	public SmoothSpec getSmooth();
	
}
