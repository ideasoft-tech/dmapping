//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: StarDimensionSpec.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This is a star dimension, note that its no a common dimension definition 
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface StarDimensionSpec {
	
	/**
	 * @return The name of the dimension
	 */
	public String getName();
	
	/**
	 * @return The optional description of the dimension
	 */
	public String getDescription();
	
	/**
	 * @return The definition of the dimension: date, normal, etc.
	 */
	public DimensionDefinitionSpec getDimensionDefinition();

}
