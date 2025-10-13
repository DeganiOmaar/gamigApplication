package com.example.game.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.game.ui.theme.GameTheme

@Composable
fun ForgetPasswordScreen(navController: NavController) {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Forget Password Screen",
            color = colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary
            )
        ) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgetPasswordLightPreview() {
    GameTheme(darkTheme = false) {
        val navController = rememberNavController()
        ForgetPasswordScreen(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun ForgetPasswordDarkPreview() {
    GameTheme(darkTheme = true) {
        val navController = rememberNavController()
        ForgetPasswordScreen(navController)
    }
}