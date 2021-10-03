//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: LevelImpl.java,v 1.3 2005/10/05 15:25:15 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.LevelSpec;

/**
 * @author ignacio
 * @version $Revision: 1.3 $
 */
public abstract class LevelImpl implements LevelSpec {
	
	public LevelImpl() {
	}
	
	public LevelImpl(String name) {
		this.name = name;
	}
	
	/**
	 * @castor.field set-method="setName"
	 * @castor.field-xml name="name" node="attribute"
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @castor.field set-method="setDescription"
	 * @castor.field-xml name="description" node="element"
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @castor.field set-method="setUniqueLevel"
	 * @castor.field-xml name="description" node="element"
	 */
	public boolean getUniqueLevel() {
		return isUnique;
	}
	public void setUniqueLevel(boolean isUnique) {
		this.isUnique = isUnique;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		LevelImpl other = (LevelImpl) obj;
		
		return equals(name, other.name) &&
			equals(description, other.description);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String name;
	private String description;
	private boolean isUnique;

}
