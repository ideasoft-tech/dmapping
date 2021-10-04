//- Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright  1997 IdeaSoft Uruguay S.R.L.  All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: I18N.java,v 1.17 2006/10/27 19:42:52 epiris Exp $
// -----------------------------------------------------------------------

package ideasoft.util;

import ideasoft.util.bundle.ResourceBundle;
import ideasoft.util.log.Log;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;


/**
 * A convenience class to deal with internationalization. The class internationalizates itself throw the standard bundle.
 */
public class I18N {

  private ClassLoader i18nLoader;

// public instance members

  /**
   * The string that has the current location of the bundle.
   */
  private String propertyLocation;

  /**
   * The current bundle.
   */
  private ResourceBundle bundle;

  private Locale locale;

  private Map<Locale, Object> bundleMap = new HashMap<Locale, Object>();

  private boolean warningLogEnabled = true;

  /**
   * The standard bundle.
   */
  private static ResourceBundle standard;

  /**
   * The location of the standard.properties file.
   */
  private static final String STANDARD_LOCATION = "ideasoft.util.standard";

  /**
   * The mnemonic postfix.
   */
  private static final String MNEMONIC_POSTFIX = "MN";

  private static ThreadLocal<Locale> threadLocal = new ThreadLocal<Locale>();

  /**
   * Static initializer for I18N.
   * By the moment the standard bundle MUST be created, otherwise, the program will abort.
   * It's the only expected reason to abort.
   * I18N registers itself as LogListener to report possibles key or file loses.
   */
  static {
    try {
      standard = ResourceBundle.getBundle(STANDARD_LOCATION, Locale.getDefault(), I18N.class.getClassLoader());
    } catch (MissingResourceException e) {
      System.out.println("ideasoft.util.I18N: can't find standard properties");
//      System.exit(1);
    }
  }

  /**
   * Constructs an I18N and creates a bundle if it's possible. If it's not, logs a warning message, but it's tolerated.
   *
   * @param propertyLocation The location of the property file in the CLASSPATH. It must be specified with the package notation (i.e:
   *                         ideasoft.util.standard, for a "standard.properties" file).
   */
  public I18N(String propertyLocation) {
    this(propertyLocation, (Locale) null);
  }

  public I18N(String propertyLocation, ClassLoader classLoader) {
    this(propertyLocation, null, true, classLoader);
  }

  /**
   * Constructs an I18N and creates a bundle if it's possible. If it's not, logs a warning message, but it's tolerated.
   *
   * @param propertyLocation The location of the property file in the CLASSPATH. It must be specified with the package notation (i.e:
   *                         ideasoft.util.standard, for a "standard.properties" file).
   * @param locale           The <code>Locale</code> instance to be used, can be independent of the system locale.
   */
  public I18N(String propertyLocation, Locale locale) {
    this(propertyLocation, locale, true);
  }

  public I18N(String propertyLocation, Locale locale, boolean warningLogEnabled) {
    this.warningLogEnabled = warningLogEnabled;
    initFrom(propertyLocation, locale, null);
  }

//- Public class members

  public I18N(String propertyLocation, Locale locale, boolean warningLogEnabled, ClassLoader classLoader) {
    this.warningLogEnabled = warningLogEnabled;
    i18nLoader = classLoader;
    initFrom(propertyLocation, locale, classLoader);
  }

  /**
   * Constructs an I18N object from the propertyLocation, the resource is not loaded if it's included in the hash, if not is included such
   * resource is added to.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public I18N(Hashtable hash, String propertyLocation) {
    Object r = hash.get(propertyLocation);
    if (r == null) {
      initFrom(propertyLocation, null, null);
      if (bundle != null) {
        hash.put(propertyLocation, bundle);
      }
    } else {
      bundle = (ResourceBundle) r;
      this.propertyLocation = propertyLocation;
    }
  }

  /**
   * This method returns the internationalizated expresion that correspond to key. If it's not found in the current bundle, is searched in
   * the standard bundle. If it's not found in the standard bundle, the passed key is returned and logs a warning message.
   *
   * @param key the internationalizated expresion to look for.
   */
  public String getString(String key) {
    ResourceBundle b = getBundle();
    if (b != null) {
      try {
        return b.getString(key);
      } catch (MissingResourceException mre) {
        try {
          return standard.getString(key);
        } catch (MissingResourceException mre2) {
          if (warningLogEnabled) {
            Log.warning(key + " " + getStdString("notFoundIn") + " " + propertyLocation);
          }
          return key;
        }
      }
    } else {
      return key;
    }
  }

