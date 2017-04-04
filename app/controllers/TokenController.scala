package controllers

import com.mongodb.casbah.Imports._
import com.mongodb.DBObject
import org.mindrot.jbcrypt.BCrypt;
import javax.inject._
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

@Singleton
class TokenController @Inject() extends Controller {
    case class LoginData(email: String, password: String)

    def loginForm = Form(
        mapping(
            "email" -> text,
            "password" -> text
        )(LoginData.apply)(LoginData.unapply)
    )

    def login = Action { implicit request =>
        loginForm.bindFromRequest.fold(
            formWithErrors => BadRequest("Unable to login"),
            login => {
                val client = MongoClient("db", 27017)
                val db = client("auth-service")
                val userCollection = db("users")
                val tokenCollection = db("tokens")
                val user = MongoDBObject("email" -> login.email)

                val u = userCollection.findOne(user)

                u match {
                    case Some(_) => {
                        if(BCrypt.checkpw(login.password, u.get.getAs[String]("password").get)) {
                            val token = java.util.UUID.randomUUID.toString
                            val bearerToken = MongoDBObject("bearerToken" -> token)
                            val bt = tokenCollection.insert(bearerToken)

                            Ok(token)
                        } else BadRequest
                    }
                    case None => NotFound
                }
            }
        )
    }

    def logout(token: String) = Action { implicit request =>

        val client = MongoClient("db", 27017)
        val db = client("auth-service")
        val coll = db("tokens")
        val bearerToken = MongoDBObject("bearerToken" -> token)
        val bt = coll.remove(bearerToken)

        Ok("Logged out successfully")
    }

    def verify(token: String) = Action { implicit request =>
        
        val client = MongoClient("db", 27017)
        val db = client("auth-service")
        val coll = db("tokens")

        val bearerToken = MongoDBObject("bearerToken" -> token) 
        
        val bt = coll.findOne(bearerToken)

        bt match {
           case Some(bt) => Ok("User verified")
           case None => NotFound("User failed verification")
        }
    }
}