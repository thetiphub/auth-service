package controllers

import javax.inject._
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

@Singleton
class TokenController @Inject() extends Controller {
    case class LoginData(loginName: String, password: String)

    def loginForm = Form(
        mapping(
            "loginName" -> text,
            "password" -> text
        )(LoginData.apply)(LoginData.unapply)
    )

    case class BearerTokenData(token: String)

    def bearerTokenForm = Form(
        mapping(
            "token" -> text
        )(BearerTokenData.apply)(BearerTokenData.unapply)
    )

    def login = Action { implicit request =>
        loginForm.bindFromRequest.fold(
            formWithErrors => BadRequest("Unable to login"),
            login => {
                println(s"\n\n\n\n${login.loginName}\n${login.password}\n\n\n\n\n")
                Ok("User successfully logged in")
            }
        )
    }

    def logout(token: String) = Action { implicit request =>
        println(s"\n\n\n\n${token}\n\n\n\n")
        Ok("Logged out successfully")
    }

    def verify(token: String) = Action { implicit request =>
        
        println(s"\n\n\n\n${token}\n\n\n\n")

        if(token == "validtoken") {
            Ok("User verified")
        }
        else {
            NotFound("User failed verification")
        }
    }
}