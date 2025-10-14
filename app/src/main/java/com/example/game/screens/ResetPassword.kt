package com.example.game.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.game.ui.theme.GameTheme

@Composable
fun ResetPassword(){
    val colorScheme = MaterialTheme.colorScheme

    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        containerColor = colorScheme.background
    ) {  innerPadding ->
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
                .padding(16.dp,12.dp)
                .padding(innerPadding)
        ){
            Icon(
                imageVector = Icons.Filled.ArrowCircleLeft,
                contentDescription = "Back Icon",
                tint = colorScheme.onPrimary,
                modifier = Modifier.size(36.dp)
                    .clickable{
                        //TODO LATER
                    }
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "Reset Password",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  colorScheme.onPrimary
                )


            Spacer(Modifier.height(20.dp))

            Text("Please enter your new password and confirm it.",
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = colorScheme.onPrimary
                )

            Spacer(Modifier.height(20.dp))





        }

    }

}


@Preview(showBackground = true)
@Composable
fun ResetPasswordLightPreview() {
    GameTheme(darkTheme = false) {
        val navController = rememberNavController()
        ResetPassword()
    }
}

@Preview(showBackground = true)
@Composable
fun ResetPasswordDarkPreview() {
    GameTheme(darkTheme = true) {
        val navController = rememberNavController()
        ResetPassword()
    }
}