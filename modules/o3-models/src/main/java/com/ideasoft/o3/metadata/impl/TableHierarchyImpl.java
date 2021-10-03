//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TableHierarchyImpl.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideasoft.o3.metadata.api.TableHierarchySpec;
import com.ideasoft.o3.metadata.api.TableLevelSpec;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public class TableHierarchyImpl implements TableHierarchySpec {

	public List<TableLevelSpec> getLevels() {
		return levels;
	}
	public void setLevels(List<TableLevelSpec> levels) {
		this.levels = levels;
	}
	
	@Override
	public int hashCode() {
		return levels.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		TableHierarchyImpl other = (TableHierarchyImpl) obj;
		
		return equals(levels, other.levels);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private List<TableLevelSpec> levels = new ArrayList<TableLevelSpec>();
	
}
