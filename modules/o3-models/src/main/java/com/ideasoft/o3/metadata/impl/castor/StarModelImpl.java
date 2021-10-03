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
 * This is a castor customization
 * @author ignacio
 * @version $Revision:$
 * @castor.class xml="starModel"
 */
public class StarModelImpl extends com.ideasoft.o3.metadata.impl.StarModelImpl {

	/**
	 * @castor.field set-method="setArrayListDimensions" type="com.ideasoft.o3.metadata.impl.castor.StarDimensionImpl" collection="arraylist"
	 * @castor.field-xml name="starDimension" node="element" location="starDimensions"
	 */
	public ArrayList getArrayListDimensions() {
		return (ArrayList) getDimensions();
	}
	public void setArrayListDimensions(ArrayList dimensions) {
		setDimensions(dimensions);
	}
	
	/**
	 * @castor.field set-method="setArrayListFactTables" type="com.ideasoft.o3.metadata.impl.castor.FactTableImpl" collection="arraylist"
	 * @castor.field-xml name="factTable" node="element" location="factTables"
	 */
	public ArrayList getArrayListFactTables() {
		return (ArrayList) getFactTables();
	}
	public void setArrayListFactTables(ArrayList factTables) {
		setFactTables(factTables);
	}
	
	/**
	 * @castor.field set-method="setArrayListCubes" type="com.ideasoft.o3.metadata.impl.castor.CubeImpl" collection="arraylist"
	 * @castor.field-xml name="cube" node="element" location="cubes"
	 */
	public ArrayList getArrayListCubes() {
		return (ArrayList) getCubes();
	}
	public void setArrayListCubes(ArrayList cubes) {
		setCubes(cubes);
	}
	
}
