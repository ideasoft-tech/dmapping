//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: StarDimensionReferenceSpec.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

import java.util.List;

/**
 * This is a reference to a star dimension
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface StarDimensionReferenceSpec extends DimensionReferenceSpec {
	
	/**
	 * @return A list of the fact attribute names
	 */
	public List<String> getFactAttributes();

}
