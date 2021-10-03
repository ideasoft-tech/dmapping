//- Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright  1997 IdeaSoft Uruguay S.R.L.  All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: IEnvUtils.java,v 1.11 2008/11/06 12:45:35 rbotto Exp $
// -----------------------------------------------------------------------
package com.ideasoft.app;

import ideasoft.util.log.Log;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * This class provides a set of methods that are useful to manage the
 * environment, that is, properties such as user's home, etc.
 */

public class IEnvUtils {

	//- Public members

	//- Applet stuff
	public static IEnvUtils getInstance(IEnv iEnv) {
		return getInstance(iEnv, true); 
	}

	public static IEnvUtils getInstance(IEnv iEnv, boolean initialize) {
		return new IEnvUtils(iEnv, initialize); 
	}

	/**
	 * Returns the host where the Applet was loaded from, or null if the application
	 * is not running as an applet.
	 */
	public String getAppletHost() {
		if (iEnv.isApplet()) {
			return iEnv.getApplet().getCodeBase().getHost();
		}
		return null;
	}

	//- Home and HomePath properties

	/**
	 * Returns the home URL for the current Context
	 */
	public String getHome() {
		if (home != null) {
			return home;
		} else {
			String home = iEnv.getProperty("ideasoft.home");
			if (home != null && home.startsWith("http:")) {
				setHome(home);
			} else {
				setHomePath(home);
			}
			return home;
		}
	}

	/**
	 * Sets the home URL.
	 */
	public void setHome(String urlString) {
		String usrHome = null;
		int idx = -1;
		if ((idx = urlString.indexOf("#user_home#")) != -1 && (usrHome = getUsrHomePath(false, null)) != null) {
			String path = usrHome + urlString.substring(idx + "#user_home#".length());
			File file = new File(path);
			if (!file.exists()) {
				if (!file.mkdirs()) {
					Log.warning("Can't create the home directory: " + path + ", setting home to: " + usrHome);
					path = usrHome;					
				}
			}				
			urlString = "file:" + path;
		}

		home = urlString;
		iEnv.getProperties().put("ideasoft.home", urlString);
//		try {
////			homePath = RepositoryResolver.getFile(urlString);
//		} catch (MalformedURLException e) {
//			homePath = null;
//			Log.error("Malformed URL: " + urlString + ". setHome operation rejected.");
//		}
	}

	/**
	 * 
	 * @param workingIdentifier
	 * @return	the working folder according to the specified working identifier defined in the 
	 * working configuration file
	 */
	public String getWorkingFolder(String workingIdentifier) {
//		ensureWorkingAppManager();
//
//		return appWorkingManager.getHome(workingIdentifier);
		return "/";
	}

	/**
	 * 
	 * @param workingIdentifier
	 * @return	the user working folder(a folder relative to the user home) according to the specified workingIdentifier 
	 */
	public String getUserWorkingFolder(String workingIdentifier) {
//		ensureWorkingAppManager();
//
//		return appWorkingManager.getUserFolder(workingIdentifier);
		return "/";
	}
	
	
	/**
	 * Returns the value of property "ideasoft.home".
	 */
	public String getHomePath() {
		if (homePath != null) {
			return homePath;
		}
		String homeProp = iEnv.getProperty("ideasoft.home");
		if (homeProp != null) {
			setHomePath(homeProp);
		}
		return homePath;
	}

	/**
	 * Sets the home path, indicating also that the home URL have the
	 * protocol file.
	 */
	public void setHomePath(String path) {
		setHome(path == null || !path.startsWith("file:") ? "file:" + path : path);

		//ideasoft.home is replaced to file:/path, but I don't want to loose the original path
		//in order to allow replacements (URI=xml:${ideasoft.home.path}/xmls/a.xml)
		iEnv.getProperties().put("ideasoft.home.path", homePath);
	}

	//- User, UserHome and UserPath properties

	public boolean isUserNull() {
		return user == null;
	}

