// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl.castor;

import com.ideasoft.o3.metadata.api.DerivedMeasureTypeSpec;

import com.ideasoft.o3.metadata.impl.TwoMeasuresDerivationImpl;
import com.ideasoft.o3.metadata.impl.ExpressionDerivationImpl;

/**
 * @author ignacio
 * @version $Revision:$
 * @castor.class
 */
public class DerivedMeasureTypeImpl extends com.ideasoft.o3.metadata.impl.DerivedMeasureTypeImpl implements DerivedMeasureTypeSpec {
	
	/**
	 * @castor.field set-method="setDateAcumulation"
	 * @castor.field-xml name="dateAcumulation" node="element"
	 */
	public DateAcumulationDerivationImpl getDateAcumulation() {
		return getMeasureDerivation() instanceof DateAcumulationDerivationImpl ? (DateAcumulationDerivationImpl) getMeasureDerivation() : null;
	}
	public void setDateAcumulation(DateAcumulationDerivationImpl dateAcumulation) {
		setMeasureDerivation(dateAcumulation);
	}
	
	/**
	 * @castor.field set-method="setExpression"
	 * @castor.field-xml name="expression" node="element" 
	 */
	public ExpressionDerivationImpl getExpression() {
		return getMeasureDerivation() instanceof ExpressionDerivationImpl ? (ExpressionDerivationImpl) getMeasureDerivation() : null;
	}
	public void setExpression(ExpressionDerivationImpl expression) {
		setMeasureDerivation(expression);
	}
	
	/**
	 * @castor.field set-method="setTwoMeasures"
	 * @castor.field-xml name="twoMeasures" node="element"
	 */
	public TwoMeasuresDerivationImpl getTwoMeasures() {
		return getMeasureDerivation() instanceof TwoMeasuresDerivationImpl ? (TwoMeasuresDerivationImpl) getMeasureDerivation() : null;
	}
	public void setTwoMeasures(TwoMeasuresDerivationImpl twoMeasures) {
		setMeasureDerivation(twoMeasures);
	}

}
