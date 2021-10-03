//- Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright  1997 IdeaSoft Uruguay S.R.L.  All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ContextUtils.java,v 1.28 2005/08/16 21:32:01 ameyer Exp $
// -----------------------------------------------------------------------

package com.ideasoft.app;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * This class provides a set of static methods that are useful to manage the
 * environment, that is, properties such as user's home, etc.
 */

public class ContextUtils {

	//- Public members

	//- Applet stuff

	/**
	 * Returns the host where the Applet was loaded from, or null if the application
	 * is not running as an applet.
	 */
	public static String getAppletHost() {
		return getThreadUtils().getAppletHost();
	}

	//- Home and HomePath properties

	/**
	 * Returns the home URL for the current Context
	 */
	public static String getHome() {
		return getThreadUtils().getHome();
	}

	/**
	 * Sets the home URL.
	 */
	public static void setHome(String urlString) {
		getThreadUtils().setHome(urlString);
	}

	public static String getWorkingFolder(String workingIdentifier) {
		return getThreadUtils().getWorkingFolder(workingIdentifier);
	}
	public String getUserWorkingFolder(String workingIdentifier) {
		return getThreadUtils().getUserWorkingFolder(workingIdentifier);
	}
	
	/**
	 * Returns the value of property "ideasoft.home".
	 */
	public static String getHomePath() {
		return getThreadUtils().getHomePath();
	}

	/**
	 * Sets the home path, indicating also that the home URL have the
	 * protocol file.
	 */
	public static void setHomePath(String path) {
		getThreadUtils().setHomePath(path);
	}


	//- User, UserHome and UserPath properties

	public static boolean isUserNull() {
		return getThreadUtils().isUserNull();
	}

	/**
	 * Returns a string containing user's name.
	 * @return value of property "ideasoft.user". If it's null, it searchs for
	 * system property "user.name".
	 */
	public static String getUser() {
		return getThreadUtils().getUser();
	}

	/**
	 * Returns value of property "user.home" as an URL.
	 */
	public static String getUserHome() {
		return getThreadUtils().getUserHome();
	}

	/**
	 * Returns value of property "user.home" as an URL according to the workingIdentifier.
	 */
	public static String getUserHome(String workingIdentifier) {
		return getThreadUtils().getUserHome(workingIdentifier);
	}

	/**
	 * Returns value of system property "user.home", if it's null, it returns the
	 * value of "ideasoft.home".
	 */
	public static String getUsrHomePath() {
		return getThreadUtils().getUsrHomePath();
	}

	/**
	 * Returns value of system property "user.home" according to the workingIdentifier, if it's null, it returns the
	 * value of "ideasoft.home".
	 */
	public static String getUsrHomePath(String workingIdentifier) {
		return getThreadUtils().getUsrHomePath(workingIdentifier);
	}


	//- Properties management

	/**
	 * Load a properties file and install the properties in the Context.
	 * If one of the properties <b>context.resource.anchor</b> or
	 * <b>context.resource.url</b> is not null, the file is first tried to
	 * open using getInstallResource, if this method fails or the properties
	 * are not set, the file is opened from <b>ideasoft.home</b>
	 * @param file The name of the file inside the installation directory.
	 */
	public static Properties loadProperties(String file) {
		return loadProperties("", file);
	}

	public static Properties loadProperties(String infoTitle, String file) {
		return getThreadUtils().loadProperties(infoTitle, file);
	}

	/**
	 * Load a properties file from the user home.
	 * @param file The name of the file inside the user home directory.
	 * @return null if the file could not be readed
	 */
	public static Properties loadUserProperties(String file) {
		return getThreadUtils().loadUserProperties(file);
	}

	/**
	 * Saves the Global Properties to the given file (<HOME>/file)
	 */
	public static void saveGlobalProperties(String file) throws IOException	{
		getThreadUtils().saveGlobalProperties(file);
	}


	//- Properties facilities

	/**
	 * Retrieves value of given property.
	 * @param name: name of property to retrieve.
	 * @return String value of given property in Context.
	 */
	public static String getOption(String name) {
		return getThreadUtils().getOption(name);
	}

	/**
	 * Retrieves value of given option; if it's null, it returns the given default value.
	 * @param name: name of property to retrieve.
	 * @param defaultValue: nvalue to return in case property is not defined in Context.
	 * @return String value of given property in Context, or the given defaultValue if
	 * property is not in the Context.
	 */
	public static String getOption(String name, String defaultValue) {
		return getThreadUtils().getOption(name, defaultValue);
	}

