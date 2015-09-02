import argonaut.Argonaut._
import argonaut._
import ModelUtils._
import org.specs2.mutable.Specification

class ModelUtilsSpec extends Specification{

  "fromObjectToJson" should {
    "encode model to jsonobject" in {
       val person = Person("sc", 2)
      ModelUtils.fromObjectToJson(person).nospaces must beEqualTo("{\"name\":\"sc\",\"age\":2}")
    }
  }


  "fromStringToObject" should {
    "decode jsonString to model" in {
      val person = Person("sc", 2)
      val str = "{\"name\":\"sc\",\"age\":2}"
      ModelUtils.fromStringToObject(str) must beEqualTo(Some(person))
    }
  }



}