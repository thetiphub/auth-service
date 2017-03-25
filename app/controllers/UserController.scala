package controllers

import javax.inject._
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

@Singleton
class UserController @Inject() extends Controller {
    case class UserData(email: String, username: String, password: String)

    val userForm = Form(
        mapping(
            "email" -> text,
            "username" -> text,
            "password" -> text
        )(UserData.apply)(UserData.unapply)
    )

    def create = Action { implicit request =>
        userForm.bindFromRequest.fold(
            formWithErrors => BadRequest("has errors"),
            user => {
                println(s"\n\n\n\n${user.email}\n${user.username}\n${user.password}\n\n\n\n")
                Ok("User created")
            }
        )
    }

    def updatePassword = TODO
}