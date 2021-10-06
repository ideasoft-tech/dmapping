package uy.ideasoft.o3tng.test

import uy.ideasoft.o3tng.metadata.ctx.ConnectionSpc

object TestConnectionSpc {
  val connectionSpc: ConnectionSpc = ConnectionSpc(
    engine = Some("pg"),
    driver = Some("xx"),
    url = Some("jdbc:aaa:bbb"),

  )
}
