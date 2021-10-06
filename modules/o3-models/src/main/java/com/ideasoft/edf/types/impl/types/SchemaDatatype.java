//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideasoft.edf.types.impl.types;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

public class SchemaDatatype implements Serializable {
  public static final int DURATION_TYPE = 0;
  public static final SchemaDatatype DURATION = new SchemaDatatype(0, "duration");
  public static final int DATETIME_TYPE = 1;
  public static final SchemaDatatype DATETIME = new SchemaDatatype(1, "dateTime");
  public static final int TIME_TYPE = 2;
  public static final SchemaDatatype TIME = new SchemaDatatype(2, "time");
  public static final int DATE_TYPE = 3;
  public static final SchemaDatatype DATE = new SchemaDatatype(3, "date");
  public static final int STRING_TYPE = 4;
  public static final SchemaDatatype STRING = new SchemaDatatype(4, "string");
  public static final int BOOLEAN_TYPE = 5;
  public static final SchemaDatatype BOOLEAN = new SchemaDatatype(5, "boolean");
  public static final int BASE64BINARY_TYPE = 6;
  public static final SchemaDatatype BASE64BINARY = new SchemaDatatype(6, "base64Binary");
  public static final int DECIMAL_TYPE = 7;
  public static final SchemaDatatype DECIMAL = new SchemaDatatype(7, "decimal");
  public static final int LONG_TYPE = 8;
  public static final SchemaDatatype LONG = new SchemaDatatype(8, "long");
  public static final int DOUBLE_TYPE = 9;
  public static final SchemaDatatype DOUBLE = new SchemaDatatype(9, "double");
  public static final int FLOAT_TYPE = 10;
  public static final SchemaDatatype FLOAT = new SchemaDatatype(10, "float");
  public static final int INT_TYPE = 11;
  public static final SchemaDatatype INT = new SchemaDatatype(11, "int");
  public static final int SHORT_TYPE = 12;
  public static final SchemaDatatype SHORT = new SchemaDatatype(12, "short");
  public static final int BYTE_TYPE = 13;
  public static final SchemaDatatype BYTE = new SchemaDatatype(13, "byte");
  private static Hashtable _memberTable = init();
  private int type = -1;
  private String stringValue = null;

  private SchemaDatatype(int type, String value) {
    this.type = type;
    this.stringValue = value;
  }

  public static Enumeration enumerate() {
    return _memberTable.elements();
  }

  public int getType() {
    return this.type;
  }

  private static Hashtable init() {
    Hashtable members = new Hashtable();
    members.put("duration", DURATION);
    members.put("dateTime", DATETIME);
    members.put("time", TIME);
    members.put("date", DATE);
    members.put("string", STRING);
    members.put("boolean", BOOLEAN);
    members.put("base64Binary", BASE64BINARY);
    members.put("decimal", DECIMAL);
    members.put("long", LONG);
    members.put("double", DOUBLE);
    members.put("float", FLOAT);
    members.put("int", INT);
    members.put("short", SHORT);
    members.put("byte", BYTE);
    return members;
  }

  public String toString() {
    return this.stringValue;
  }

  public static SchemaDatatype valueOf(String string) {
    Object obj = null;
    if (string != null) {
      obj = _memberTable.get(string);
    }

    if (obj == null) {
      String err = "'" + string + "' is not a valid SchemaDatatype";
      throw new IllegalArgumentException(err);
    } else {
      return (SchemaDatatype)obj;
    }
  }
}
