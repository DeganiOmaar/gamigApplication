package com.example.game.screens.myScreens.store

import androidx.annotation.DrawableRes

data class StoreModel(
    @DrawableRes val imageStoreRes : Int,
    val titleStoreMode : String,
    val consoleType : String,
    val price: Double
)