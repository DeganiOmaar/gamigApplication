package com.example.game.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.game.R
import com.example.game.ui.theme.GameTheme

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_gamer),
            contentDescription = "App Logo",
            modifier = Modifier.size(150.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    GameTheme(darkTheme = false) {
        SplashScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenDarkPreview() {
    GameTheme(darkTheme = true) {
        SplashScreen()
    }
}