	/**
	 * Returns the value of the given property.
	 * @param key: the property name
	 * @return java.awt.Color, value of given key, or null if property was not set.
	 */
	static public Color getColorProperty(String key) {
		//. ASK: Perhaps is better having always a default Color ...
		return getThreadUtils().getColorProperty(key);
	}

	/**
	 * Returns the value of the given property, if it's not null, otherwise returns
	 * given default value.
	 * @param key: the property name
	 * @param defaultValue: value to return in the case that given key is not defined.
	 * @return a Color value.
	 */
	static public Color getProperty(String key, Color defaultValue) {
		return getThreadUtils().getProperty(key, defaultValue);
	}

	/**
	 * Returns the boolean value of the given property, or the given default
	 * value if there is no value for the given key.
	 * @param key: name of the property
	 * @param defaultValue: value to return in the case that 'key' is not
	 * defines in the Context.
	 */
	public static boolean getBooleanProperty(String key, boolean defaultValue) {
		return getThreadUtils().getBooleanProperty(key, defaultValue);
	}

	/**
	 * Returns the integer value of the given property, or the given default
	 * value if the given key is not mapped in the context.
	 * @param key: name of the property
	 * @param defaultValue: value to return in the case that 'key' is not
	 * defines in the Context.
	 */
	public static int getIntegerProperty(String key, int defaultValue) {
		return getThreadUtils().getIntegerProperty(key, defaultValue);
	}

	//- Miscelaneous

	/**
	 * Returns the full path for the given file, i.e. <HOME>/file
	 * @deprecated Use getInstallResource instead. @see ContextUtils#getInstallResource(String)
	 */
	public static String getFullPath(String file) {
		return getThreadUtils().getFullPath(file);
	}

	/**
	 * Returns a string containig the path of given file if it is in home directory.
	 */
	public static String getFullUserPath(String file) {
		return getThreadUtils().getFullUserPath(file);
	}

	/**
	 * Returns an URL for the specified resource on the installation dir, the resource may
	 * be located in a jar file (as a resource) or in a base URL.
	 * <p>To load the resource from a jar file, an anchor class must be specified, then the resource
	 * will be obtained using Class.getResource(file), the anchor class may be specified
	 * with the property <b>context.resource.anchor</b>
	 * <p>The base url may be specified with the property <b>context.resource.url</b>
	 * <p>By default this method returns a new URL for the same file as returned by getFullPath(file).
	 * @param file the name of the file, may be a relative path, for example: config/cfg.xml
	 * @return An URL for the specified resource, null if the resource is not found.
	 */
	public static URL getInstallResource(String file) {
		return getInstallResource(file, null);
	}

	public static URL getInstallResource(String file, String anchorClassName) {
		return getThreadUtils().getInstallResource(file, anchorClassName);
	}

	/**
	 *
	 * @param file
	 * @return a stream opened from the URL returned by  getInstallResource(file), null
	 * if the resource was not found
	 * @throws java.io.IOException
	 * @see ContextUtils#getInstallResource(String)
	 */
	public static InputStream getInstallResourceAsStream(String file) throws IOException {
		return getInstallResourceAsStream(file, null);
	}

	public static InputStream getInstallResourceAsStream(String file, String anchorClassName) throws IOException {
		return getThreadUtils().getInstallResourceAsStream(file, anchorClassName);
	}

	//- Private members
	/**
	 * Concatenates the given arrays; this method is useful when extending a
	 * BasicApplication, and recognition of more parameters is needed (BasicApplication
	 * defines some parameters such as 'help').
	 * @see com.ideasoft.app.BasicApplication
	 */
	public static String[][] concatParameters(String[][] params1, String[][] params2) {
		return getThreadUtils().concatParameters(params1, params2);
	}
	
	public static void setThreadIEnvUtils(IEnvUtils iEnvUtils) {
		threadUtils.set(iEnvUtils);	
	}
	
	private static IEnvUtils getThreadUtils() {
		IEnvUtils utils = (IEnvUtils) threadUtils.get();
		return utils == null ? iEnvUtils : utils;
	}

	static IEnvUtils iEnvUtils;
	private static final ThreadLocal threadUtils;
	
	static {
		threadUtils = new ThreadLocal();
		iEnvUtils = IEnvUtils.getInstance(Context.getIEnv(), false);
		iEnvUtils.init();
	}
}

