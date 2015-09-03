import argonaut._, Argonaut._

import scalaz._, Scalaz._

object BasicUtils {

  def defineObject : Json =
    Json.obj("key1" -> jString("String"), "key2" -> jString("String2"))

  def defineString(str: String): Json = {
      jString(str)
  }

  def accessJsonObject: Option[String] = {
    val innerObject: Json = ("innerkey1", jString("innervalue1")) ->:
      ("innerkey2", jString("innervalue2")) ->:
      jEmptyObject
    val complexObject: Json = ("outerkey1", innerObject) ->:
      ("outerkey2", jFalse) ->:
      jEmptyObject

    var complexObjectPL = jObjectPL >=>
    jsonObjectPL("outerkey1") >=>
    jObjectPL >=>
    jsonObjectPL("innerkey2") >=>
    jStringPL

    complexObjectPL.get(complexObject)
  }


  def accessJsonObjectUsingCursor: Option[String] = {
    val innerObject: Json = ("innerkey1", jString("innervalue1")) ->:
      ("innerkey2", jString("innervalue2")) ->:
      jEmptyObject
    val complexObject: Json = ("outerkey1", innerObject) ->:
      ("outerkey2", jFalse) ->:
      jEmptyObject

    val aCursor = (complexObject.hcursor --\ "outerkey1" --\ "innerkey2")

    aCursor.as[String].toOption
  }

  def fromStringToJson(str: String): Json = {
     Parse.parse(str) match {
       case \/-(a) => a
     }
  }

  def fromJsonToScalaType(json: Json) = {
    json.assoc
  }

}
