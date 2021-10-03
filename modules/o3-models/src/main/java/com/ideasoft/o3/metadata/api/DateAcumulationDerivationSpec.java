// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DateAcumulationDerivationSpec.java,v 1.1 2005/12/05 20:19:15 ignacio Exp $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This is the date accumulation measure derivation specification,
 * that is a kind of measure derivation<br>
 * With this derivation the masure is dinamically accuulated over the time
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface DateAcumulationDerivationSpec extends MeasureDerivationSpec {

	/**
	 * @return The name of the base measure
	 */
	public String getMeasure();
	
	/**
	 * @return The name of the date dimension to use
	 */
	public String getDateDimension();
	
	/**
	 * @return The accumulation level, where the cut point is produced
	 */
	public String getAcumulationLevel();
	
	/**
	 * @return The optional smooth (if there is null there is no smooth)
	 */
	public SmoothSpec getSmooth();
	
}
