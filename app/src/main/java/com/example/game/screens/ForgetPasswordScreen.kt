package com.example.game.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.game.ui.theme.GameTheme
import kotlinx.coroutines.launch

@Composable
fun ForgetPasswordScreen(navController: NavController) {
    val colorScheme = MaterialTheme.colorScheme

    var email by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf<String?>(null) }
    var emailHasFocus by remember { mutableStateOf(false) }

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        containerColor = colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(16.dp,12.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,

        ) {
            Icon(
                imageVector = Icons.Filled.ArrowCircleLeft,
                contentDescription = "Back",
                tint = colorScheme.primary,
                modifier = Modifier
                    .clickable {navController.popBackStack()}
                    .size(36.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text("Forget Password",
                color =  colorScheme.onPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(Modifier.height(12.dp))

            Text("Please enter your registered email to reset your password",
                color = colorScheme.onPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.W400
            )
            Spacer(Modifier.height(20.dp))



            OutlinedTextField(
                shape = RoundedCornerShape(36.dp),
                value = email,
                onValueChange = {
                    email = it
                   if(email.isNotEmpty()){
                       emailError = false
                       emailErrorMessage = null
                   }
                },
                label = {Text("Email / Phone ",
                    color = colorScheme.onPrimary,
                    fontSize = 14.sp
                    )},
                leadingIcon = {Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "TextField Icon",
                    tint = colorScheme.secondary
                )},
                isError = emailError,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        emailHasFocus = focusState.isFocused
                        if (focusState.isFocused && email.isEmpty()) {
                            emailError = true
                            emailErrorMessage = "Email must not be empty"
                        }},
                supportingText = {
                    emailErrorMessage?.let {
                        Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                    }
                },
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    emailError = email.isEmpty()
                    emailErrorMessage = when{
                        email.isEmpty() -> "Email must be not empty"
                        else -> null
                    }
                    if (emailError){
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                "You have some error in your inputs",
                                withDismissAction = true
                            )
                        }
                    }

                    if (!emailError){
                        //TODO LATER
                    }
                },
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text(
                    "Submit",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(Modifier.height(20.dp))

            Text(
                "OR",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    val otpToSend = "6789"
                    navController.navigate(
                        com.example.game.navigation.Routes.OtpVerification.withCode(otpToSend)
                    )
                },
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text(
                    "Send SMS",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }





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