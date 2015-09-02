//import argonaut._, Argonaut._
//
//object Main  {
//  def p (ss:Any): Unit ={
//    println(ss)
//  }
//
//  case class Person(name: String, age: Int, things: List[String])
//
//  implicit def PersonCodecJson = casecodec3(Person.apply, Person.unapply)("name", "age", "things")
//
//  val person = Person("Bam Bam", 2, List("club"))
//
//  val json: Json = person.asJson
//  //{"name":"Bam Bam","age":2,"things":["club"]}
//  //println(json)
//
//  val prettyprinted: String = json.spaces2
//  //println(prettyprinted)
////  {
////    "name" : "Bam Bam",
////    "age" : 2,
////    "things" : [
////    "club"
////    ]
////  }
//
//  val parsed: Option[Person] = prettyprinted.decodeOption[Person]
//  //Some(Person(Bam Bam,2,List(club)))
//
//  // Strings are straight forward, but include a few conveniences
//  val jsonString: Json       = jString("JSON!")
//
//
//  val jsonEmptyString: Json  = jEmptyString
//
//  // Numbers again are straight forward
//  val jsonNumber: Option[Json]       = jNumber(20)
//  val jsonZero: Json         = jZero
//
////  // Null
////  val jsonNull: Json         = jNull
////
////  // Booleans
//  val jsonBoolean: Json      = jBool(true)
//  val jsonTrue: Json         = jTrue
//  val jsonFalse: Json        = jFalse
////
////  // Arrays
//  val jsonArray: Json = Json.array(jsonBoolean, jsonString)
//
//  //
//  val jsonArrayAlternative: Json =
//    jArray(List(jsonBoolean, jsonString))
////
////  // Object
//  val jsonObject: Json =
//    Json("key1" -> jsonBoolean, "key2" -> jsonString)
//
//  val jsonObjectExplicit: Json =
//    Json.obj("key1" -> jsonBoolean, "key2" -> jsonString)
//
//  val jsonObjectAlternative: Json =
//    jObjectAssocList(List("key1" -> jsonBoolean, "key2" -> jsonString))
////  // Arrays and object creation can be cleaned up with a cons style
////  // syntax for clarity and flexibility
////
//  val jsonArrayBuilder: Json =
//    jsonString -->>: jsonBoolean -->>: jEmptyArray
//
//  val jsonObjectBuilder: Json =
//    ("key1", jsonBoolean) ->: ("key2", jsonString) ->: jEmptyObject
////
////
////  // Taking this a step further, we can use codecs to help define objects
////  // Note the `:=` operator which takes the value on the right and uses
////  // the its corresponding type class to convert it to json.
////
//  val jsonObjectWithCodec: Json =
//    Json("key1" := 3, "key2" := "hello")
//
//  val jsonObjectExplicitWithCodec: Json =
//    Json.obj("key1" := 3, "key2" := "hello")
//
//  val jsonObjectBuilderWithCodec: Json =
//    ("key1" := 3) ->: ("key2" := "hello") ->: jEmptyObject
////
////
////  // Often you want to conditionally add a field, in this case
////  // the field `option` is only added to the object if the value
////  // is set. Note the `:=?` operator which constructs an (String, Option[Json])
////  // pair, and the '->?:' operator which will conditionally prepend
////  // the field to the object.
////
////  val jsonObjectWithCodecAndOptionals: Json =
////    ("key1" := 3) ->: ("option" :=? none[String]) ->?: jEmptyObject
//
////
////  // The alternative to this, would include the field in the object
////  // but set its value to null in the None case.
////
////  val jsonObjectWithCodecAndNullFields: Json =
////    ("key1" := 3) ->: ("option" := none[String]) ->: jEmptyObject
////}
//val modifiedObject: Json =
//  jSingleObject("field", jTrue).withObject(_ - "field")
//
//  val objectAccess: Option[JsonObject] =
//    jSingleObject("field", jTrue).obj
//
//  val nestedObjectAccess: Option[Json] =
//    jSingleObject("field", jSingleObject("nested", jTrue)) -|| List("field", "nested")
//  val nested = jSingleObject("field", jSingleObject("nested", jTrue))
//
//
//  val jsonString2: Json = jString("JSON!")
//  // Get the possible value from the lens, this would return None if the instance wasn't a string.
//  //println(jStringPL.get(jsonString2))
//
//  // Create a two level nested object.
//  val innerObject: Json = ("innerkey1", jString("innervalue1")) ->:
//    ("innerkey2", jString("innervalue2")) ->:
//    jEmptyObject
//  val complexObject: Json = ("outerkey1", innerObject) ->:
//    ("outerkey2", jFalse) ->:
//    jEmptyObject
//
//  val innerKey2StringLens = jObjectPL >=>   // Lens composition starts with converting to object...
//    jsonObjectPL("outerkey1") >=>           // ...Looking up the "outerkey1" field...
//    jObjectPL >=>                           // ...Converting to an object...
//    jsonObjectPL("innerkey2") >=>           // ...Looking up the "innerkey2" field...
//    jStringPL                               // ...Converting to a string.
//
//  // Gets the value from deep within the structure.
//  //println(innerKey2StringLens.get(complexObject))
//
//  // Modifies the inner most value returning entirely new Json instance.
////  println(innerKey2StringLens.mod(_ + " is innervalue2", complexObject))
//
//  val cursor = complexObject.cursor
//  val updatedJson: Option[Json] = for {
//  // Drill down into the outerkey1 field.
//    outerkey1Field <- cursor.downField("outerkey1")
//
//    // Drill down into the innerkey2 field.
//    innerkey2Field <- outerkey1Field.downField("innerkey2")
//
//    // Update the Json element we're focused on.
//    updatedInnerkey2Field = innerkey2Field.withFocus(
//      _.withString(_ + " is innervalue2")
//    )
//
//  // Unwinds to the top and returns Json.
//  } yield updatedInnerkey2Field.undo
//
//  //println(updatedJson)
//
//  val hcursor = complexObject.hcursor
//
//  val updated = (hcursor --\ "outerKey" --\ "innerkey2").withFocus(
//    _.withString(_ + " is innervalue2")
//  )
//
////  println("we traversed: " + updated.history.toString())
////
// // println("we got: " + updated.undo)
////
////  // Handle failure cases
////
////  val lastSuccess =
////    (cursor --\ "outerKey" --\ "innerkey2" --\ "oops" --\ "off the rails").reattempt.focus
////
////  val firstSuccess =
////    (cursor --\ "outerKey" --\ "oldkey2") ||| (cursor --\ "outerKey" --\ "newkey2")
//
//
//    case class Person2(name: String, age: Int)
//
//    // Explicit DecodeJson instance using hcursor
//    //   (See http://argonaut.io/doc/zipper for more detail).
//
//    // In this example `--\` is just a combinator on HCursor,
//    // you can and should utilize the full range of operations.
//
//    implicit def PersonDecodeJson: DecodeJson[Person2] =
//      DecodeJson(c => for {
//        name <- (c --\ "name").as[String]
//        age <- (c --\ "age").as[Int]
//      } yield Person2(name, age))
//   //p( "{\"name\":\"haha\",\"age\":12}".decodeEither[Person2])
//
////  import scala.concurrent._
////  import ExecutionContext.Implicits.global
////  val f = Future {
////    2 / 0
////  }
////  f onFailure {
////    case npe: NullPointerException =>
////      println("I'd be amazed if this printed out.")
////  }
//
//  val requestJson =
//    """
//      |{
//      |   "userid": "1"
//      |}
//      |""".stripMargin
//  val parsedTemp = requestJson.parseOption
//
//  // parse the json and prepend a name field
//  val updatedJson2: Option[Json] = for {
//    parsed <- requestJson.parseOption
//  } yield ("name", jString("testuser")) ->: parsed
// // println(updatedJson2.getOrElse().getClass)
////
//  // If there was a failure at any point, provide a default.
//  val responseJson: Json = updatedJson2.getOrElse{
//    jSingleObject("error", jString("Something went wrong."))
//  }
////  println(responseJson.nospaces)
//
//  ///////////////////////////codec//////////////////////////
//  val jsonObject2: Json = Json("a" -> jString("JSON!") ,"b" -> jString("JSO2N!"))
////  println(jsonObject2.nospaces)
//  val jsonCodec: Json = Json("a" := 3, "b" := "hello")
////  println(jsonCodec.nospaces)
//val jsonObjectBuilderWithCodec2: Json =
//  ("key1" := 3) ->: ("key2" := "hello") ->: jEmptyObject
//  println(jsonObjectBuilderWithCodec2.nospaces)
//
//
//
//
//
//
//  val innerObject2: Json =
//    ("innerkey1", jString("innervalue1")) ->:
//      ("innerkey2", jString("innervalue2")) ->:
//      jEmptyObject
//
//  val complexObject2: Json =
//    ("outerkey1", innerObject2) ->:
//      ("outerkey2", jFalse) ->:
//      jEmptyObject
//
//  // The history basic cursor, handle traversal management yourself
//  val hhcursor = complexObject.hcursor
//
//  val hupdated = (hhcursor --\ "outerKey1" --\ "innerkey2").withFocus(
//    _.withString(_ + " is innervalue2")
//  )
//  hupdated.undo
//
//  //println("we traversed: " + updated.history.shows)
//
//  println("we got: " + complexObject)
//
//
//
//  case class Person3(name: String, age: Int)
//
//  // Explicit EncodeJson instance using the object builder syntax
//  //   (See http://argonaut.io/doc/json for more detail).
////
////  implicit def PersonEncodeJson: EncodeJson[Person3] =
////    EncodeJson((p: Person3) =>
////      ("name" := p.name) ->: ("age" := p.age) ->: jEmptyObject)
//
//  implicit  def PercondCodecJson: CodecJson[Person3] =
//    casecodec2(Person3.apply, Person3.unapply)("name","age")
//
//  val person2= Person3("sc",2)
//  println(person2.asJson)
//  val ss="{\"name\":\"sc\",\"age\":2}"
//  println(Parse.decodeOption[Person3](ss))
////  println(ss.decodeEither[Person3])
//}
//
