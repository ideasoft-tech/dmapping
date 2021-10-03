//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DateDimensionDefinitionImpl.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideasoft.o3.metadata.api.DateDimensionDefinitionSpec;
import com.ideasoft.o3.metadata.api.DateHierarchySpec;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public class DateDimensionDefinitionImpl extends DimensionDefinitionImpl implements DateDimensionDefinitionSpec {
	
	public List<DateHierarchySpec> getHierarchies() {
		return hierarchies;
	}
	public void setHierarchies(List<DateHierarchySpec> hierarchies) {
		this.hierarchies = hierarchies;
	}
	
	@Override
	public int hashCode() {
		return hierarchies.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		DateDimensionDefinitionImpl other = (DateDimensionDefinitionImpl) obj;
		
		return equals(hierarchies, other.hierarchies);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private List<DateHierarchySpec> hierarchies = new ArrayList<DateHierarchySpec>();

}
