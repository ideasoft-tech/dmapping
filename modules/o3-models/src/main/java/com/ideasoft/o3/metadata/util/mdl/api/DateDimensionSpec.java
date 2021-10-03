//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: O3Generator.java,v 1.3 2005/09/27 22:03:52 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl.api;

import java.util.List;

/**
 * @author ignacio
 * @version $Revision:$
 */
public interface DateDimensionSpec extends DimensionSpec {
	
	public String getDateField();
	public List<DateLevelSpec> getLevels();

}
