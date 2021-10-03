//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: CubeImpl.java,v 1.2 2005/10/05 14:19:41 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideasoft.o3.metadata.api.CubeSpec;

/**
 * @author ignacio
 * @version $Revision: 1.2 $
 */
public class CubeImpl implements CubeSpec {
	
	public CubeImpl() {		
	}
	
	public CubeImpl(String name) {
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
	
	public List<String> getFactTables() {
		return factTables;
	}
	public void setFactTables(List<String> factTables) {
		this.factTables = factTables;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		CubeImpl other = (CubeImpl) obj;
		
		return equals(name, other.name) &&
			equals(description, other.description) &&
			equals(factTables, other.factTables);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String name;
	private String description;
	private List<String> factTables = new ArrayList<String>();
	
}
