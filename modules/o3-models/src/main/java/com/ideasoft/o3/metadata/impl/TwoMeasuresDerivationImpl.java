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

import com.ideasoft.o3.metadata.api.TwoMeasureDerivationSpec;

/**
 * @author ignacio
 * @version $Revision:$
 * @castor.class
 */
public class TwoMeasuresDerivationImpl implements TwoMeasureDerivationSpec {

	/**
	 * @castor.field set-method="setMeasure1"
	 * @castor.field-xml name="measure1" node="attribute"
	 */
	public String getMeasure1() {
		return measure1;
	}
	public void setMeasure1(String measure1) {
		this.measure1 = measure1;
	}
	
	/**
	 * @castor.field set-method="setOperator"
	 * @castor.field-xml name="operator" node="attribute"
	 */
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	/**
	 * @castor.field set-method="setMeasure2"
	 * @castor.field-xml name="measure2" node="attribute"
	 */
	public String getMeasure2() {
		return measure2;
	}
	public void setMeasure2(String measure2) {
		this.measure2 = measure2;
	}
	
	@Override
	public int hashCode() {
		return measure1.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		TwoMeasuresDerivationImpl other = (TwoMeasuresDerivationImpl) obj;
		
		return equals(measure1, other.measure1) &&
			equals(operator, other.operator) &&
			equals(measure2, other.measure2);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String measure1;
	private String operator;
	private String measure2;
	
}
