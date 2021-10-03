//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DateHierarchySpec.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

import java.util.List;

/**
 * This is the date hierarchy specification, a set of date levels
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface DateHierarchySpec {

	/**
	 * @return The levels of the date hierarchy
	 */
	public List<DateLevelSpec> getLevels();
	
}
