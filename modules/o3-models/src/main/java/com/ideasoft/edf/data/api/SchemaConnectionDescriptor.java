//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideasoft.edf.data.api;

import java.util.Map;

public interface SchemaConnectionDescriptor {
  String getEngine();

  String getDriver();

  String getURL();

  String getUser();

  String getPassword();

  String getDataSource();

  Map getParameters();

//  SchemaConnectionPoolDescriptor getPoolDescriptor();
}
