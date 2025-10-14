package com.example.game.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.game.R
import com.example.game.ui.theme.GameTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController) {
    // User Input
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Validation
    var fullNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    // SnackBar
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        containerColor = colorScheme.background
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(20.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Back button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowCircleLeft,
                    contentDescription = "Back",
                    tint = colorScheme.primary,
                    modifier = Modifier
                        .size(36.dp)
                        .clickable { navController.popBackStack() }
                )
            }

            Spacer(Modifier.height(22.dp))

            // Logo
            Image(
                painter = painterResource(R.drawable.logo_gamer),
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp)
            )

            Spacer(Modifier.height(22.dp))

            // Full Name
            OutlinedTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    fullNameError = false
                },
                label = { Text("Full Name") },
                leadingIcon = {
                    Icon(Icons.Filled.Person, contentDescription = null, tint = colorScheme.primary)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                isError = fullNameError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    unfocusedBorderColor = colorScheme.onSurface.copy(alpha = 0.4f),
                    cursorColor = colorScheme.primary
                )
            )

            if (fullNameError) {
                val message = when {
                    fullName.isEmpty() -> "Full name must be not empty"
                    fullName.length < 6 -> "Full name must be at least 6 characters"
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

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = false
                },
                label = { Text("Email") },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null, tint = colorScheme.primary)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                isError = emailError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    unfocusedBorderColor = colorScheme.onSurface.copy(alpha = 0.4f),
                    cursorColor = colorScheme.primary
                )
            )

            if (emailError) {
                val message = when {
                    email.isEmpty() -> "Must not be empty!"
                    !email.lowercase().endsWith("@esprit.tn") -> "Email must end with @esprit.tn"
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

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = false
                },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = colorScheme.primary)
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
                    Icon(Icons.Default.Lock, contentDescription = null, tint = colorScheme.primary)
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

            // Submit button
            Button(
                onClick = {
                    // Reset all errors first
                    fullNameError = false
                    emailError = false
                    passwordError = false
                    confirmPasswordError = false

                    // Validate all fields
                    var hasError = false

                    if (fullName.isEmpty() || fullName.length < 6) {
                        fullNameError = true
                        hasError = true
                    }
                    if (email.isEmpty() || !email.endsWith("@esprit.tn")) {
                        emailError = true
                        hasError = true
                    }
                    if (password.isEmpty()) {
                        passwordError = true
                        hasError = true
                    }
                    if (confirmPassword.isEmpty() || confirmPassword != password) {
                        confirmPasswordError = true
                        hasError = true
                    }

                    // If no error, show success message
                    if (!hasError) {
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = "Registered Successfully",
                                withDismissAction = true
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.primary,
                    contentColor = colorScheme.onPrimary
                )
            ) {
                Text("Submit", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "By registering, you agree to our Terms & Conditions and Privacy Policy.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = colorScheme.onBackground.copy(alpha = 0.8f),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpLightPreview() {
    GameTheme(darkTheme = false) {
        val navController = rememberNavController()
        SignUpScreen(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpDarkPreview() {
    GameTheme(darkTheme = true) {
        val navController = rememberNavController()
        SignUpScreen(navController)
    }
}