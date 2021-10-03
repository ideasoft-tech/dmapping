//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideasoft.edf.data.metadata.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DbIndexType implements Serializable {
  private String _name;
  private boolean _has_name;
  private List _fieldNameList = new ArrayList();
  private boolean _has_fieldNameList;

  public DbIndexType() {
  }

  public void addFieldName(FieldName vFieldName) throws IndexOutOfBoundsException {
    this._fieldNameList.add(vFieldName);
  }

  public void addFieldName(int index, FieldName vFieldName) throws IndexOutOfBoundsException {
    this._fieldNameList.add(index, vFieldName);
  }

  public FieldName getFieldName(int index) throws IndexOutOfBoundsException {
    if (index >= 0 && index <= this._fieldNameList.size()) {
      return (FieldName)this._fieldNameList.get(index);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public FieldName[] getFieldName() {
    int size = this._fieldNameList.size();
    FieldName[] mArray = new FieldName[size];

    for(int index = 0; index < size; ++index) {
      mArray[index] = (FieldName)this._fieldNameList.get(index);
    }

    return mArray;
  }

  public int getFieldNameCount() {
    return this._fieldNameList.size();
  }

  public String getName() {
    return this._name;
  }


  public Iterator iteratorFieldName() {
    return this._fieldNameList.iterator();
  }


  public void removeAllFieldName() {
    this._fieldNameList.clear();
  }

  public boolean removeFieldName(FieldName vFieldName) {
    boolean removed = this._fieldNameList.remove(vFieldName);
    return removed;
  }

  public FieldName removeFieldName(int index) {
    Object obj = this._fieldNameList.get(index);
    this._fieldNameList.remove(index);
    return (FieldName)obj;
  }

  public void setFieldName(int index, FieldName vFieldName) throws IndexOutOfBoundsException {
    if (index >= 0 && index <= this._fieldNameList.size()) {
      this._fieldNameList.set(index, vFieldName);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public void setFieldName(FieldName[] fieldNameArray) {
    this._fieldNameList.clear();

    for(int i = 0; i < fieldNameArray.length; ++i) {
      this._fieldNameList.add(fieldNameArray[i]);
    }

  }

  public void setName(String name) {
    this._name = name;
  }

}
