//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: MeasureSpec.java,v 1.3 2005/12/06 19:34:18 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl.api;

import java.util.List;

/**
 * @author ignacio
 * @version $Revision: 1.3 $
 */
public interface MeasureSpec {
	
	public String getName();
	public String getDescription();
	
	/**
	 * @return The aggregation function to be used
	 * @see #SUM_AGGREGATION_FUNCTION
	 * @see #MAX_AGGREGATION_FUNCTION
	 * @see #MIN_AGGREGATION_FUNCTION
	 * @see #AVG_AGGREGATION_FUNCTION
	 * @see #COUNT_AGGREGATION_FUNCTION
	 */
	public String getAggregationFunction();
	
	public List<DimensionScopeSpec> getDimensionScopes();
	
	public static final String SUM_AGGREGATION_FUNCTION = "SUM";
	public static final String MAX_AGGREGATION_FUNCTION = "MAX";
	public static final String MIN_AGGREGATION_FUNCTION = "MIN";
	public static final String AVG_AGGREGATION_FUNCTION = "AVG";
	public static final String COUNT_AGGREGATION_FUNCTION = "COUNT";

}
