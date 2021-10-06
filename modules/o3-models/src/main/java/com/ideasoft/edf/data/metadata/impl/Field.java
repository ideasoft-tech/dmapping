package com.ideasoft.edf.data.metadata.impl;

import org.apache.commons.lang.Validate;

import java.io.Serializable;


  public class Field extends DbFieldType implements Serializable {
    private Validate _validate;
    private boolean _has_validate;
//    private Extension _extension;
    private boolean _has_extension;

    public Field() {
    }

}
