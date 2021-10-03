package com.ideasoft.edf.types.datatype;

public interface DatatypeEvaluator {
  Object getValue(String var1);

  String getLexicalValue(Object var1);

  boolean isValid(String var1);

//  void checkValid(String var1) throws DatatypeEvaluatorException;

  String getTypeName();

  int getType();

  boolean hasListType();

  DatatypeEvaluator getBasicTypeEvaluator();

//  TypeSpec getTypeSpec();

  Object normalizeValue(Object var1);
}
