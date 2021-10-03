//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideasoft.app;

import ideasoft.util.log.Log;
import java.util.Iterator;
import java.util.Properties;

public class PropertiesUtilities {
  static String DELIM_START = "${";
  static char DELIM_STOP = '}';
  static int DELIM_START_LEN = 2;
  static int DELIM_STOP_LEN = 1;

  public PropertiesUtilities() {
  }

  public static void replaceAllProperties(Properties basicProperties, Properties p) {
    Iterator it = p.keySet().iterator();

    while(it.hasNext()) {
      String s = (String)it.next();
      String value = p.getProperty(s);
      if (value != null) {
        value = replaceProperties(value, basicProperties, p);
        p.setProperty(s, value);
      }
    }

  }

  public static String replaceProperties(String val, Properties props) throws IllegalArgumentException {
    return replaceProperties(val, (Properties)null, props);
  }

  public static String replaceProperties(String val, Properties basicProperties, Properties props) throws IllegalArgumentException {
    if (val == null) {
      return null;
    } else {
      StringBuffer sbuf = new StringBuffer();
      int i = 0;

      while(true) {
        int j = val.indexOf(DELIM_START, i);
        if (j == -1) {
          break;
        }

        sbuf.append(val.substring(i, j));
        int k = val.indexOf(DELIM_STOP, j);
        if (k == -1) {
          Log.warning('"' + val + "\" has no closing brace. Opening brace at position " + j + '.');
          break;
        }

        j += DELIM_START_LEN;
        String key = val.substring(j, k);
        String replacement = basicProperties == null ? null : basicProperties.getProperty(key);
        if (replacement == null && props != null) {
          replacement = props.getProperty(key);
        }

        if (replacement != null) {
          String recursiveReplacement = replaceProperties(replacement, basicProperties, props);
          sbuf.append(recursiveReplacement);
        }

        i = k + DELIM_STOP_LEN;
      }

      if (i == 0) {
        return val;
      } else {
        sbuf.append(val.substring(i, val.length()));
        return sbuf.toString();
      }
    }
  }

  public static String replaceSpecialChars(String s) {
    int len = s.length();
    StringBuffer sbuf = new StringBuffer(len);

    char c;
    for(int i = 0; i < len; sbuf.append(c)) {
      c = s.charAt(i++);
      if (c == '\\' && i < len) {
        c = s.charAt(i++);
        if (c == 'n') {
          c = '\n';
        } else if (c == 'r') {
          c = '\r';
        } else if (c == 't') {
          c = '\t';
        } else if (c == 'f') {
          c = '\f';
        } else if (c == '\b') {
          c = '\b';
        } else if (c == '"') {
          c = '"';
        } else if (c == '\'') {
          c = '\'';
        } else if (c == '\\') {
          c = '\\';
        }
      }
    }

    return sbuf.toString();
  }
}
