package model

import play.api.data.Form
import play.api.data.Forms.{mapping, text, number, nonEmptyText}

case class UserSignUp(fname: String,mname: String, lname: String, email: String,
                password: String, confirmPwd: String, mobile: String, gender: String,
                age: Int, hobbies: String)

class UserSignUpForm {

  val emailRegex = "(?=[^\\s]+)(?=(\\w+)@([\\w\\.]+))"
  val passwordRegex = "[0-9]"
  val nameRegex = "[a-zA-Z]"

  val userInfoForm = Form(mapping(
    "fname" -> text.verifying("Enter First Name", fname => fname.nonEmpty && fname.matches(nameRegex)),
    "mname" -> text,
    "lname" -> text.verifying("Enter Last Name", lname => lname.nonEmpty && lname.matches(nameRegex)),
    "email" -> text.verifying("Enter valid Email", _.matches(emailRegex)),
    "password" -> text.verifying("Enter valid password", passCode => passCode.length >= 8 && passCode.matches(passwordRegex)),
    "confirmPwd" -> text,
    "mobile" -> text.verifying("Enter valid mobile number", contact => contact.length != 10 && contact.matches(passwordRegex)),
    "gender" -> text,
    "age" -> number(min = 18, max = 75),
    "hobbies" -> text
  )(UserSignUp.apply)(UserSignUp.unapply) verifying("Confirm Password do not match", data => data.password == data.confirmPwd))


}
