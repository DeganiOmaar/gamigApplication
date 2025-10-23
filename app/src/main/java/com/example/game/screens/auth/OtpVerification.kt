package com.example.game.screens.auth


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.game.navigation.Routes
import com.example.game.screens.components.OtpInput
import com.example.game.ui.theme.GameTheme
import kotlinx.coroutines.launch

@Composable
fun OtpVerification(navController: NavController,expectedCode: String) {
    val colorScheme = MaterialTheme.colorScheme

    var otp by remember { mutableStateOf("") }

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold (snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        containerColor = colorScheme.background
    ){ innerPadding ->
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
                .padding(16.dp, 12.dp)
                .padding(innerPadding)

        ){
            Icon(
                imageVector = Icons.Filled.ArrowCircleLeft,
                contentDescription = "Back",
                tint = colorScheme.primary,

                modifier = Modifier.clickable{
                    navController.popBackStack()
                } .size(36.dp)
            )


            Spacer(Modifier.height(20.dp))

            Text("Enter The code sent to you by Email or Mobile number",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = colorScheme.onPrimary
                )

            Spacer(Modifier.height(20.dp))

            OtpInput(
                otp = otp,
                onOtpChange = { otp = it },
                length = 4,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(28.dp))

            Button(
                onClick = {
                    if (otp == expectedCode) {
                        // Success
                        navController.navigate(Routes.ResetPassword.route)
                        // TODO LATER
                    } else {
                        // Error
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = " Wrong code!",
                                withDismissAction = true
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Verify",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                    )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "Didn't receive a verification code ?",
                fontSize = 16.sp,
                color = colorScheme.onPrimary,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,

                modifier = Modifier.fillMaxWidth()
                )

            Spacer(Modifier.height(20.dp))

            Text(
                "Resend Code",
                fontSize = 16.sp,
                color = colorScheme.primary,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.fillMaxWidth()
                    .clickable{
                        // TODO LATER
                    }
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun OtpVerificationLightPreview() {
    GameTheme(darkTheme = false) {
        val navController = rememberNavController()
        OtpVerification(navController,"6789")
    }
}

@Preview(showBackground = true)
@Composable
fun OtpVerificationDarkPreview() {
    GameTheme(darkTheme = true) {
        val navController = rememberNavController()
        OtpVerification(navController,"6789")
    }
}