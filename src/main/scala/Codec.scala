//import argonaut._, Argonaut._
//
//object Codec {
//
//  def p (obj: Any) = println(obj)
//
//  case class Person(name: String, age: Int)
//
//  val person: Person = Person("SC", 2)
//
////  implicit def PersonEncodejson: EncodeJson[Person] =
////  EncodeJson((p: Person) =>
////    ("name" := p.name) ->: ("age" :=p.age ) ->: jEmptyObject
////  )
//
//  implicit def PersonEncodejson: EncodeJson[Person] =
//    jencode2L((p: Person) => (p.name, p.age))("name", "age")
//  //val jsonObjectAlternative: Json =
//  //jObjectAssocList(List("key1" -> jsonNumber, "key2" -> jsonString))
//
//  // model to argonaut.JObject
//  p(person.asJson)
//
//
//  implicit def PersonDecodeJson: DecodeJson[Person] =
//  DecodeJson(c => for {
//    name <- (c --\ "name").as[String]
//    age <-  c.get[Int]("name")
//  } yield Person(name,age))
//
////  implicit def PersonDecodeJson: DecodeJson[Person] =
////    jdecode2L(Person.apply)("name", "age")
//
//  val decodePerson: Option[Person] = "{\"age\":2,\"name\":\"SC\"}".decodeOption[Person]
//  p(decodePerson)
//
//  val temp = Parse.parseOption("{\"age\":2,\"name\":\"SC\"}").getOrElse()
//  p(temp.getClass)
//
//
//
//  val l = for {
//    i <- 1
//  } yield i + 1
//}
