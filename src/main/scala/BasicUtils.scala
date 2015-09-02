import argonaut._, Argonaut._

import scalaz._, Scalaz._

object BasicUtils {

  def defineObject : Json =
    Json.obj("key1" -> jString("String"), "key2" -> jString("String2"))

  def defineString(str: String): Json = jString(str)

  def accessJsonObject: Option[String] = {
    val innerObject: Json = ("innerkey1", jString("innervalue1")) ->:
      ("innerkey2", jString("innervalue2")) ->:
      jEmptyObject
    val complexObject: Json = ("outerkey1", innerObject) ->:
      ("outerkey2", jFalse) ->:
      jEmptyObject

    var valInLevel2Lens = jObjectPL >=>
    jsonObjectPL("outerkey1") >=>
    jObjectPL >=>
    jsonObjectPL("innerkey2") >=>
    jStringPL


    return valInLevel2Lens.get(complexObject)

  }


  def accessJsonObjectUsingCursor: Option[String] = {
    val innerObject: Json = ("innerkey1", jString("innervalue1")) ->:
      ("innerkey2", jString("innervalue2")) ->:
      jEmptyObject
    val complexObject: Json = ("outerkey1", innerObject) ->:
      ("outerkey2", jFalse) ->:
      jEmptyObject

    var cursor = complexObject.hcursor



    return (cursor --\ "outerkey1" --\ "innerkey2").as[String].toOption

  }

  def fromStringToJson(str: String): Json = {
    val str:String = "{\"id\":\"001\", \"info\":{\"name\":\"sc\"}}"
    Parse.parse(str) match {
      case \/-(a) => a
    }
  }

  def fromJsonToScalaType(json: Json) = {
    json.assoc
  }


}
