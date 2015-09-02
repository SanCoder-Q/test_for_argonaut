import argonaut._, Argonaut._
import org.specs2.mutable.Specification

class BasicUtilsSpec extends Specification{
  "defineString" should {
    "return Json represent the input string" in {
      jStringPL.get(BasicUtils.defineString("sunchen")) must beEqualTo(Some("sunchen"))
    }
  }

  "accessJsonObject" should {
    "return value in leve2 of json object" in {
      BasicUtils.accessJsonObject must beEqualTo(Some("innervalue2"))
    }
  }

  "accessJsonObjectUsingCursor" should {
    "return value in leve2 of json object" in {
      BasicUtils.accessJsonObjectUsingCursor must beEqualTo(Some("innervalue2"))
    }
  }

  "fromStringToJson" should {
    "return JsonObject based on the input string" in {
      val str= "{\"id\":\"001\",\"info\":{\"name\":\"sc\"}}"

        BasicUtils.fromStringToJson(str).nospaces must beEqualTo(str)
    }
  }

  "fromJsonToScalaType" should {
    "return List based on the input jsonObject" in {
      val jsonObject = Json("key" -> jString("val"))

     BasicUtils.fromJsonToScalaType(jsonObject) must beEqualTo(Some(List(("key",jString("val")))))
    }
  }


}