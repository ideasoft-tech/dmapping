//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: StarModelImpl.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideasoft.o3.metadata.api.CubeSpec;
import com.ideasoft.o3.metadata.api.FactTableSpec;
import com.ideasoft.o3.metadata.api.StarDimensionSpec;
import com.ideasoft.o3.metadata.api.StarModelSpec;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public class StarModelImpl implements StarModelSpec {
	
	public StarModelImpl() {
	}
	
	public StarModelImpl(String name) {
		this(name, null);
	}
	
	public StarModelImpl(String name, String description) {
		this.name = name;
		this.description = description;
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
	
	public List<StarDimensionSpec> getDimensions() {
		return dimensions;
	}
	public void setDimensions(List<StarDimensionSpec> dimensions) {
		this.dimensions = dimensions;
	}
	
	public List<FactTableSpec> getFactTables() {
		return factTables;
	}
	public void setFactTables(List<FactTableSpec> factTables) {
		this.factTables = factTables;
	}
	
	public List<CubeSpec> getCubes() {
		return cubes;
	}
	public void setCubes(List<CubeSpec> cubes) {
		this.cubes = cubes;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		StarModelImpl other = (StarModelImpl) obj;
		
		return equals(name, other.name) &&
			equals(description, other.description) &&
			equals(dimensions, other.dimensions) &&
			equals(factTables, other.factTables) &&
			equals(cubes, other.cubes);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String name;
	private String description;
	private List<StarDimensionSpec> dimensions = new ArrayList<StarDimensionSpec>();
	private List<FactTableSpec> factTables = new ArrayList<FactTableSpec>();
	private List<CubeSpec> cubes = new ArrayList<CubeSpec>();

}
