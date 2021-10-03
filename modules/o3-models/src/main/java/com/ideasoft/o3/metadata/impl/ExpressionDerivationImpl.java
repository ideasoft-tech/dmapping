// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.ExpressionDerivationSpec;

/**
 * @author ignacio
 * @version $Revision:$
 * @castor.class
 */
public class ExpressionDerivationImpl implements ExpressionDerivationSpec {

	/**
	 * @castor.field set-method="setExpression"
	 * @castor.field-xml name="expression" node="element"
	 */
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	@Override
	public int hashCode() {
		return expression.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		ExpressionDerivationImpl other = (ExpressionDerivationImpl) obj;
		
		return equals(expression, other.expression);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String expression;
	
}
