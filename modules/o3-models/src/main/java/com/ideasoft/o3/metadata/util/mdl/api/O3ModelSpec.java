//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: O3Generator.java,v 1.3 2005/09/27 22:03:52 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl.api;

import java.util.List;

/**
 * This is the O3Model interface
 * @author ignacio
 * @version $Revision:$
 */
public interface O3ModelSpec {
	
	public String getName();
	public String getDescription();
	
	public List<DimensionSpec> getDimensions();
	public List<MeasureSpec> getMeasures();
	public List<SourceSpec> getSources();

}
