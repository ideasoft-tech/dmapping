package com.ideasoft.edf.types.datatype;

public interface Datatype {
  int UNKNOWN_TYPE = 0;
  int FLOAT_TYPE = 1;
  int DOUBLE_TYPE = 2;
  int INT_TYPE = 3;
  int BOOLEAN_TYPE = 4;
  int SHORT_TYPE = 5;
  int DECIMAL_TYPE = 6;
  int LONG_TYPE = 7;
  int DATE_TYPE = 8;
  int DATETIME_TYPE = 9;
  int TIME_TYPE = 10;
  int BASE64_TYPE = 11;
  int STRING_TYPE = 12;
  int DURATION_TYPE = 13;
  int BYTE_TYPE = 14;
  String FLOAT_TYPE_NAME = "float";
  String DOUBLE_TYPE_NAME = "double";
  String INT_TYPE_NAME = "int";
  String BOOLEAN_TYPE_NAME = "boolean";
  String SHORT_TYPE_NAME = "short";
  String LONG_TYPE_NAME = "long";
  String DATE_TYPE_NAME = "date";
  String DATETIME_TYPE_NAME = "dateTime";
  String TIME_TYPE_NAME = "time";
  String BASE64_TYPE_NAME = "base64Binary";
  String STRING_TYPE_NAME = "string";
  String DURATION_TYPE_NAME = "duration";
  String DECIMAL_TYPE_NAME = "decimal";
  String BYTE_TYPE_NAME = "byte";
  int LIST_TYPE = 150;
  int LIST_OF_FLOAT_TYPE = 151;
  int LIST_OF_DOUBLE_TYPE = 152;
  int LIST_OF_INT_TYPE = 153;
  int LIST_OF_BOOLEAN_TYPE = 154;
  int LIST_OF_SHORT_TYPE = 155;
  int LIST_OF_DECIMAL_TYPE = 156;
  int LIST_OF_LONG_TYPE = 157;
  int LIST_OF_DATE_TYPE = 158;
  int LIST_OF_DATETIME_TYPE = 159;
  int LIST_OF_TIME_TYPE = 160;
  int LIST_OF_BASE64_TYPE = 161;
  int LIST_OF_STRING_TYPE = 162;
  int LIST_OF_BYTE_TYPE = 163;
}