	/**
	 * Returns a string containing user's name.
	 * @return value of property "ideasoft.user". If it's null, it searchs for
	 * system property "user.name".
	 */
	public String getUser() {
		if (user != null) {
			return user;
		} else {
			user = iEnv.getProperty("ideasoft.user");

			if (user == null) {
				try {
					user = System.getProperty("user.name");
				} catch (SecurityException e) {
					user = "<Unknown>";
				}
			}
			return user;
		}
	}

	/**
	 * Returns value of property "user.home" as an URL.
	 */
	public String getUserHome() {
		return getUserHome(null);
	}

	public String getUserHome(String workingIdentifier) {
		return usrHome == null ? ("file:" + getUsrHomePath(workingIdentifier)) : usrHome;
	}

	/**
	 * Returns value of system property "user.home", if it's null, it returns the
	 * value of "ideasoft.home".
	 */
	public String getUsrHomePath() {
		return getUsrHomePath(null);
	}

	/**
	 * 
	 * @param workingIdentifier
	 * @return	the user home according to the specified workingIdentifier(defined in the file homeSpec.xml) 
	 */
	public String getUsrHomePath(String workingIdentifier) {
		return getUsrHomePath(true, workingIdentifier);
	}

	//- Properties management

	public Properties loadProperties(String file) {
		return loadProperties("", file);
	}
		
	/**
	 * Load a properties file and install the properties in the Context.
	 * If one of the properties <b>context.resource.anchor</b> or
	 * <b>context.resource.url</b> is not null, the file is first tried to
	 * open using getInstallResource, if this method fails or the properties
	 * are not set, the file is opened from <b>ideasoft.home</b>
	 * @param file The name of the file inside the installation directory.
	 */
	public Properties loadProperties(String infoTitle, String file) {
		if (iEnv.getProperty(CONTEXT_RESOURCE_ANCHOR) != null || iEnv.getProperty(CONTEXT_RESOURCE_URL) != null) {
			URL url = getInstallResource(file);
			if (url != null) {
				return loadPropertiesFile(infoTitle, url);
			}
		}
		return loadPropertiesFile(infoTitle, getHome(), file);
	}

	/**
	 * Load a properties file from the user home.
	 * @param file The name of the file inside the user home directory.
	 * @return null if the file could not be readed
	 */
	public Properties loadUserProperties(String file) {
		Properties p = null;
		if (/*usrHome*/getUserHome() != null) {
			p = loadPropertiesFile("User", /*usrHome*/getUserHome(), file);
		}
		return p;
	}

	/**
	 * Saves the Global Properties to the given file (<HOME>/file)
	 */
	public void saveGlobalProperties(String file) throws IOException	{
		saveProperties(file, iEnv.getProperties());
	}


	//- Properties facilities

	/**
	 * Retrieves value of given property.
	 * @param name: name of property to retrieve.
	 * @return String value of given property in Context.
	 */
	public String getOption(String name) {
		return iEnv.getProperty(name);
	}

	/**
	 * Retrieves value of given option; if it's null, it returns the given default value.
	 * @param name: name of property to retrieve.
	 * @param defaultValue: nvalue to return in case property is not defined in Context.
	 * @return String value of given property in Context, or the given defaultValue if
	 * property is not in the Context.
	 */
	public String getOption(String name, String defaultValue) {
		return iEnv.getProperty(name, defaultValue);
	}

	/**
	 * Returns the value of the given property.
	 * @param key: the property name
	 * @return java.awt.Color, value of given key, or null if property was not set.
	 */
	public Color getColorProperty(String key) {
		//. ASK: Perhaps is better having always a default Color ...
		return getProperty(key, (Color) null);
	}

