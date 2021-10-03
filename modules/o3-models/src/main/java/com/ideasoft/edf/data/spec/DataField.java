//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideasoft.edf.data.spec;

import com.ideasoft.edf.core.env.SessionContext;
import com.ideasoft.edf.types.datatype.DatatypeEvaluator;
//import com.ideasoft.edf.types.datatype.DatatypeEvaluator;
//import com.ideasoft.edf.types.impl.DataItemType;

public interface DataField {
  void setSessionContext(SessionContext var1);

  String[] getValues();

  String[] getLabels();

  String[] getDescriptions();

  String getLabel();

  String getDescription();

  String getHelpText();

  String getErrorMessage(String var1);

  String getLabelforValue(String var1);

  String getDescriptionforValue(String var1);
//
//  DataItemType getSpec();
//
  DatatypeEvaluator getDatatype();
//
//  HelpSpec getHelp();
//
//  ValidationSpec getValidation();
//
//  Options getOptions();

  String getName();

  String getQName();

  boolean isNullable();

  String getErrorMessage(int var1);

  int getErrorMessageCount();
}
