//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: SourceFieldSpec.java,v 1.1 2005/10/05 20:33:58 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl.api;

import com.ideasoft.o3.metadata.MetadataException;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface SourceFieldSpec {
	
	public String getName();
	public String getType() throws MetadataException;

}