//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: CubeSpec.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

import java.util.List;

/**
 * This interface represents the cube specification.<br>
 * A cube is represented by a set of Fact Tables, and obviously a name and description.<br>
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface CubeSpec {
	
	/**
	 * @return The simulation name
	 */
	public String getName();
	
	/**
	 * @return The optional simulation description
	 */
	public String getDescription();
	
	/**
	 * @return A list of all Fact Tables involved in the cube
	 */
	public List<String> getFactTables();

}
