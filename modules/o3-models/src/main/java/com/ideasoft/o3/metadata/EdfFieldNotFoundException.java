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

import com.ideasoft.edf.data.spec.Metadata;

/**
 * This exception indicate that the EDF metadata was not found
 * @author ignacio
 * @version $Revision:$
 */
public class EdfFieldNotFoundException extends MetadataException {
	
	public EdfFieldNotFoundException(String msg, Metadata edfMetadata, String edfField) {
		super(msg);
		this.edfMetadata = edfMetadata;
		this.edfField = edfField;
	}
	
	public String getEdfField() {
		return edfField;
	}
	public Metadata getEdfMetadata() {
		return edfMetadata;
	}
	
	private transient Metadata edfMetadata;
	private String edfField;

}
