//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: StarModelSpec.java,v 1.2 2005/10/05 15:24:59 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

import java.util.List;

/**
 * This interface defines the star model specification.<br>
 * An star model is composed of cubes, each of those is composed of fact tables and dimensions
 * @author ignacio
 * @version $Revision: 1.2 $
 */
public interface StarModelSpec {

	/**
	 * @return The name of the star model
	 */
	public String getName();
	
	/**
	 * @return The optional description
	 */
	public String getDescription();	
	
	/**
	 * @return The set of star dimensions
	 */
	public List<StarDimensionSpec> getDimensions();
	
	/**
	 * @return The set of fact tables
	 */
	public List<FactTableSpec> getFactTables();
	
	/**
	 * @return The set of the cubes
	 */
	public List<CubeSpec> getCubes();

}
