//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ParameterScopeImpl.java,v 1.2 2005/09/21 20:47:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl.castor;

import java.util.ArrayList;

/**
 * This is the castor customization of the cube implementation
 * @author ignacio
 * @version $Revision:$
 * @castor.class xml="cube"
 */
public class CubeImpl extends com.ideasoft.o3.metadata.impl.CubeImpl {
	
	/**
	 * @castor.field set-method="setArrayListFactTables" type="java.lang.String" collection="arraylist"
	 * @castor.field-xml name="factTableReference" node="element"
	 */
	public ArrayList getArrayListFactTables() {
		return (ArrayList) getFactTables();
	}
	public void setArrayListFactTables(ArrayList factTables) {
		setFactTables(factTables);
	}

}
