//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TableDimensionDefinitionSpec.java,v 1.3 2005/10/20 17:11:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

import java.util.List;

/**
 * This is interface represents a star dimension defined from a table
 * @author ignacio
 * @version $Revision: 1.3 $
 */
public interface TableDimensionDefinitionSpec extends DimensionDefinitionSpec {
	
	/**
	 * @return The name of the table
	 */
	public String getTableName();
	
	/**
	 * @return The name of the schema
	 */
	public String getEdfSchemaName();
	
	/**
	 * @return The unique level used or null if not correspond
	 */
	public String getUniqueLevel();
	
	/**
	 * @return The hierarchy of the table dimension
	 */
	public List<TableHierarchySpec> getHierarchies();

}
