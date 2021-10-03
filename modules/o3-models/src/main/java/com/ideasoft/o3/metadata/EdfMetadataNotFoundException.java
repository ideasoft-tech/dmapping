//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: QueryGenerator.java,v 1.7 2005/10/21 16:06:25 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata;

/**
 * This exception indicate that the EDF field was not found
 * @author ignacio
 * @version $Revision:$
 */
public class EdfMetadataNotFoundException extends MetadataException {

	public EdfMetadataNotFoundException(String msg) {
		super(msg);
	}

}
