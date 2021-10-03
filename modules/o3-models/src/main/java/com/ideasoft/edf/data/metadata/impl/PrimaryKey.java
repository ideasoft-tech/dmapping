package com.ideasoft.edf.data.metadata.impl;

import java.io.Serializable;

public class PrimaryKey extends DbIndexType implements Serializable {

  private boolean _complete;

  private boolean _has_complete;

  public PrimaryKey() {
  }

  public void deleteComplete() {
    this._has_complete = false;
  }

  public boolean getComplete() {
    return this._complete;
  }

  public boolean hasComplete() {
    return this._has_complete;
  }

}