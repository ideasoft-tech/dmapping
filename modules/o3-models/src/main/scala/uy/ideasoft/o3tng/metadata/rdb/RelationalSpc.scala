package uy.ideasoft.o3tng.metadata.rdb

case class RelationalSpc(
  tables: Seq[TableSpc],
)

case class TableSpc(
  name: String,
  label: Option[String] = None,
  description: Option[String] = None,
)
