package uy.ideasoft.o3tng.test

import com.ideasoft.o3.metadata.util.mdl.MDLGenerator
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers._
import com.ideasoft.o3.mdl.MdlGeneratorTypes._
import uy.ideasoft.o3tng.metadata.ctx.ContextSpc

class O3ModelGeneratorTest extends AnyFreeSpec with should.Matchers {

  "O3 Model Generator" - {
    "should initialize" - {
      "and do nothing" in {
        val star = TestStarSpc.preciosStar
        val mdlGenerator = new MDLGenerator()
        val ctx = ContextSpc(TestDatasetSpc.datasets, TestConnectionSpc.connectionSpc)
        val mdl = mdlGenerator.getO3Model(ctx, star)
        val a = mdl
      }
    }
  }

}