//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: LevelSpec.java,v 1.2 2005/10/28 14:45:28 marcelo Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This is the base interface for al level definitions
 * @author ignacio
 * @version $Revision: 1.2 $
 */
public interface LevelSpec {
	
	/**
	 * @return The name of the level
	 */
	public String getName();
	
	/**
	 * @return The description of the level
	 */
	public String getDescription();
	
	/**
	 * @return true if this is a unique level
	 */
	public boolean getUniqueLevel();

}
