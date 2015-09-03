import argonaut.EncodeJson
import argonaut._, Argonaut._

object ModelUtils {


  case class Age(cnt: Int, unit: String)

  case class Person2(name: String, age : Age)
  case class Person(name: String, age: Int)

  implicit def personEncode : CodecJson[Person] =
    casecodec2(Person.apply, Person.unapply)("name", "age")

  def fromObjectToJson(p: Person) : Json = {
    return p.jencode
  }

  def fromStringToObject(str:String): Option[Person] = {
    str.decodeOption[Person]
  }

  def main(args: Array[String]) = {
    val str = "{\"name\":\"sc\",\"age\":{\"cnt\":3,\"unit\":\"sui\"}}"
//    println(str.decodeEither[Person2])
  }
}
