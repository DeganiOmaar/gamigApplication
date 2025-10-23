package com.example.game.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.game.R
import com.example.game.navigation.Routes
import com.example.game.ui.theme.GameTheme
import kotlinx.coroutines.launch
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun LoginPage(navController: NavController) {
    // User Inputs
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Validation
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    // Password Visibility State
    var isPasswordVisible by remember { mutableStateOf(false) }

    // SnackBar
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Color
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        containerColor = colorScheme.background
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(20.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo_gamer),
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp)
            )

            Spacer(Modifier.height(16.dp))

            // Champ email
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = false
                },
                label = { Text("Email", color = colorScheme.onBackground) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null,
                        tint = colorScheme.secondary)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                isError = emailError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    unfocusedBorderColor = colorScheme.onSurface.copy(alpha = 0.5f),
                    cursorColor = colorScheme.primary
                )
            )

            if (emailError) {
                Text(
                    text = "Email must end with @esprit.tn",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }

            Spacer(Modifier.height(16.dp))

            // Champ mot de passe
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = false
                },
                label = { Text("Password", color = colorScheme.onBackground) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null,
                        tint = colorScheme.secondary)
                },
                trailingIcon = {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null,
                        tint = colorScheme.primary,
                        modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                isError = passwordError,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    unfocusedBorderColor = colorScheme.onSurface.copy(alpha = 0.5f),
                    cursorColor = colorScheme.primary
                )
            )

            if (passwordError) {
                Text(
                    text = "Password must not be empty!",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            // Bouton Login
            Button(
                onClick = {
                    emailError = email.isEmpty() || !email.endsWith("@esprit.tn")
                    passwordError = password.isEmpty()

                    if (!emailError && !passwordError) {
                    //just here for to know snackbar
                       // scope.launch {
                           // snackBarHostState.showSnackbar(
                              //  message = "Login successful (placeholder)",
                               // withDismissAction = true
                            //)
                       // }


                        navController.navigate(Routes.MainTabs.route) {
                            popUpTo(Routes.Login.route) { inclusive = true }
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
                Text("Login", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
            }

            Spacer(Modifier.height(16.dp))

            // Forget Password
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = false, onCheckedChange = { }, colors = CheckboxDefaults.colors(colorScheme.primary))
                    Text(
                        "Remember Me",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.primary
                    )
                }
                Text(
                    text = "Forgot password ?",
                    color = colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.ForgetPassword.route)
                    }
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "OR",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorScheme.primary
            )

            Spacer(Modifier.height(20.dp))

            // Social Media
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Facebook
                Row(
                    Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(24, 119, 242))
                        .clickable {
                            scope.launch {
                                snackBarHostState.showSnackbar("Facebook login coming soon")
                            }
                        }
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Facebook,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text("Facebook", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                // Google
                Row(
                    Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorScheme.surfaceVariant.copy(alpha = 0.2f))
                        .clickable {
                            scope.launch {
                                snackBarHostState.showSnackbar("Google login coming soon")
                            }
                        }
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = R.drawable.google),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text("Google", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = colorScheme.onBackground)
                }
            }

            Spacer(Modifier.height(64.dp))

            // Section Register
            Row {
                Text(
                    "Don't have an account ?",
                    color = colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Register Now",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    color = colorScheme.primary,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.SignUp.route)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPageLightPreview() {
    GameTheme(darkTheme = false) {
        val navController = rememberNavController()
        LoginPage(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPageDarkPreview() {
    GameTheme(darkTheme = true) {
        val navController = rememberNavController()
        LoginPage(navController)
    }
}