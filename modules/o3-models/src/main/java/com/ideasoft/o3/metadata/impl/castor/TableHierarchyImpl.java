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
 * @castor.class
 */
public class TableHierarchyImpl extends com.ideasoft.o3.metadata.impl.TableHierarchyImpl {
	
	/**
	 * @castor.field set-method="setArrayListLevels" type="com.ideasoft.o3.metadata.impl.TableLevelImpl" collection="arraylist"
	 * @castor.field-xml name="level" node="element"
	 */
	public ArrayList getArrayListLevels() {
		return (ArrayList) getLevels();
	}
	public void setArrayListLevels(ArrayList levels) {
		setLevels(levels);
	}

}
