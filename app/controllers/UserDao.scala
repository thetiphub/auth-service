package controllers

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.Imports.ObjectId

case class User(email: String, username: String, password: String)

class UserDAO {
  def collection = {
    val client = MongoClient("localhost", 27017)
    val db = client("auth-service")
     
    db("users")
  }

  def insert(user: User) = {
    val dbObject = convertToDbObject(user)
    collection.insert(dbObject)
  }

  def delete(user: User) = {
    val dbObject = convertToDbObject(user)
    collection.remove(dbObject)
  }

  private def convertToDbObject(user: User) = {
    MongoDBObject(
      "email" -> user.email,
      "username" -> user.username,
      "password" -> user.password
    )
  }
}