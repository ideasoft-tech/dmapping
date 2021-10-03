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
 * @castor.class xml="starDimensionReference"
 */
public class StarDimensionReferenceImpl extends com.ideasoft.o3.metadata.impl.StarDimensionReferenceImpl {
	
	/**
	 * @castor.field set-method="setArrayListFactAttributes" type="java.lang.String" collection="arraylist"
	 * @castor.field-xml name="factAttribute" node="element"
	 */
	public ArrayList getArrayListFactAttributes() {
		return (ArrayList) getFactAttributes();
	}
	public void setArrayListFactAttributes(ArrayList factAttributes) {
		setFactAttributes(factAttributes);
	}

}
