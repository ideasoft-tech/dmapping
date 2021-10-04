package uy.ideasoft.o3tng.metadata.ctx

import uy.ideasoft.o3tng.metadata.rdb.RelationalSpc


case class ContextSpc(
  relationalSpc: RelationalSpc,
  schConnection: ConnectionSpc) {
}

case class ConnectionSpc(
  engine: Option[String] = None,
  driver: Option[String] = None,
  url: Option[String] = None,
  user: Option[String] = None,
  password: Option[String] = None,
  dataSource: Option[String] = None,
  parameters: Map[String, String] = Map()
)