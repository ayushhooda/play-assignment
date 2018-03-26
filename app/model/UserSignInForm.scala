package model

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, text}

case class UserSignIn(email: String, password: String)

class UserSignInForm {


  val userForm = Form(mapping(
    "email" -> nonEmptyText,
    "password" -> nonEmptyText
  )(UserSignIn.apply)(UserSignIn.unapply))

}
