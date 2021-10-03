//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: FactTableSpec.java,v 1.5 2005/10/20 17:11:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

import java.util.List;

/**
 * This interface represents a fact table
 * @author ignacio
 * @version $Revision: 1.5 $
 */
public interface FactTableSpec {
	
	/**
	 * @return The name of the fact table
	 */
	public String getName();
	
	/**
	 * @return The optional description of the fact table
	 */
	public String getDescription();
	
	/**
	 * @return The table name
	 */
	public String getTableName();
	
	/**
	 * @return The schema of the fact table
	 */
	public String getEdfSchemaName();

	/**
	 * @return The dimensions used in the fact table
	 */
	public List<DimensionReferenceSpec> getDimensionReferences();
	
	/**
	 * @return The measures used in the fact table
	 */
	public List<MeasureSpec> getMeasures();

}