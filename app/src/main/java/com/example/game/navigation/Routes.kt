package com.example.game.navigation



sealed class Routes(val route: String) {
    object Splash : Routes("splash_screen")
    object Login : Routes("login_screen")
    object ForgetPassword : Routes("Forget_password")
    object SignUp : Routes("Sign_up")
    object OtpVerification : Routes("otp/{code}"){
        fun withCode(code: String) = "otp/$code"
    }
    object ResetPassword : Routes("reset_password")


    sealed class MainRoute(val route: String) {
        object News : MainRoute("news")
        object Store : MainRoute("store")
        object Profile : MainRoute("profile")
    }

    object MainTabs : Routes("main_tabs")

}