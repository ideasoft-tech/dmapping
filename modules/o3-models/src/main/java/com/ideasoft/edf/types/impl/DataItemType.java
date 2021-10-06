package com.ideasoft.edf.types.impl;

import com.ideasoft.edf.types.impl.types.SchemaDatatype;
import org.xml.sax.ContentHandler;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

  public class DataItemType implements Serializable {
    private SchemaDatatype _type;
    private String _label;
    private boolean _has_label;
    private String _description;
    private boolean _has_description;
    private String _help;
    private boolean _has_help;
//    private Restriction _restriction;
    private boolean _has_restriction;
    private String _default;
    private boolean _has_default;
    private List _valueList = new ArrayList();
    private boolean _has_valueList;
    private String _mask;
    private boolean _has_mask;
    private List _errorConditionList = new ArrayList();
    private boolean _has_errorConditionList;

    public DataItemType() {
    }

//    public void addErrorCondition(ErrorCondition vErrorCondition) throws IndexOutOfBoundsException {
//      this._errorConditionList.add(vErrorCondition);
//    }
//
//    public void addErrorCondition(int index, ErrorCondition vErrorCondition) throws IndexOutOfBoundsException {
//      this._errorConditionList.add(index, vErrorCondition);
//    }
//
//    public void addValue(Value vValue) throws IndexOutOfBoundsException {
//      this._valueList.add(vValue);
//    }
//
//    public void addValue(int index, Value vValue) throws IndexOutOfBoundsException {
//      this._valueList.add(index, vValue);
//    }

    public String getDefault() {
      return this._default;
    }

    public String getDescription() {
      return this._description;
    }

//    public ErrorCondition getErrorCondition(int index) throws IndexOutOfBoundsException {
//      if (index >= 0 && index <= this._errorConditionList.size()) {
//        return (ErrorCondition)this._errorConditionList.get(index);
//      } else {
//        throw new IndexOutOfBoundsException();
//      }
//    }
//
//    public ErrorCondition[] getErrorCondition() {
//      int size = this._errorConditionList.size();
//      ErrorCondition[] mArray = new ErrorCondition[size];
//
//      for(int index = 0; index < size; ++index) {
//        mArray[index] = (ErrorCondition)this._errorConditionList.get(index);
//      }
//
//      return mArray;
//    }

    public int getErrorConditionCount() {
      return this._errorConditionList.size();
    }

    public String getHelp() {
      return this._help;
    }

    public String getLabel() {
      return this._label;
    }

    public String getMask() {
      return this._mask;
    }

//    public Restriction getRestriction() {
//      return this._restriction;
//    }

    public SchemaDatatype getType() {
      return this._type;
    }

//    public Value getValue(int index) throws IndexOutOfBoundsException {
//      if (index >= 0 && index <= this._valueList.size()) {
//        return (Value)this._valueList.get(index);
//      } else {
//        throw new IndexOutOfBoundsException();
//      }
//    }

//    public Value[] getValue() {
//      int size = this._valueList.size();
//      Value[] mArray = new Value[size];
//
//      for(int index = 0; index < size; ++index) {
//        mArray[index] = (Value)this._valueList.get(index);
//      }
//
//      return mArray;
//    }

    public int getValueCount() {
      return this._valueList.size();
    }

//    public boolean isValid() {
//      try {
//        this.validate();
//        return true;
//      } catch (ValidationException var2) {
//        return false;
//      }
//    }

    public Iterator iteratorErrorCondition() {
      return this._errorConditionList.iterator();
    }

    public Iterator iteratorValue() {
      return this._valueList.iterator();
    }

//    public void marshal(Writer out) throws MarshalException, ValidationException {
//      Marshaller.marshal(this, out);
//    }
//
//    public void marshal(ContentHandler handler) throws IOException, MarshalException, ValidationException {
//      Marshaller.marshal(this, handler);
//    }

    public void removeAllErrorCondition() {
      this._errorConditionList.clear();
    }

    public void removeAllValue() {
      this._valueList.clear();
    }

//    public boolean removeErrorCondition(ErrorCondition vErrorCondition) {
//      boolean removed = this._errorConditionList.remove(vErrorCondition);
//      return removed;
//    }
//
//    public ErrorCondition removeErrorCondition(int index) {
//      Object obj = this._errorConditionList.get(index);
//      this._errorConditionList.remove(index);
//      return (ErrorCondition)obj;
//    }
//
//    public boolean removeValue(Value vValue) {
//      boolean removed = this._valueList.remove(vValue);
//      return removed;
//    }
//
//    public Value removeValue(int index) {
//      Object obj = this._valueList.get(index);
//      this._valueList.remove(index);
//      return (Value)obj;
//    }

    public void setDefault(String _default) {
      this._default = _default;
    }

    public void setDescription(String description) {
      this._description = description;
    }

//    public void setErrorCondition(int index, ErrorCondition vErrorCondition) throws IndexOutOfBoundsException {
//      if (index >= 0 && index <= this._errorConditionList.size()) {
//        this._errorConditionList.set(index, vErrorCondition);
//      } else {
//        throw new IndexOutOfBoundsException();
//      }
//    }
//
//    public void setErrorCondition(ErrorCondition[] errorConditionArray) {
//      this._errorConditionList.clear();
//
//      for(int i = 0; i < errorConditionArray.length; ++i) {
//        this._errorConditionList.add(errorConditionArray[i]);
//      }
//
//    }

    public void setHelp(String help) {
      this._help = help;
    }

    public void setLabel(String label) {
      this._label = label;
    }

    public void setMask(String mask) {
      this._mask = mask;
    }

//    public void setRestriction(Restriction restriction) {
//      this._restriction = restriction;
//    }

    public void setType(SchemaDatatype type) {
      this._type = type;
    }



  }
