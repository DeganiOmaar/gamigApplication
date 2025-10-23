package com.example.game.screens.myScreens.news

import androidx.annotation.DrawableRes

data class NewsItem(
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String
)