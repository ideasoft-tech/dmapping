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
import org.xml.sax.ContentHandler;

public class KeysSequence implements Serializable {
  private List _foreignKeyList = new ArrayList();
  private boolean _has_foreignKeyList;
  private List _uniqueKeyList = new ArrayList();
  private boolean _has_uniqueKeyList;

  public KeysSequence() {
  }

  public void addForeignKey(ForeignKey vForeignKey) throws IndexOutOfBoundsException {
    this._foreignKeyList.add(vForeignKey);
  }

  public void addForeignKey(int index, ForeignKey vForeignKey) throws IndexOutOfBoundsException {
    this._foreignKeyList.add(index, vForeignKey);
  }

  public void addUniqueKey(UniqueKey vUniqueKey) throws IndexOutOfBoundsException {
    this._uniqueKeyList.add(vUniqueKey);
  }

  public void addUniqueKey(int index, UniqueKey vUniqueKey) throws IndexOutOfBoundsException {
    this._uniqueKeyList.add(index, vUniqueKey);
  }

  public ForeignKey getForeignKey(int index) throws IndexOutOfBoundsException {
    if (index >= 0 && index <= this._foreignKeyList.size()) {
      return (ForeignKey)this._foreignKeyList.get(index);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public ForeignKey[] getForeignKey() {
    int size = this._foreignKeyList.size();
    ForeignKey[] mArray = new ForeignKey[size];

    for(int index = 0; index < size; ++index) {
      mArray[index] = (ForeignKey)this._foreignKeyList.get(index);
    }

    return mArray;
  }

  public int getForeignKeyCount() {
    return this._foreignKeyList.size();
  }

  public UniqueKey getUniqueKey(int index) throws IndexOutOfBoundsException {
    if (index >= 0 && index <= this._uniqueKeyList.size()) {
      return (UniqueKey)this._uniqueKeyList.get(index);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public UniqueKey[] getUniqueKey() {
    int size = this._uniqueKeyList.size();
    UniqueKey[] mArray = new UniqueKey[size];

    for(int index = 0; index < size; ++index) {
      mArray[index] = (UniqueKey)this._uniqueKeyList.get(index);
    }

    return mArray;
  }

  public int getUniqueKeyCount() {
    return this._uniqueKeyList.size();
  }

  public Iterator iteratorForeignKey() {
    return this._foreignKeyList.iterator();
  }

  public Iterator iteratorUniqueKey() {
    return this._uniqueKeyList.iterator();
  }

  public void removeAllForeignKey() {
    this._foreignKeyList.clear();
  }

  public void removeAllUniqueKey() {
    this._uniqueKeyList.clear();
  }

  public boolean removeForeignKey(ForeignKey vForeignKey) {
    boolean removed = this._foreignKeyList.remove(vForeignKey);
    return removed;
  }

  public ForeignKey removeForeignKey(int index) {
    Object obj = this._foreignKeyList.get(index);
    this._foreignKeyList.remove(index);
    return (ForeignKey)obj;
  }

  public boolean removeUniqueKey(UniqueKey vUniqueKey) {
    boolean removed = this._uniqueKeyList.remove(vUniqueKey);
    return removed;
  }

  public UniqueKey removeUniqueKey(int index) {
    Object obj = this._uniqueKeyList.get(index);
    this._uniqueKeyList.remove(index);
    return (UniqueKey)obj;
  }

  public void setForeignKey(int index, ForeignKey vForeignKey) throws IndexOutOfBoundsException {
    if (index >= 0 && index <= this._foreignKeyList.size()) {
      this._foreignKeyList.set(index, vForeignKey);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public void setForeignKey(ForeignKey[] foreignKeyArray) {
    this._foreignKeyList.clear();

    for(int i = 0; i < foreignKeyArray.length; ++i) {
      this._foreignKeyList.add(foreignKeyArray[i]);
    }

  }

  public void setUniqueKey(int index, UniqueKey vUniqueKey) throws IndexOutOfBoundsException {
    if (index >= 0 && index <= this._uniqueKeyList.size()) {
      this._uniqueKeyList.set(index, vUniqueKey);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public void setUniqueKey(UniqueKey[] uniqueKeyArray) {
    this._uniqueKeyList.clear();

    for(int i = 0; i < uniqueKeyArray.length; ++i) {
      this._uniqueKeyList.add(uniqueKeyArray[i]);
    }

  }

}
