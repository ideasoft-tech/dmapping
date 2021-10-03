//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: QuerySourceSpec.java,v 1.1 2005/10/05 20:33:58 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl.api;

import java.util.List;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface QuerySourceSpec {
	
	public String getName();
	public String getDescription();
	public String getCode();
	public boolean getQualify();
	public List<QuerySourceFieldSpec> getFields();

}
