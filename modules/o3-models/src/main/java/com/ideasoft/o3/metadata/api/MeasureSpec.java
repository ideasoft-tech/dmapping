//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: MeasureSpec.java,v 1.4 2005/12/27 16:24:12 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This interface represents the measure specification
 * @author ignacio
 * @version $Revision: 1.4 $
 */
public interface MeasureSpec {
	
	/**
	 * @return The name of the measure
	 */
	public String getName();
	
	/**
	 * @return The optional description of the measure
	 */
	public String getDescription();
	
	/**
	 * @return The measure type (basic/derivated)
	 */
	public MeasureTypeSpec getType();
	
	/**
	 * @return The aggregation funtion to be used
	 * @see #SUM_AGGREGATION_FUNCTION
	 * @see #MAX_AGGREGATION_FUNCTION
	 * @see #MIN_AGGREGATION_FUNCTION
	 * @see #AVG_AGGREGATION_FUNCTION
	 * @see #COUNT_AGGREGATION_FUNCTION
	 */
	public String getAggregationFunction();
	
	/**
	 * Those are the aggregation functions
	 */
	public static final String SUM_AGGREGATION_FUNCTION = "SUM",
		MAX_AGGREGATION_FUNCTION = "MAX",
		MIN_AGGREGATION_FUNCTION = "MIN",
		AVG_AGGREGATION_FUNCTION = "AVG",
		COUNT_AGGREGATION_FUNCTION = "COUNT";

}
