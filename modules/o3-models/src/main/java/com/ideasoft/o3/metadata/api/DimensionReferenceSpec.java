//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DimensionReferenceSpec.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This interface represents the base for dimension references
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface DimensionReferenceSpec {	

	/**
	 * @return The name of the dimension referenced
	 */
	public String getDimension();
	
}
