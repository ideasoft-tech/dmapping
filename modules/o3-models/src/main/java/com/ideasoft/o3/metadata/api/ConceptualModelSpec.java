//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ConceptualModelSpec.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This is the conceptual model (root node)
 * Star Model is a part of conceptual model but there are others, Simulation Model, etc.
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface ConceptualModelSpec {

	/**
	 * @return The Star Model defined in the conceptual model
	 */
	public StarModelSpec getStarModel();
	
}
