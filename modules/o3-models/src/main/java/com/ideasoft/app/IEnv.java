//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideasoft.app;

import java.applet.Applet;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

public class IEnv {
  protected Applet applet;
  protected int parametersCount = 0;
  protected Properties properties;

  public IEnv() {
    try {
      this.properties = (Properties)System.getProperties().clone();
    } catch (SecurityException var2) {
      System.err.println("Unable to load System properties: " + var2);
      this.properties = new Properties();
    }

    PropertiesUtilities.replaceAllProperties((Properties)null, this.properties);
  }

  public IEnv(Properties properties) {
    this.setProperties(properties);
  }

  /** @deprecated */
  public Properties getProperties() {
    return this.properties;
  }

  public void setProperties(Properties p) {
    this.properties = new Properties();
    if (p != null) {
      this.properties.putAll(p);
      PropertiesUtilities.replaceAllProperties(com.ideasoft.app.Context.getGlobalProperties(), this.properties);
    }

  }

  public void setProperty(String property, String value) {
    this.properties.setProperty(property, value);
  }

  public Iterator getPropertyNames() {
    HashSet keys = new HashSet();

    try {
      Properties p = System.getProperties();
      keys.addAll(p.keySet());
    } catch (SecurityException var3) {
      System.err.println("Unable to load System properties: " + var3);
    }

    keys.addAll(this.properties.keySet());
    return keys.iterator();
  }

  public String getProperty(String key, String defaultValue) {
    String p = this.getProperty(key);
    return p != null ? p : defaultValue;
  }

  public String getProperty(String key) {
    String p = this.properties.getProperty(key);
    if (p == null) {
      try {
        p = System.getProperty(key);
      } catch (SecurityException var4) {
        System.out.println("Cannot get the value for the System property: " + key + ". SecurityException: " + var4);
      }
    }

    return p == null && this.isApplet() ? this.applet.getParameter(key) : p;
  }

  public boolean containsKey(String key) {
    boolean exists = this.properties.containsKey(key);
    if (!exists) {
      try {
        Properties p = System.getProperties();
        exists = p.containsKey(key);
      } catch (SecurityException var4) {
        System.err.println("Unable to load System properties: " + var4);
      }
    }

    return exists;
  }

  public int getIntProperty(String property, int defaultValue) {
    String value = this.getProperty(property, String.valueOf(defaultValue));

    int iValue;
    try {
      iValue = Integer.parseInt(value.trim());
    } catch (NumberFormatException var6) {
      iValue = defaultValue;
    }

    return iValue;
  }

  public long getLongProperty(String property, long defaultValue) {
    String value = this.getProperty(property, String.valueOf(defaultValue));

    long iValue;
    try {
      iValue = Long.parseLong(value.trim());
    } catch (NumberFormatException var8) {
      iValue = defaultValue;
    }

    return iValue;
  }

  public boolean getBooleanProperty(String property, boolean defaultValue) {
    String value = this.getProperty(property, String.valueOf(defaultValue));
    return Boolean.valueOf(value);
  }

  public String getParameter(int number) {
    return this.getProperty("arg" + number);
  }

  public String getParameter(int number, String defaultValue) {
    return this.properties.getProperty("arg" + number, defaultValue);
  }

  public int getParametersCount() {
    return this.parametersCount;
  }

  public int setParametersCount(int count) {
    this.parametersCount = count;
    return count;
  }

  public void addProperties(Properties dest, Properties prop) {
    if (prop != null && dest != null) {
      Enumeration e = prop.keys();

      while(e.hasMoreElements()) {
        String key = (String)e.nextElement();
        dest.put(key, ((String)prop.get(key)).trim());
      }

    }
  }

  public boolean isApplet() {
    return this.applet != null;
  }

  public Applet getApplet() {
    return this.applet;
  }

  public Applet setApplet(Applet app) {
    this.applet = app;
    return app;
  }
}
