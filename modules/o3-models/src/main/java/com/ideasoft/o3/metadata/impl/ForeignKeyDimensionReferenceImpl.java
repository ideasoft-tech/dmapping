//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ForeignKeyDimensionReferenceImpl.java,v 1.1 2005/10/03 18:50:55 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.ForeignKeyDimensionReferenceSpec;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 * @castor.class xml="foreignKeyReference"
 */
public class ForeignKeyDimensionReferenceImpl extends DimensionReferenceImpl implements ForeignKeyDimensionReferenceSpec {
	
	/**
	 * @castor.field set-method="setForeignKey"
	 * @castor.field-xml name="foreignKey" node="attribute"
	 */
	public String getForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}
	
	@Override
	public boolean equals(Object obj) {
		ForeignKeyDimensionReferenceImpl other = (ForeignKeyDimensionReferenceImpl) obj;
		
		return super.equals(obj) &&
			equals(foreignKey, other.foreignKey);
	}
	
	private String foreignKey;

}
