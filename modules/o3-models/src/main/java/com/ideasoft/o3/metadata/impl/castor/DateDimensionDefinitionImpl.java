//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: O3GeneratorTest.java,v 1.2 2005/09/16 16:51:24 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl.castor;

import java.util.ArrayList;

/**
 * This is the castor customization
 * @author ignacio
 * @version $Revision:$
 * @castor.class xml="dateDimensionDefinition"
 */
public class DateDimensionDefinitionImpl extends com.ideasoft.o3.metadata.impl.DateDimensionDefinitionImpl {
	
	/**
	 * @castor.field set-method="setArrayListHierarchies" type="com.ideasoft.o3.metadata.impl.castor.DateHierarchyImpl" collection="arraylist"
	 * @castor.field-xml name="hierarchy" node="element"
	 */
	public ArrayList getArrayListHierarchies() {
		return (ArrayList) getHierarchies();
	}
	public void setArrayListHierarchies(ArrayList hierarchy) {
		setHierarchies(hierarchy);
	}

}
