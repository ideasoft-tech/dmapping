// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: SmoothSpec.java,v 1.1 2005/12/05 20:19:15 ignacio Exp $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This is the smooth of a date derivation
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface SmoothSpec {
	
	/**
	 * @return The number of periods to be used
	 */
	public int getPeriods();

}
