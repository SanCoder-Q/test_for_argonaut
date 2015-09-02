import argonaut._, Argonaut._

import scalaz._, Scalaz._

object BasicUtils {

  def defineObject : Json =
    Json.obj("key1" -> jString("String"), "key2" -> jString("String2"))

  def defineString(str: String): Json = {
      //TODO: define jsonString
      jString("need to modify")
  }

  def accessJsonObject: Option[String] = {
    val innerObject: Json = ("innerkey1", jString("innervalue1")) ->:
      ("innerkey2", jString("innervalue2")) ->:
      jEmptyObject
    val complexObject: Json = ("outerkey1", innerObject) ->:
      ("outerkey2", jFalse) ->:
      jEmptyObject

    //TODO: return  value of outerkey1->innerkey2
    return Option("")

  }


  def accessJsonObjectUsingCursor: Option[String] = {
    val innerObject: Json = ("innerkey1", jString("innervalue1")) ->:
      ("innerkey2", jString("innervalue2")) ->:
      jEmptyObject
    val complexObject: Json = ("outerkey1", innerObject) ->:
      ("outerkey2", jFalse) ->:
      jEmptyObject

    var cursor = complexObject.hcursor


//TODO: RETURN Value of outerkey1->innerkey2
    return Option("")

  }

  def fromStringToJson(str: String): Json = {
    //TODO: parse json string to JsonObject
     jString(str)
  }

  def fromJsonToScalaType(json: Json) = {
    //TODO: convert Json to scala type
  }

}
