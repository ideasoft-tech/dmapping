//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideasoft.edf.data.metadata.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.xml.sax.ContentHandler;

public class DbTableType implements Serializable {
//  private Record _record;
  private boolean _has_record;
  private Keys _keys;
  private boolean _has_keys;
//  private Indices _indices;
  private boolean _has_indices;
//  private Constraints _constraints;
  private boolean _has_constraints;

  public DbTableType() {
  }

//  public Constraints getConstraints() {
//    return this._constraints;
//  }
//
//  public Indices getIndices() {
//    return this._indices;
//  }
//
  public Keys getKeys() {
    return this._keys;
  }
//
//  public Record getRecord() {
//    return this._record;
//  }

//  public void setConstraints(Constraints constraints) {
//    this._constraints = constraints;
//  }
//
//  public void setIndices(Indices indices) {
//    this._indices = indices;
//  }
//
//  public void setKeys(Keys keys) {
//    this._keys = keys;
//  }
//
//  public void setRecord(Record record) {
//    this._record = record;
//  }

}
