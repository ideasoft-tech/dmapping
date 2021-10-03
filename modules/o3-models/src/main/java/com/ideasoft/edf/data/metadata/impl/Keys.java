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

public class Keys implements Serializable {

  private PrimaryKey _primaryKey;

  private boolean _has_primaryKey;

  private KeysSequence _keysSequence;

  private boolean _has_keysSequence;

  public Keys() {
  }

  public KeysSequence getKeysSequence() {
    return this._keysSequence;
  }

  public PrimaryKey getPrimaryKey() {
    return this._primaryKey;
  }

}