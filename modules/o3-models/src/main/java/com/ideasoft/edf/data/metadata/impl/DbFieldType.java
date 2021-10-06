package com.ideasoft.edf.data.metadata.impl;

import com.ideasoft.edf.types.impl.DataItemType;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.xml.sax.ContentHandler;

public class DbFieldType extends DataItemType implements Serializable {
  private String _dataItem;
  private boolean _has_dataItem;
  private String _name;
  private boolean _has_name;
  private boolean _nullable = true;
  private boolean _has_nullable;

  public DbFieldType() {
  }

  public void deleteNullable() {
    this._has_nullable = false;
  }

  public String getDataItem() {
    return this._dataItem;
  }

  public String getName() {
    return this._name;
  }

  public boolean getNullable() {
    return this._nullable;
  }

  public boolean hasNullable() {
    return this._has_nullable;
  }


  public void setDataItem(String dataItem) {
    this._dataItem = dataItem;
  }

  public void setName(String name) {
    this._name = name;
  }

  public void setNullable(boolean nullable) {
    this._nullable = nullable;
    this._has_nullable = true;
  }

}
