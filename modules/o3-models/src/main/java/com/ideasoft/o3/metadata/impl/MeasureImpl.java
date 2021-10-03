//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: MeasureImpl.java,v 1.3 2005/10/05 15:25:15 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.MeasureSpec;
import com.ideasoft.o3.metadata.api.MeasureTypeSpec;

/**
 * @author ignacio
 * @version $Revision: 1.3 $
 * @see com.ideasoft.o3.metadata.impl.castor.MeasureImpl
 */
public class MeasureImpl implements MeasureSpec {
	
	public MeasureImpl() {		
	}
	
	public MeasureImpl(String name, String aggregateFunction, MeasureTypeSpec type) {
		this.name = name;
		this.aggregationFunction = aggregateFunction;
		this.type = type;
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
	 * @castor.field set-method="setAggregationFunction"
	 * @castor.field-xml name="aggregationFunction" node="attribute"
	 */
	public String getAggregationFunction() {
		return aggregationFunction;
	}
	public void setAggregationFunction(String aggregationFunction) {
		this.aggregationFunction = aggregationFunction;
	}
	
	public MeasureTypeSpec getType() {
		return type;
	}
	public void setType(MeasureTypeSpec type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		MeasureImpl other = (MeasureImpl) obj;
		
		return equals(name, other.name) &&
			equals(description, other.description) &&
			equals(aggregationFunction, other.aggregationFunction) &&
			equals(type, other.type);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String name;
	private String description;	
	private String aggregationFunction;
	private MeasureTypeSpec type;

}
