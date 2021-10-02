

val s = "/a/b/c1/d/e1"
val s2 = "/a/b"

val r1 = "/a/b/c1/d/e1".r

r1.matches(s)
r1.matches(s2)

val r2 = "/a/b/c[0-9]+/d/e1".r

r2.matches(s)

val r3 = "/a/b/c[0-9]+/d/e[0-9]+".r

r3.matches(s)





val s3 = "/grid/rows/row/data1/FmtValue"

val r4 = "/grid/rows/row/[a-zA-Z0-9]+/FmtValue".r

r4.matches(s3)



val s5 = "/grid/rows/row/data1/FmtValue"

val r5 = "/grid/rows/row/[a-zA-Z0-9]+$".r

r5.matches(s5)

s5 match {
  case r5 @ x if r5.matches(s5) => println(x)
  case _ => false
}


val r6 = "\\$$".r

val s6 = "$hhhh"

r6.matches(s6)