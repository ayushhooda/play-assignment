package model

case class User(id: Int, fname: String,mname: String, lname: String, email: String,
                password: String, confirmPwd: String, mobile: String, gender: Char,
                age: Int, hobbies: List[String])
