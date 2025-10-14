package com.example.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.game.navigation.Routes
import com.example.game.screens.ForgetPasswordScreen
import com.example.game.screens.LoginPage
import com.example.game.screens.SignUpScreen
import com.example.game.screens.SplashScreen
import com.example.game.ui.theme.GameTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Dark & Light effect
            GameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreenWithDelay(navController)
        }

        composable(Routes.Login.route) {
            LoginPage(navController)
        }

        composable(Routes.ForgetPassword.route) {
            ForgetPasswordScreen(navController)
        }

        composable(Routes.SignUp.route) {
            SignUpScreen(navController)
        }
    }
}

@Composable
fun SplashScreenWithDelay(navController: NavController) {
    SplashScreen()
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Routes.Login.route) {
            popUpTo(Routes.Splash.route) { inclusive = true }
        }
    }
}