  /**
   * A convenient way to obtain a complex internationalizated expression. This method returns the internationalizated expresion that
   * correspond to the key / arguments combination. It defines a pattern to use throw the standard getString(key), and then applys the
   * static format method of MessageFormat with arguments.
   *
   * @param key       is used to obtain the pattern.
   * @param arguments this array is passed among the pattern obtained.
   */
  public String getString(String key, Object[] arguments) {
    String pattern = getString(key);
    if (key != pattern) {
      return MessageFormat.format(pattern, arguments);
    } else {
      return key + arguments;
    }
  }

  public String getString(String key, Object argument) {
    return getString(key, new Object[]{argument});
  }


//- Private instance members

  public String getString(String key, Object argument1, Object argument2) {
    return getString(key, new Object[]{argument1, argument2});
  }

  public String getString(String key, Object argument1, Object argument2, Object argument3) {
    return getString(key, new Object[]{argument1, argument2, argument3});
  }

  /**
   * This method is a convenience to get the internationalizated mnemonic that correspond to key. It is assumed that the mnemonic in the
   * bundle has the form: key + MNEMONIC_POSTFIX If it's not found in the current bundle, is searched in the standard bundle. If it's not
   * found in the standard bundle, the first character of the passed key is returned and logs a warning message.
   *
   * @param key the internationalizated mnemonic to look for.
   */
  public char getMnemonic(String key) {
    return getString(key + MNEMONIC_POSTFIX).charAt(0);
  }

  public boolean isWarningLogEnabled() {
    return warningLogEnabled;
  }

  public void setWarningLogEnabled(boolean value) {
    warningLogEnabled = value;
  }

  /**
   * Initializes the object reading the properties from the given location.
   */
  private void initFrom(String propertyLocation, Locale locale, ClassLoader classLoader) {
    this.propertyLocation = propertyLocation;
    this.locale = locale;
    bundle = loadBundle(locale, classLoader);
  }

//- Private fields

  private ResourceBundle loadBundle(Locale locale, ClassLoader classLoader) {
    try {
      return ResourceBundle.getBundle(propertyLocation, (locale == null ? getDefaultLocale() : locale),
          classLoader == null ? getClass().getClassLoader() : classLoader);
    } catch (MissingResourceException mre) {
      Log.warning(getStdString("noProperty") + " " + propertyLocation);
      return null;
    }
  }

  private ResourceBundle getBundle() {
    Locale threadLocale = getThreadLocale();
    if (threadLocale == null || threadLocale.equals(this.locale)) {
      return this.bundle;
    }
    synchronized (this) {
      Object o = bundleMap.get(threadLocale);
      if (o instanceof Boolean) {
        return null;
      }
      ResourceBundle b = loadBundle(threadLocale, i18nLoader);
      if (b == null) {
        //mark the bundle as not found, to avoid future invocations to loadBundle
        bundleMap.put(threadLocale, Boolean.FALSE);
        return null;
      }
      bundleMap.put(threadLocale, b);
      return b;
    }
  }

  /**
   * A convenient way to access standard internationalized expresions. No bundle than the static standard needs to be created.
   *
   * @param key the internationalizated standard expresion to look for.
   */
  public static String getStdString(String key) {
    try {
      if (standard == null) {
        return key;
      } else {
        return standard.getString(key);
      }
    } catch (MissingResourceException mre) {
      Log.warning(key + " std " + standard.getString("notFoundIn") + " " + STANDARD_LOCATION);
      return key;
    }
  }

  public static String format(String pattern, Object[] arguments) {
    return MessageFormat.format(pattern, arguments);
  }

  public static String format(String template, Object argument1) {
    return format(template, new Object[]{argument1});
  }

//- Private fields

  public static String format(String template, Object argument1, Object argument2) {
    return format(template, new Object[]{argument1, argument2});
  }

  public static String format(String template, Object argument1, Object argument2, Object argument3) {
    return format(template, new Object[]{argument1, argument2, argument3});
  }

  private static Locale getDefaultLocale() {
    Locale threadLocale = getThreadLocale();
    return (threadLocale == null ? Locale.getDefault() : threadLocale);
  }

  public static Locale getThreadLocale() {
    return (Locale) threadLocal.get();
  }

//- Static initialization

  public static void setThreadLocale(Locale locale) {
    threadLocal.set(locale);
  }

};