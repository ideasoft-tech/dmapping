//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideasoft.app;

public interface Application extends Runnable {
  int EXIT_CODE_OK = 0;
  int EXIT_CODE_INIT_FAILED = 1;

  String getName();

  boolean init();

  void externFinalize();

  boolean acceptMsg(Object var1);

  String[][] getParameterInfo();

  int getExitCode();
}
