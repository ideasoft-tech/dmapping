//- Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright  1997 IdeaSoft Uruguay S.R.L.  All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: Log.java,v 1.24 2005/05/06 20:58:44 jfradiletti Exp $
// -----------------------------------------------------------------------

package ideasoft.util.log;

import com.ideasoft.app.Context;
import com.ideasoft.app.ContextUtils;
import ideasoft.util.I18N;

/**
 * Implements log facilities.
 * Broadcast every given message to all registered LogListeners.
 */
public
class Log
{

//- Public static members

/* Message kinds */
public static final int PANIC = 0;
public static final int ERROR = 1;
public static final int WARNING = 2;
public static final int NOTICE = 3;
public static final int PHASE = 4;
public static final int TRACE = 5;
public static final int DEBUG = 6;

/**
 * Panic message.
 * Used when something really serious was detected.
 * This method only log the message and takes no further action.
 */
public static
void panic(String msg) {
}

/**
 * Error message.
 */
public static
void error(String msg)
{
}

/**
 * Warning message.
 * Used to log a message that cannot be considered precisely as an error.
 *
 */
public static
void warning(String msg) {
}


/**
 * Notice message.
 * Used to log an information message.
 */
	public static void notice (String msg)
	{
	}

/**
 * Phase message.
 * Used to log the start of a new phase in the actual.
 */
	public static void phase (String msg)
	{
	}

/**
 * Trace message.
 * Used only to log messages for debugging purposes.
 */
	public static void trace (String msg)
	{
	}

/**
 * Trace message.
 * Used only to log messages for debugging purposes.
 */
	public static void debug (String msg)
	{
	}

/**
 * If debug is enabled sends a LogEvent containing a message containing
 * the message followed by the stack trace of the exception
 */
	public static void debug (String msg, Throwable e)
	{
	}

	public static void logStackTrace (Throwable e,int logLevel)
	{
	}

	public static void logStackTrace (String msg, Throwable e, int logLevel)
	{
	}

	public static void debug (Throwable e)
	{
	}


	public static void setVerboseLevel ( int level)
	{
	}

	public static int getVerboseLevel () {
		return 0;
	}

	public static final boolean isLogLevelEnabled ( int level)
	{
		return false;
	}

}