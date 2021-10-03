// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DimensionScopeSpec.java,v 1.1 2005/10/27 20:34:53 marcelo Exp $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl.api;

import java.util.List;

/**
 * @author marcelo
 */
public interface DimensionScopeSpec {

	public String getDimensionName();
	public int getDimensionIndex();
	public String getType();
	public List<String> getLevelScopes(); 

}
