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

public class Table extends DbTableType implements Serializable {
  private String _name;
  private boolean _has_name;
  private String _description;
  private boolean _has_description;
  private String _label;
  private boolean _has_label;

  public Table() {
  }

  public String getDescription() {
    return this._description;
  }

  public String getLabel() {
    return this._label;
  }

  public String getName() {
    return this._name;
  }



  public void setDescription(String description) {
    this._description = description;
  }

  public void setLabel(String label) {
    this._label = label;
  }

  public void setName(String name) {
    this._name = name;
  }


}
