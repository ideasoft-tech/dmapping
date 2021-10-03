//- Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright  1997 IdeaSoft Uruguay S.R.L.  All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ResourceBundle.java,v 1.3 2005/06/17 17:13:04 mlopez Exp $
// -----------------------------------------------------------------------

package ideasoft.util.bundle;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Vector;


abstract public
class ResourceBundle
{

	/**
	 * Get an object from a ResourceBundle.
	 * <BR>Convenience method to save casting.
	 * @param key see class description.
	 */
public final
String getString(String key)
	throws MissingResourceException
{
	return (String) getObject(key);
}

	/**
	 * Get an object from a ResourceBundle.
	 * <BR>Convenience method to save casting.
	 * @param key see class description.
	 */
public final
String[] getStringArray(String key)
	throws MissingResourceException
{
	return (String[]) getObject(key);
}

	/**
	 * Get an object from a ResourceBundle.
	 * @param key see class description.
	 */
public final
Object getObject(String key)
	throws MissingResourceException
{
	Object obj = handleGetObject(key);
	if (obj == null) {
		if (parent != null) {
			obj = parent.getObject(key);
		}
		if (obj == null) {
			throw new MissingResourceException("Can't find resource", this.getClass().getName(), key);
		}
	}
	return obj;
}


	/**
	 * Get the appropriate ResourceBundle subclass.
	 * @param baseName see class description.
	 */
/*
public static final
ResourceBundle getBundle(String baseName)
	throws MissingResourceException
{
	return getBundle(baseName, Locale.getDefault(), getLoader());

}
*/

	/**
	 * Get the appropriate ResourceBundle subclass.
	 * @param baseName see class description.
	 * @param locale   see class description.
	 */
/*
public static final
ResourceBundle getBundle(String baseName, Locale locale)
{
	return getBundle(baseName, locale, getLoader());
}
*/


	/*
	 * Automatic determination of the ClassLoader to be used to load
	 * resources on behalf of the client.  N.B. The client is getLoader's
	 * caller's caller.
	 */
/*
private static
ClassLoader getLoader()
{
	Class[] stack = getClassContext();
	// Magic number 2 identifies our caller's caller
	Class c = stack[2];
	ClassLoader cl = (c == null) ? null : c.getClassLoader();
	return (cl == null) ? systemClassLoader : cl;
}

private static native Class[] getClassContext();
*/
private static SystemClassLoader systemClassLoader = null;
	/**
	 * Get the appropriate ResourceBundle subclass.
	 * @param baseName see class description.
	 * @param locale see class description.
	 */
public static synchronized
ResourceBundle getBundle(String baseName, Locale locale, ClassLoader loader)
	throws MissingResourceException
{
	StringBuffer localeName	= new StringBuffer("_").append(locale.toString());
	if (locale.toString().equals("")) {
		localeName.setLength(0);
	}
	if (loader == null) {
		try {
			if (systemClassLoader == null) {
				systemClassLoader = new SystemClassLoader();
			}
			loader = systemClassLoader;
		} catch (Exception e) {
			throw new MissingResourceException("Unable to find a ClassLoader", baseName + "_" + locale,"");
		}
	}

	ResourceBundle lookup = findBundle(baseName,localeName,loader,!defaultToLocale);
	if(lookup == null) {
		localeName.setLength(0);
		localeName.append("_").append( Locale.getDefault().toString() );
		lookup = findBundle(baseName, localeName, loader, true);
		if( lookup == null ) {
			throw new MissingResourceException("can't find resource for " + baseName + "_" + locale, baseName + "_" + locale,"");
		}
	}

	// Setup lookup's ancestry. If we find an ancestor whose parent is null,
	// we set up the ancestor's parent as well.
	ResourceBundle child = lookup;
	while( (child != null) && (child.parent == null) ) {
		// Chop off the last component of the locale name and search for that
		// as the parent locale.  Use it to set the parent of current child.
		int lastUnderbar = localeName.toString().lastIndexOf('_');
		if( lastUnderbar != -1 ) {
			localeName.setLength(lastUnderbar);
			debug("Searching for parent " + baseName + localeName);
			child.setParent( findBundle(baseName,localeName,loader,true) );
		}
		child = child.parent;
	}
	return lookup;
}


	/**
	 * Set the parent bundle of this bundle.  The parent bundle is
	 * searched by getObject when this bundle does not contain a
	 * particular resource.
	 * @param parent this bundle's parent bundle.
	 */
protected
void setParent( ResourceBundle parent )
{
	this.parent = parent;
}


