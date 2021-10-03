//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: QueryGenerationException.java,v 1.1 2005/10/20 15:53:49 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public class QueryPluginGenerationException extends MetadataException {

	/**
	 * Constructs an instance of <code>QueryPluginGenerationException</code> with the specified detail message.
	 * @param msg the detail message.
	 */
	public QueryPluginGenerationException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructs an instance of <code>QueryPluginGenerationException</code> with the specified detail message and cause.
	 * @param msg the detail message.
	 * @param cause the attached cause
	 */
	public QueryPluginGenerationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
