//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DateLevelSpec.java,v 1.3 2005/12/27 15:51:47 npalombo Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This is a level used in a date hierarchy definition
 * @author ignacio
 * @version $Revision: 1.3 $
 */
public interface DateLevelSpec extends LevelSpec {
	
	/**
	 * Note: This is not an enumeration because the it has problems with the XML persistence and velocity
	 * @see #YEAR_GRANULARITY
	 * @see #SEMESTER_GRANULARITY
	 * @see #QUARTER_GRANULARITY
	 * @see #MONTH_GRANULARITY
	 * @see #WEEK_GRANULARITY
	 * @see #DAY_GRANULARITY
	 * @return The granularity of the level
	 */
	public String getGranularity();
	
	/**
	 * Those are the possible granularities for the date level
	 */
	public static final String YEAR_GRANULARITY = "LEVEL_YEAR",
		SEMESTER_GRANULARITY = "LEVEL_SEMESTER",
		QUARTER_GRANULARITY = "LEVEL_QUARTER",
		MONTH_GRANULARITY = "LEVEL_MONTH",
		WEEK_GRANULARITY = "LEVEL_WEEK",
		DAY_GRANULARITY = "LEVEL_DAY";

}
