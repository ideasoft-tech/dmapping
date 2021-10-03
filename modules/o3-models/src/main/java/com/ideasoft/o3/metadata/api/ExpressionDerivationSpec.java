// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ExpressionDerivationSpec.java,v 1.1 2005/12/05 20:19:15 ignacio Exp $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.api;

/**
 * This is the expression derivation specification,
 * that is a derivation produced by an expression over measures
 * @author ignacio
 * @version $Revision: 1.1 $
 */
public interface ExpressionDerivationSpec extends MeasureDerivationSpec {

	/**
	 * @return The expression used to derivate the measure
	 */
	public String getExpression();
	
}
