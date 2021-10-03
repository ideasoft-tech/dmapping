// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DerivedMeasureTypeImpl.java,v 1.1 2005/12/05 20:19:28 ignacio Exp $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.DerivedMeasureTypeSpec;
import com.ideasoft.o3.metadata.api.MeasureDerivationSpec;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 * @see com.ideasoft.o3.metadata.impl.castor.DerivedMeasureTypeImpl
 */
public class DerivedMeasureTypeImpl implements DerivedMeasureTypeSpec {
	
	public DerivedMeasureTypeImpl() {		
	}
	
	public DerivedMeasureTypeImpl(String calculate, MeasureDerivationSpec measureDerivation) {
		this.calculate = calculate;
		this.measureDerivation = measureDerivation;
	}
	
	/**
	 * @castor.field set-method="setCalculate"
	 * @castor.field-xml name="calculate" node="attribute"
	 */
	public String getCalculate() {
		return calculate;
	}
	public void setCalculate(String calculate) {
		this.calculate = calculate;
	}
	
	public MeasureDerivationSpec getMeasureDerivation() {
		return measureDerivation;
	}
	public void setMeasureDerivation(MeasureDerivationSpec measureDerivation) {
		this.measureDerivation = measureDerivation;
	}
	
	@Override
	public int hashCode() {
		return calculate.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		DerivedMeasureTypeImpl other = (DerivedMeasureTypeImpl) obj;
		
		return equals(calculate, other.calculate) &&
			equals(measureDerivation, other.measureDerivation);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String calculate;
	private MeasureDerivationSpec measureDerivation;

}
