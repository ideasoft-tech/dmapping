//- Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright  1997 IdeaSoft Uruguay S.R.L.  All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: Context.java,v 1.19 2005/04/18 20:10:48 jrodriguez Exp $
// -----------------------------------------------------------------------

package com.ideasoft.app;

import java.applet.Applet;
import java.util.Hashtable;
import java.util.Properties;
import java.util.TimeZone;

/**
 * The applicaction context.
 * This is a complete static class, whose main purpose is to provide a single
 * point to access environment properties. This allows library classes to
 * reference such properties with independence from different execution and
 * startup environments (as for example app with main or applets).
 *
 * This class also supports the collection of all applications running that
 * is used for applications interoperation.
 *
 */
public
class Context
{

//- Public class members

/**
 * Initialize the app, and if it doesn't fail (returns true), registers the
 * app as running and executes its run method.
 */
public static
int execute(Application app)
{
	if (! app.init()) {
		//if the application has not changed the exitCode return the standard INIT_FAILED
		return app.getExitCode() == Application.EXIT_CODE_OK ? Application.EXIT_CODE_INIT_FAILED : app.getExitCode();
	}
	appRunning(app);
	app.run();
	return app.getExitCode();
}

/**
 * Finalize the application
 */
public static
void externFinalize(Application app)
{
	app.externFinalize();
}

/**
 * Registers the applications as running
 */
public static
void appRunning(Application app)
{
	runningApps.put(app.getClass().getName(), app);
}

/**
 * This method indicates the end of an application, if it was the last running
 * app and it is not an Applet calls a System.exit(0).
 */
public static
void appFinished(Application app)
{
	runningApps.remove(app.getClass().getName());

	if (runningApps.size() == 0 && ! isApplet()) {
		System.exit(0);
	}
}


//- Properties related methods

/**
 * Returns a Properties object containing the gloabal properties, usually accessed
 * through the getProperties() method.
 */
public static
Properties getGlobalProperties()
{
	return globalProperties;
}

/**
 * Returns the value of given property, if such value is null, it returns
 * the given default value.
 */
public static
String getProperty(String key, String defaultValue)
{
	return getThreadIEnv().getProperty(key, defaultValue);
}

/**
 * Returns the value of the given property, that is searched in the follwowing
 * order: user, app, default.
 * @return The value for a property.
 */
public static
String getProperty(String key)
{
	return getThreadIEnv().getProperty(key);
}

/**
 * Returns the value of the parameter; command line parameters are trated as
 * properties, giving them the property names arg0, arg1, ...
*/
public static
String getParameter(int number)
{
	return getThreadIEnv().getParameter(number);
}

/**
 * Returns the value of the n-th parameter as a string, if value is null, it
 * returns the given defaultValue.
 * @param number: index of paramater to retrieve.
 * @param defaultValue: value to retrieve in case value is null.
 */
public static
String getParameter(int number, String defaultValue)
{
	return getThreadIEnv().getParameter(number, defaultValue);
}

/**
 * @return total number of parameters.
*/
public static
int getParametersCount()
{
	return getThreadIEnv().getParametersCount();
}

/**
 * Returns the default time zone for this host.
 * @return the default TimeZone for this host.
*/
public static
TimeZone getDefaultTimeZone()
{
	return defaultTimeZone;
}

/**
 * Set # of parameters
 */
public static
int setParametersCount(int count)
{
	return getThreadIEnv().setParametersCount(count);
}


//- Applet related

/**
 * Shows if application is being run as applet or not.
 * @return true if application is being executed through an applet, false otherwise.
 */
public static
boolean isApplet()
{
	return getThreadIEnv().isApplet();
}

/**
 * Returns the applet that is executing the application.
 */
public static
Applet getApplet()
{
	return getThreadIEnv().getApplet();
}

/**
 * Sets the given applet, indicating that application is being executed through
 * an applet.
 */
public static
Applet setApplet(Applet app)
{
	return getThreadIEnv().setApplet(app);
}


//- Miscelaneous

/**
 * Add the properties in 'prop' to 'dest'.
 */
public static
void addProperties(Properties dest, Properties prop)
{
	getThreadIEnv().addProperties(dest, prop);
}

/**
 * Change the internal properties
 * @param properties
 */
public static void setProperties(Properties p) {
	getThreadIEnv().setProperties(p);
	if (iEnv == getThreadIEnv()) {
		globalProperties = iEnv.getProperties();
	}
}

/**
 * Sets the value of the specified property
 */
public static void setProperty(String property, String value) {
	getThreadIEnv().setProperty(property, value);
}

/**
 * @return the reference to the internal IEnv object that provides
 * the properties management support
 */
public static IEnv getIEnv() {
	IEnv env = (IEnv) threadIEnv.get();
	return env == null ? iEnv : env;
}

/**
 * @return the number of running applications
 */
public static int getRunningAppCount() {
	if (runningApps != null) {
		return runningApps.size();
	}
	return 0;
}

public static void setThreadIEnv(IEnv iEnv) {
	threadIEnv.set(iEnv);
}

private static IEnv getThreadIEnv() {
	IEnv env = (IEnv) threadIEnv.get();
	return env == null ? iEnv : env;
}

//- Package fields

static Hashtable runningApps;
static Properties globalProperties;

public static TimeZone defaultTimeZone = TimeZone.getDefault();

private static final IEnv iEnv;

private static final ThreadLocal threadIEnv;

static {
	Properties properties;
	try {
		//for compatibility with Context
		properties = System.getProperties();
	} catch (SecurityException exc) {
		System.err.println("Unable to load System properties: " + exc);
		properties = new Properties();
	}
	PropertiesUtilities.replaceAllProperties(null, properties);
	iEnv = new IEnv();
	//for compatibility, set the IEnv properties as the same reference on System
	iEnv.properties = properties;
	runningApps = new Hashtable();
	globalProperties = properties;
	threadIEnv = new ThreadLocal();
}
}
