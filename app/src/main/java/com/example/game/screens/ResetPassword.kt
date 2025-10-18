package com.example.game.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.game.ui.theme.GameTheme
import kotlinx.coroutines.launch

@Composable
fun ResetPassword(navController: NavController){
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
                tint = colorScheme.primary,
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

            Spacer(Modifier.height(22.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = false
                },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null,
                        tint = colorScheme.secondary)
                },
                trailingIcon = {
                    Icon(Icons.Filled.Visibility, contentDescription = null, tint = colorScheme.primary)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                isError = passwordError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    unfocusedBorderColor = colorScheme.onSurface.copy(alpha = 0.4f),
                    cursorColor = colorScheme.primary
                )
            )

            if (passwordError) {
                Text(
                    text = "Password must not be empty!",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(Modifier.height(22.dp))

            // Confirm Password
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = false
                },
                label = { Text("Confirm Password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock,
                        contentDescription = null, tint = colorScheme.secondary)
                },
                trailingIcon = {
                    Icon(Icons.Filled.Visibility,
                        contentDescription = null, tint = colorScheme.primary)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                isError = confirmPasswordError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    unfocusedBorderColor = colorScheme.onSurface.copy(alpha = 0.4f),
                    cursorColor = colorScheme.primary
                )
            )

            if (confirmPasswordError) {
                val message = when {
                    confirmPassword.isEmpty() -> "Must not be empty!"
                    confirmPassword != password -> "Passwords do not match!"
                    else -> ""
                }
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(Modifier.height(22.dp))

            // Reset button
            Button(
                onClick = {

                    passwordError = false
                    confirmPasswordError = false

                    // Validate all fields
                    var hasError = false


                    if (password.isEmpty()) {
                        passwordError = true
                        hasError = true
                    }
                    if (confirmPassword.isEmpty() || confirmPassword != password) {
                        confirmPasswordError = true
                        hasError = true
                    }

                    if (hasError) {
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = "You Have Some Errors in your inputs",

                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.primary,
                    contentColor = colorScheme.onPrimary
                )
            ) {
                Text("Submit", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
            }





        }

    }

}


@Preview(showBackground = true)
@Composable
fun ResetPasswordLightPreview() {
    GameTheme(darkTheme = false) {
        val navController = rememberNavController()
        ResetPassword(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun ResetPasswordDarkPreview() {
    GameTheme(darkTheme = true) {
        val navController = rememberNavController()
        ResetPassword(navController)
    }
}