	/**
	 * The internal routine that does the real work of finding and loading
	 * the right ResourceBundle for a given name and locale.
	 */
private static
ResourceBundle findBundle(String baseName, StringBuffer localeName, ClassLoader loader, boolean includeBase)
{
	String localeStr = localeName.toString();
	String baseFileName = baseName.replace('.', '/');
	Object lookup = null;
	String searchName;
	Vector cacheCandidates = new Vector();
	int lastUnderbar;
	InputStream stream;

	debug("Searching with localeStr " + localeStr);
	if (optimizeSearch) {
		int fUnderbarIdx = localeStr.indexOf("_");
		int lUnderbarIdx = localeStr.lastIndexOf("_");
		while (fUnderbarIdx != -1 && fUnderbarIdx != lUnderbarIdx) {
			// We want to avoid localization
			localeStr = localeStr.substring(0,lUnderbarIdx);
			localeName.setLength(lUnderbarIdx);
			fUnderbarIdx = localeStr.indexOf("_");
			lUnderbarIdx = localeStr.lastIndexOf("_");
		}
	}
	debug("PostProcessing with localeStr " + localeStr);
	searchLoop:
	while (true) {
		searchName = baseName + localeStr;
		String cacheName =	"["+Integer.toString(loader.hashCode())+"]" + searchName;

		// First, look in the cache.  We may either find the bundle we're
		// looking for or we may find that the bundle was not found by a
		// previous search.
		lookup = cacheList.get(cacheName);
		if( lookup == NOTFOUND ) {
			debug("Found " + searchName + " in cache as NOTFOUND");
			localeName.setLength(0);
			break searchLoop;
		}
		if( lookup != null ) {
			debug("Found " + searchName + " in cache");
			localeName.setLength(0);
			break searchLoop;
		}
		cacheCandidates.addElement( cacheName );

		// Next search for a Properties file.
		searchName = baseFileName + localeStr + ".properties";
		debug("Searching for " + searchName );
		stream = loader.getResourceAsStream(searchName);
		if( stream != null ) {
			// make sure it is buffered
			stream = new java.io.BufferedInputStream(stream);
			try {
				lookup = (Object)new PropertyResourceBundle( stream );
				break searchLoop;
			} catch (Exception e) {}
		}

		//Chop off the last part of the locale name string and try again.
		lastUnderbar = localeStr.lastIndexOf('_');
		if( ((lastUnderbar==0)&&(!includeBase)) || (lastUnderbar == -1) ) {
			break;
		}
		localeStr = localeStr.substring(0,lastUnderbar);
		localeName.setLength(lastUnderbar);
	}


	if( lookup != null ) {
		// Add a positive result to the cache. The result may include
		// NOTFOUND
		for( int i=0; i<cacheCandidates.size(); i++ ) {
			cacheList.put(cacheCandidates.elementAt(i), lookup);
			debug("Adding " + cacheCandidates.elementAt(i) + " to cache" + ((lookup == NOTFOUND)?" as NOTFOUND.":"."));
		}
	} else {
		// If we searched all the way to the base, then we can add
		// the NOTFOUND result to the cache.  Otherwise we can say
		// nothing.
		if( includeBase == true ) {
			for( int i=0; i<cacheCandidates.size(); i++ ) {
				cacheList.put(cacheCandidates.elementAt(i), NOTFOUND);
				debug("Adding " + cacheCandidates.elementAt(i) + " to cache as NOTFOUND.");
			}
		}
	}

	if( (lookup == NOTFOUND) || (lookup == null) ) {
		return null;
	} else {
		return (ResourceBundle)lookup;
	}
}


	/** Get an object from a ResourceBundle.
	 * <STRONG>NOTE: </STRONG>Subclasses must override.
	 * @param key see class description.
	 */
protected abstract
Object handleGetObject(String key)
	throws MissingResourceException;

	/**
	 * Return an enumeration of the keys.
	 * <STRONG>NOTE: </STRONG>Subclasses must override.
	 */
public abstract
Enumeration getKeys();

	/**
	 * For printf debugging.
	 */
private static boolean debugFlag = false;

private static
void debug(String str)
{
	if( debugFlag ) {
		System.out.println("ResourceBundle: " + str);
	}
}

	/**
	 * The parent bundle is consulted by getObject when this bundle
	 * does not contain a particular resource.
	 */
protected ResourceBundle parent = null;

private static final Integer NOTFOUND = new Integer(-1);
private static Hashtable cacheList = new Hashtable();
//private static final boolean optimizeSearch = Context.getBooleanProperty("ideasoft.bundle.optimizeSearch", true);
private static final boolean optimizeSearch = true;

private static final boolean defaultToLocale = Boolean.valueOf(System.getProperty("ideasoft.bundle.defaultToLocale")).booleanValue();

}


/**
 * The SystemClassLoader loads system classes (those in your classpath).
 * This is an attempt to unify the handling of system classes and ClassLoader
 * classes.
 */
class SystemClassLoader
	extends ClassLoader
{

protected
Class loadClass( String name, boolean resolve )
	throws ClassNotFoundException
{
	return findSystemClass( name );
}

public
InputStream getResourceAsStream(String name)
{
	return ClassLoader.getSystemResourceAsStream(name);
}

public
java.net.URL getResource(String name)
{
	return ClassLoader.getSystemResource(name);
}
}
