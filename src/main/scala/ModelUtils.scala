import argonaut.EncodeJson
import argonaut._, Argonaut._

object ModelUtils {


  case class Age(cnt: Int, unit: String)

  case class Person2(name: String, age : Age)
  case class Person(name: String, age: Int)

  implicit def PersonEncode: EncodeJson[Person] = {
    jencode2L((p : Person) => (p.name, p.age))("name","age")
    //  //val jsonObjectAlternative: Json =
    //  //jObjectAssocList(List("key1" -> jsonNumber, "key2" -> jsonString))

  }

  implicit def PersonDecode: DecodeJson[Person] = {
    jdecode2L(Person.apply)("name","age")
  }

//  implicit def AgeDecode: DecodeJson[Age] = {
//    DecodeJson(c => for{
//      cnt <- (c --\ "cnt").as[Int]
//      unit <- (c --\ "unit").as[String]
//    } yield Age(cnt, unit))
//  }
//
//  implicit def Person2Decode: DecodeJson[Person2] = {
//    DecodeJson(c => for{
//      name <- (c --\ "name").as[String]
//      age <- (c --\ "age").as[Age]
//  } yield Person2(name, age))
//  }

  implicit def AgeDecode: DecodeJson[Age] = {
    jdecode2L(Age.apply)("cnt","unit")
  }

  implicit def Person2Decode: DecodeJson[Person2] = {
    jdecode2L(Person2.apply)("name","age")
  }

  def fromObjectToJson(p: Person) : Json = {
    return p.jencode
  }

  def fromStringToObject(str:String): Option[Person] = {

    str.decodeOption[Person]

  }

  def main(args: Array[String]) = {
    val str = "{\"name\":\"sc\",\"age\":{\"cnt\":3,\"unit\":\"sui\"}}"
    println(str.decodeEither[Person2])

  }
}
