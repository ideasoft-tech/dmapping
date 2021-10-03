//- Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright  1997 IdeaSoft Uruguay S.R.L.  All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ILogFactory.java,v 1.4 2005/01/04 18:18:39 martin Exp $
// -----------------------------------------------------------------------

package ideasoft.util.log;

import com.ideasoft.app.ContextUtils;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;

/**
 * @author martin
 */
public class ILogFactory {

  static ILog iLog;

  public static synchronized ILog getILog(Class callerClass) {
    return getILog(callerClass.getName());
  }

  public static synchronized ILog getILog(String name) {

    if (iLog == null) {
      iLog = new ILog();
    } return iLog;

  }
}
