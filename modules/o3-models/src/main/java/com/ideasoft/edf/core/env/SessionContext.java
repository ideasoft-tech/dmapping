package com.ideasoft.edf.core.env;

import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.ideasoft.edf.data.api.SchemaConnectionDescriptor;
import com.ideasoft.edf.data.spec.Metadata;
import org.w3c.dom.events.EventTarget;

public interface SessionContext extends EventTarget {

  public Metadata getMetadata();

  public SchemaConnectionDescriptor getConnectionDescriptor();

}