	/**
	 * Returns the value of the given property, if it's not null, otherwise returns
	 * given default value.
	 * @param key: the property name
	 * @param defaultValue: value to return in the case that given key is not defined.
	 * @return a Color value.
	 */
	public Color getProperty(String key, Color defaultValue) {
		String p = iEnv.getProperties().getProperty(key);

		if (p == null) {
			return defaultValue;
		}

		try {
			return Color.decode(p);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Returns the boolean value of the given property, or the given default
	 * value if there is no value for the given key.
	 * @param key: name of the property
	 * @param defaultValue: value to return in the case that 'key' is not
	 * defines in the Context.
	 */
	public boolean getBooleanProperty(String key, boolean defaultValue) {
	//	String value = getProperty(key);
		String value = getOption(key);
		return value != null ? Boolean.valueOf(value).booleanValue() : defaultValue;
	}

	/**
	 * Returns the integer value of the given property, or the given default
	 * value if the given key is not mapped in the context.
	 * @param key: name of the property
	 * @param defaultValue: value to return in the case that 'key' is not
	 * defines in the Context.
	 */
	public int getIntegerProperty(String key, int defaultValue) {
	//	String value = getProperty(key);
		String value = getOption(key);

		if (value == null) {
			return defaultValue;
		}

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	//- Miscelaneous

	/**
	 * Returns the full path for the given file, i.e. <HOME>/file
	 * @deprecated Use getInstallResource instead. @see ContextUtils#getInstallResource(String)
	 */
	public String getFullPath(String file) {
		String homePath = getHomePath();
		if (homePath == null) {
			return file;
		} else {
			String path = (homePath.endsWith("/") || homePath.endsWith("\\")) ? homePath : (homePath = homePath + "/");
			return path + file;
		}
	}

	/**
	 * Returns a string containig the path of given file if it is in home directory.
	 */
	public String getFullUserPath(String file) {
		String usrHomePath = getUsrHomePath(); 
		if (usrHomePath != null) {
			String path = (usrHomePath.endsWith("/") || usrHomePath.endsWith("\\")) ? usrHomePath : (usrHomePath = usrHomePath + "/");
			file = path + file;
		}

		return file;
	}
	
	public URL getInstallResource(String file) {
		return getInstallResource(file, null);
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
	public URL getInstallResource(String file, String anchorClassName) {
		URL url = null;
		anchorClassName = anchorClassName == null ? iEnv.getProperty(CONTEXT_RESOURCE_ANCHOR) : anchorClassName;
		if (anchorClassName != null) {
			try {
				Class anchorClass = Class.forName(anchorClassName);
				url = anchorClass.getClassLoader().getResource(file);
				Log.debug("Install Resource URL (anchor): " + url);
			} catch (Exception exc) {
				//Log.debug(exc);
				Log.error("Unable to load anchor class: " + anchorClassName);
			}
		} else {
			String baseURL = iEnv.getProperty(CONTEXT_RESOURCE_URL);
			if (baseURL != null) {
				try {
					URL base = new URL(baseURL);
					url = new URL(base, file);
					Log.debug("Install Resource URL: " + url);
				} catch (Exception exc) {
					Log.debug(exc);
					Log.error("Invalid base URL for resources: " + baseURL);
				}
			}
		}

		if (url == null) {
			try {
				return new URL("file", null, getFullPath(file));
			} catch (Exception exc) {
				Log.debug(exc);
				Log.error("Unable to open the local install resource: " + getFullPath(file));
			}
		}
		return url;
	}

	/**
	 *
	 * @param file
	 * @return a stream opened from the URL returned by  getInstallResource(file), null
	 * if the resource was not found
	 * @throws java.io.IOException
	 * @see ContextUtils#getInstallResource(String)
	 */
	public InputStream getInstallResourceAsStream(String file) throws IOException {
		return getInstallResourceAsStream(file, null);
	}

	public InputStream getInstallResourceAsStream(String file, String anchorClassName) throws IOException {
		URL url = getInstallResource(file, anchorClassName);
		if (url != null) {
			return url.openStream();
		} else {
			return null;
		}
		
	}
	//- Private members

	/**
	 * Save the properties to the specified file at the home base.
	 * If the Context is running like an applet nothing is performed.
	 */
	private void saveProperties(String file, Properties prop) throws IOException	{
		if (iEnv.isApplet() || homePath == null || prop == null) {
			return;
		}

		String fileName = (homePath.endsWith("/") || home.endsWith("\\")) ? homePath : (homePath + "/");
		fileName = fileName + file;

		FileOutputStream fos = new FileOutputStream(fileName);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		prop.save(bos, "");

		bos.close();
		fos.close();
	}

	/**
	 * Reads the given file from the given home directory, and loads the properties
	 * defined in that file.
	 * @param home: directory in which the given file is searched for.
	 */
	private Properties loadPropertiesFile(String infoTitle, String home, String file) {
		//if (home == null || iEnv.isApplet()) { //Unable to locate the file
		if (home == null) { //Unable to locate the file
			return new Properties();
		}

		String base = (home.endsWith("/") || home.endsWith("\\")) ? home : (home + "/");

		if (base.startsWith("file:") && iEnv.isApplet()) {
			return new Properties();
		}

		URL url = null;
		try {
			url = new URL(base + file);
		} catch (MalformedURLException exc) {
			exc.printStackTrace();
			System.err.println("WARNING: Invalid URL: " + (base + file));
			return new Properties();
		}

		return loadPropertiesFile(infoTitle, url);
	}

	private Properties loadPropertiesFile(String infoTitle, URL url) {
		infoTitle = infoTitle == null || infoTitle.equals("") ? " " : " " + infoTitle + " ";
		System.out.println("Loading" + infoTitle +  "properties file from: " + url);

		Properties properties = new Properties();

		try {
			properties.load(url.openStream());
			for (Enumeration e = properties.keys(); e.hasMoreElements();) {
				String xKey = (String) e.nextElement();
				properties.put(xKey, ((String)properties.get(xKey)).trim());
			}
		} catch (IOException e) {
			return properties;
		}

		if (getBooleanProperty("properties.replacement", true)) {
			PropertiesUtilities.replaceAllProperties(iEnv.getProperties(), properties);
		}

		return properties;
	}

	/**
	 * Concatenates the given arrays; this method is useful when extending a
	 * BasicApplication, and recognition of more parameters is needed (BasicApplication
	 * defines some parameters such as 'help').

	 */
	public String[][] concatParameters(String[][] params1, String[][] params2) {
		int len1 = params1.length;
		int len2 = params2.length;

		String[][] result = new String[len1 + len2][];
		System.arraycopy(params1, 0, result, 0, len1);
		System.arraycopy(params2, 0, result, len1, len2);

		return result;
	}

	public void init() {
		getHome();
		getHomePath();
		getUser();
		getUserHome();
		getUsrHomePath();
	}	
	
	private String getUsrHomePath(boolean useIsoftHome, String workingIdentifier) {
		if (usrHomePath == null) {
			try {
				usrHomePath = System.getProperty("user.home");
			} catch (SecurityException exc) {
				if (useIsoftHome) {
					System.err.println("Unable to obtain the user home, using ideasoft.home");
					usrHomePath = getHomePath();
					//MR: I don't understand the return below, it makes no sense
					//return null;
				}
			}

			String userFolder = getUserWorkingFolder(workingIdentifier);
			if (userFolder != null && !userFolder.equals("")) {
				usrHomePath = usrHomePath + (usrHomePath.endsWith("/") || usrHomePath.endsWith("\\") ? userFolder : "/" + userFolder);			
			}
			
		}
		
		return usrHomePath;
	}

	private IEnvUtils(IEnv iEnv, boolean initialize) {		
		this.iEnv = iEnv;
		if (initialize) {
			init();
		}
	}


	/** The string representation of the home URL */
	String home;
	/** The string representation of the home path, it is not an URL */
	String homePath;

	String user;
	String usrHome;
	String usrHomePath;
	
	private IEnv iEnv;


	private static final String CONTEXT_RESOURCE_ANCHOR = "context.resource.anchor";
	private static final String CONTEXT_RESOURCE_URL = "context.resource.url";
}

