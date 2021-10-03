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

public class ForeignKey extends DbIndexType {
  private String _name;
  private String _index;
  private boolean _has_index;
  private String _helpLabelField;
  private boolean _has_helpLabelField;
  private String _helpDescriptionField;
  private boolean _has_helpDescriptionField;

  public ForeignKey() {
  }


  public String getName() {
    return this._name;
  }


  public String getHelpDescriptionField() {
    return this._helpDescriptionField;
  }

  public String getHelpLabelField() {
    return this._helpLabelField;
  }

  public String getIndex() {
    return this._index;
  }



  public void setHelpDescriptionField(String helpDescriptionField) {
    this._helpDescriptionField = helpDescriptionField;
  }

  public void setHelpLabelField(String helpLabelField) {
    this._helpLabelField = helpLabelField;
  }

  public void setIndex(String index) {
    this._index = index;
  }
}

