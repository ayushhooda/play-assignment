package controllers

import javax.inject.Inject

import model.{UserSignInForm, UserSignUp, UserSignUpForm}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}


class SignUpController @Inject()(cc: ControllerComponents, form: UserSignUpForm, signInForm: UserSignInForm) extends AbstractController(cc) with I18nSupport {

  val message = cc.messagesApi

  def signUp = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.signup(form.userInfoForm))
  }

  def signIn = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.signin(signInForm.userForm))
  }

  def profile = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.profile())
  }

  def userPost = Action { implicit request: Request[AnyContent] =>
    form.userInfoForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.signup(formWithErrors))
      },
      userData => {
        /* binding success, you get the actual value. */

        val newUser = UserSignUp(
          userData.fname,
          userData.mname,
          userData.lname,
          userData.email,
          userData.password,
          userData.confirmPwd,
          userData.mobile,
          userData.gender,
          userData.age,
          userData.hobbies
        )
        Redirect(routes.SignUpController.profile()).withSession("key" -> userData.email)
//        Ok(views.html.profile())
      }

    )
  }



  //  def signUpInDb = Action { implicit request =>
  ////    userForm.userInfoForm.bindFromRequest().fold(
  ////      formWithError => {
  ////        Future.successful(BadRequest(views.html.index(formWithError)))
  ////      },
  ////      data => {
  ////        userRepo.store(User(0, data.fname, data.lname, data.email)).map {
  ////          case true => Ok("stored")
  ////          case false => Ok("not stored")
  ////        }
  ////      })
  //  }

}
