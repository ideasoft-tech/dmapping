// - Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright 2005 IdeaSoft Uruguay S.R.L. All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: DateAcumulationDerivationImpl.java,v 1.1 2005/12/05 20:19:28 ignacio Exp $
// -----------------------------------------------------------------------

package com.ideasoft.o3.metadata.impl;

import com.ideasoft.o3.metadata.api.DateAcumulationDerivationSpec;
import com.ideasoft.o3.metadata.api.SmoothSpec;

/**
 * @author ignacio
 * @version $Revision: 1.1 $
 * @see com.ideasoft.o3.metadata.impl.castor.DateAcumulationDerivationImpl
 */
public class DateAcumulationDerivationImpl implements DateAcumulationDerivationSpec {
	
	public DateAcumulationDerivationImpl() {		
	}
	
	public DateAcumulationDerivationImpl(String acumulationLevel, String dateDimension, String measure, SmoothSpec smooth) {
		this.acumulationLevel = acumulationLevel;
		this.dateDimension = dateDimension;
		this.measure = measure;
		this.smooth = smooth;
	}
	
	/**
	 * @castor.field set-method="setAcumulationLevel"
	 * @castor.field-xml name="acumulationLevel" node="attribute"
	 */
	public String getAcumulationLevel() {
		return acumulationLevel;
	}
	public void setAcumulationLevel(String accumulationLevel) {
		this.acumulationLevel = accumulationLevel;
	}
	
	/**
	 * @castor.field set-method="setDateDimension"
	 * @castor.field-xml name="dateDimension" node="attribute"
	 */
	public String getDateDimension() {
		return dateDimension;
	}
	public void setDateDimension(String dateDimension) {
		this.dateDimension = dateDimension;
	}
	
	/**
	 * @castor.field set-method="setMeasure"
	 * @castor.field-xml name="measure" node="attribute"
	 */
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
	public SmoothSpec getSmooth() {
		return smooth;
	}
	public void setSmooth(SmoothSpec smooth) {
		this.smooth = smooth;
	}
	
	@Override
	public int hashCode() {
		return measure.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		DateAcumulationDerivationImpl other = (DateAcumulationDerivationImpl) obj;
		
		return equals(measure, other.measure) &&
			equals(dateDimension, other.dateDimension) &&
			equals(acumulationLevel, other.acumulationLevel) &&
			equals(smooth, other.smooth);
	}
	
	protected boolean equals(Object one, Object two) {
		return (one == null && two == null) || (one != null && two != null && one.equals(two));
	}
	
	private String measure;
	private String dateDimension;
	private String acumulationLevel;
	private SmoothSpec smooth;

}
