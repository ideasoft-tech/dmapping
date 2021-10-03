//- Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright  1997 IdeaSoft Uruguay S.R.L.  All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: PropertyResourceBundle.java,v 1.2 1999/12/03 22:18:56 martin Exp $
// -----------------------------------------------------------------------
package ideasoft.util.bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;


public
class PropertyResourceBundle
	extends ResourceBundle
{
	/**
	 * Creates a property resource
	 * @param file property file to read from.
	 */
    public PropertyResourceBundle(InputStream stream) throws IOException {
        lookup.load(stream);
    }

    /**
     * Override of ResourceBundle, same semantics
     */
    public Object handleGetObject(String key) {
        Object obj = lookup.get(key);
        return obj; // once serialization is in place, you can do non-strings
    }

    /**
     * Implementation of ResourceBundle.getKeys.
     */
    public Enumeration getKeys() {
	    Enumeration result = null;
	    if (parent != null) {
            final Enumeration myKeys = lookup.keys();
            final Enumeration parentKeys = parent.getKeys();

            result = new Enumeration() {
                public boolean hasMoreElements() {
                    if (temp == null)
                        nextElement();
                    return temp != null;
                }

                public Object nextElement() {
                    Object returnVal = temp;
                    if (myKeys.hasMoreElements())
                        temp = myKeys.nextElement();
                    else {
                        temp = null;
                        while (temp == null && parentKeys.hasMoreElements()) {
                            temp = parentKeys.nextElement();
                            if (lookup.containsKey(temp))
                                temp = null;
                        }
                    }
                    return returnVal;
                }

                Object temp = null;
            };
	    } else {
	        result = lookup.keys();
	    }
        return result;
    }

    // ==================privates====================

    private Properties lookup = new Properties();
}
