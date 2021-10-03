//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TableLevelSpec.java,v 1.2 2005/12/30 16:32:32 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This is the table level specification
 * @author ignacio
 * @version $Revision: 1.2 $
 */
public interface TableLevelSpec extends LevelSpec {
	
	/**
	 * @return The attribute to be used as key in the level
	 */
	public String getKeyAttribute();
	
	/**
	 * This is optionally used when the label/description/etc attributes are not in main table
	 * @return The schema for help table
	 */
	public String getHelpSchemaName();
	
	/**
	 * This is optionally used when the label/description/etc attributes are not in main table
	 * @return The help table name
	 */
	public String getHelpTableName();
	
	/**
	 * @return The attribute to be used as label in the level
	 */
	public String getLabelAttribute();
	
	/**
	 * @return The attribute to be used as description in the level
	 */
	public String getDescriptionAttribute();
	
	/**
	 * @return The attribute to be used as long description in the level
	 */
	public String getLongDescriptionAttribute();

}
