//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: MDLGenerator.java,v 1.4 2005/10/07 19:21:00 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata;

/**
 * This is the base exception for metadata package
 * @author ignacio
 * @version $Revision:$
 */
public class MetadataException extends Exception {

	/**
	 * Constructs an instance of <code>MetadataException</code> with the specified detail message.
	 * @param msg the detail message.
	 */
	public MetadataException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructs an instance of <code>MetadataException</code> with the specified detail message and cause.
	 * @param msg the detail message.
	 * @param cause the attached cause
	 */
	public MetadataException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
