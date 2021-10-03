//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TableLevelImpl.java,v 1.5 2006/01/16 20:01:38 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.TableLevelSpec;

/**
 * @author ignacio
 * @version $Revision: 1.5 $
 * @castor.class
 */
public class TableLevelImpl extends LevelImpl implements TableLevelSpec {
	
	public TableLevelImpl() {		
	}
	
	public TableLevelImpl(String name, String keyAttribute) {
		super(name);
		
		this.keyAttribute = keyAttribute;
	}
	
	public TableLevelImpl(String name, String keyAttribute, String helpSchemaName,
		String helpTableName, String labelAttribute, String longDescriptionAttribute,
		String descriptionAttribute) {
		super(name);
		
		this.keyAttribute = keyAttribute;
		this.helpSchemaName = helpSchemaName;
		this.helpTableName = helpTableName;
		this.labelAttribute = labelAttribute;
		this.longDescriptionAttribute = longDescriptionAttribute;
		this.descriptionAttribute = descriptionAttribute;
	}
	
	/**
	 * @castor.field set-method="setKeyAttribute"
	 * @castor.field-xml name="keyAttribute" node="attribute"
	 */
	public String getKeyAttribute() {
		return keyAttribute;
	}
	public void setKeyAttribute(String keyAttribute) {
		this.keyAttribute = keyAttribute;
	}
	
	/**
	 * @castor.field set-method="setHelpSchemaName"
	 * @castor.field-xml name="helpSchemaName" node="attribute"
	 */
	public String getHelpSchemaName() {
		return helpSchemaName;
	}
	public void setHelpSchemaName(String helpSchemaName) {
		this.helpSchemaName = helpSchemaName;
	}

	/**
	 * @castor.field set-method="setHelpTableName"
	 * @castor.field-xml name="helpTableName" node="attribute"
	 */
	public String getHelpTableName() {
		return helpTableName;
	}
	public void setHelpTableName(String helpTableName) {
		this.helpTableName = helpTableName;
	}
	
	/**
	 * @castor.field set-method="setLabelAttribute"
	 * @castor.field-xml name="labelAttribute" node="attribute"
	 */
	public String getLabelAttribute() {
		return labelAttribute;
	}
	public void setLabelAttribute(String labelAttribute) {
		this.labelAttribute = labelAttribute;
	}
	
	/**
	 * @castor.field set-method="setDescriptionAttribute"
	 * @castor.field-xml name="descriptionAttribute" node="attribute"
	 */
	public String getDescriptionAttribute() {
		return descriptionAttribute;
	}
	public void setDescriptionAttribute(String descriptionAttribute) {
		this.descriptionAttribute = descriptionAttribute;
	}
	
	/**
	 * @castor.field set-method="setLongDescriptionAttribute"
	 * @castor.field-xml name="longDescriptionAttribute" node="attribute"
	 */
	public String getLongDescriptionAttribute() {
		return longDescriptionAttribute;
	}
	public void setLongDescriptionAttribute(String longDescriptionAttribute) {
		this.longDescriptionAttribute = longDescriptionAttribute;
	}
	
	@Override
	public boolean equals(Object obj) {
		TableLevelImpl other = (TableLevelImpl) obj;
		
		return super.equals(obj) && 		
			equals(keyAttribute, other.keyAttribute) &&
			equals(helpSchemaName, other.helpSchemaName) &&
			equals(helpTableName, other.helpTableName) &&
			equals(labelAttribute, other.labelAttribute) &&
			equals(descriptionAttribute, other.descriptionAttribute) &&
			equals(longDescriptionAttribute, other.longDescriptionAttribute);
	}
	
	private String keyAttribute;
	private String helpSchemaName;
	private String helpTableName;
	private String labelAttribute;
	private String descriptionAttribute;
	private String longDescriptionAttribute;
	
}
