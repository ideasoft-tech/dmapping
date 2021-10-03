//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ConceptualModelImpl.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.ConceptualModelSpec;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 * @castor.class xml="conceptualModel"
 */
public class ConceptualModelImpl implements ConceptualModelSpec {
	
	/**
	 * @castor.field set-method="setStarModel" type="com.ideasoft.o3.metadata.impl.castor.StarModelImpl"
	 * @castor.field-xml name="starModel" node="element"
	 */
	public com.ideasoft.o3.metadata.impl.StarModelImpl getStarModel() {
		return starModel;
	}
	public void setStarModel(com.ideasoft.o3.metadata.impl.StarModelImpl starModel) {
		this.starModel = starModel;
	}
	
	@Override
	public int hashCode() {
		return starModel != null ? starModel.hashCode() : 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		ConceptualModelImpl other = (ConceptualModelImpl) obj;
		
		return equals(starModel, other.starModel);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private StarModelImpl starModel;

}
