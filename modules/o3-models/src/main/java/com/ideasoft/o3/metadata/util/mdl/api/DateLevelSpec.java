//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: DateLevelSpec.java,v 1.1 2005/10/05 20:33:58 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl.api;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface DateLevelSpec extends LevelSpec {
	
	public String getGranularity();
	
	public static final String YEAR_GRANULARITY = "LEVEL_YEAR";
	public static final String SEMESTER_GRANULARITY = "LEVEL_SEMESTER";
	public static final String TRIMESTER_GRANULARITY = "LEVEL_QUARTER";
	public static final String MONTH_GRANULARITY = "LEVEL_MONTH";
	public static final String WEEK_GRANULARITY = "LEVEL_WEEK";
	public static final String DAY_GRANULARITY = "LEVEL_DAY";

}
