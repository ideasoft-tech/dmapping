//- Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright  1997 IdeaSoft Uruguay S.R.L.  All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ILog.java,v 1.4 2004/05/14 18:20:36 martin Exp $
// -----------------------------------------------------------------------

package ideasoft.util.log;

import ideasoft.util.I18N;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ILog acts as a container for LogListeners
 */
public class ILog {

//- Public static members
/* Message kinds */
public static final int PANIC = Log.PANIC;
public static final int ERROR = Log.ERROR;
public static final int WARNING = Log.WARNING;
public static final int NOTICE = Log.NOTICE;
public static final int PHASE = Log.PHASE;
public static final int TRACE = Log.TRACE;
public static final int DEBUG = Log.DEBUG;

/**
 * Panic message.
 * Used when something really serious was detected.
 * This method only log the message and takes no further action.
 */
public
void panic(String msg)
{

}

/**
 * Error message.
 */
public
void error(String msg)
{
}

/**
 * Warning message.
 * Used to log a message that cannot be considered precisely as an error.
 *
 */
public
void warning(String msg)
{
}

/**
 * Notice message.
 * Used to log an information message.
 */
public
void notice(String msg)
{
}

/**
 * Phase message.
 * Used to log the start of a new phase in the actual.
 */
public
void phase(String msg)
{
}

/**
 * Trace message.
 * Used only to log messages for debugging purposes.
 */
public
void trace(String msg)
{
}

/**
 * Trace message.
 * Used only to log messages for debugging purposes.
 */
public
void debug(String msg)
{
}

/**
 * If debug is enabled sends a LogEvent containing a message containing
 * the message followed by the stack trace of the exception
 */
public
void debug(String msg, Throwable e)
{
	logStackTrace(msg, e, DEBUG);
}

public
void logStackTrace(Throwable e, int logLevel)
{
	logStackTrace(null, e, logLevel);
}

public
void logStackTrace(String msg, Throwable e, int logLevel)
{
	if (logLevel <= verboseLevel) {
		StringWriter sw = new StringWriter();
		PrintWriter writer = new PrintWriter(sw);
		if (msg != null) {
			writer.println(msg);
		}
		e.printStackTrace(writer);
		writer.flush();
	}
}

public
void debug(Throwable e)
{
	debug(null, e);
}


public
static String getKindDescr(int kind)
{
	if ((kind >= 0) && (kind < kinds.length)) {
		return kinds[kind];
	} else {
		return null;
	}
}

public
void setVerboseLevel(int verboseLevelIn)
{
	verboseLevel = verboseLevelIn;
}

public
int getVerboseLevel()
{
	return verboseLevel;
}

public final
boolean isLogLevelEnabled(int level)
{
	return level <= verboseLevel;
}

//access package to be used from ILogFactory
void setAddListenersEnabled(boolean enabled) {
	addListenersEnabled = enabled;
}


//- Private members
// is a variable used to filter the msg
private int verboseLevel = PHASE;

private boolean addListenersEnabled = false;


public static I18N getI18N() {
	return i18n;
}

protected static I18N i18n = new I18N("ideasoft.util.log.msg.log");

protected static String[] kinds = new String[8];

static {
	String panic = new String(i18n.getString("Log.panic"));
	kinds[0] = panic;
	String error = i18n.getString("Log.error");
	kinds[1] = error;
	String warning = i18n.getString("Log.warning");
	kinds[2] = warning;
	String notice = i18n.getString("Log.notice");
	kinds[3] = notice;
	String phase = i18n.getString("Log.phase");
	kinds[4] = phase;
	String trace = i18n.getString("Log.trace");
	kinds[5] = trace;
	String debug = i18n.getString("Log.debug");
	kinds[6] = debug;
}

}
