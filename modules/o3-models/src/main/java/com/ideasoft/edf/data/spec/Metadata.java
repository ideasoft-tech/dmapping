package com.ideasoft.edf.data.spec;

import com.ideasoft.edf.data.metadata.impl.Table;

public interface Metadata {

  Table getTable(String sch, String name);

  DataField getFieldbyFieldName(String var1);

}
