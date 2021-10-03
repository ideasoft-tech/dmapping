//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: BasicMeasureTypeSpec.java,v 1.1 2005/12/05 20:19:15 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This interface represents the basic measure type
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface BasicMeasureTypeSpec extends MeasureTypeSpec {

	/**
	 * @return The name of the attribute that was used to obtain the measure value
	 */
	public String getMeasureAttribute();
	
}
