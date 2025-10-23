package com.example.game.screens.myScreens.store

import android.graphics.drawable.Icon
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.game.R

@Composable
fun StoreScreen() {

    // Color
    val colorScheme = MaterialTheme.colorScheme


    val storeList = listOf(
        StoreModel(
            imageStoreRes = R.drawable.store1,
            titleStoreMode = "Awel Titre",
            consoleType = "PS4",
            price = 23.9
        ),
        StoreModel(
            imageStoreRes = R.drawable.store2,
            titleStoreMode = "Theni Titre",
            consoleType = "PC",
            price = 33.5
        ),
        StoreModel(
            imageStoreRes = R.drawable.store3,
            titleStoreMode = "Theleth Titre",
            consoleType = "PS5",
            price = 43.1
        ),
        StoreModel(
            imageStoreRes = R.drawable.store4,
            titleStoreMode = "Rabaa Titre",
            consoleType = "ATARI",
            price = 53.2
        ),
        StoreModel(
            imageStoreRes = R.drawable.store5,
            titleStoreMode = "Khames Titre",
            consoleType = "Phone",
            price = 63.3
        )
    )

    LazyColumn (
        modifier = Modifier.padding(horizontal = 20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items(storeList) { item ->
            StoreCard(item)
        }
    }

}

@Composable
fun StoreCard(item : StoreModel){
    Card (
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.background
        ),
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(id = item.imageStoreRes),
                contentDescription = "",
                modifier = Modifier
                    .height(150.dp)
            )



            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = item.titleStoreMode,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.primary
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = item.consoleType,
                    fontWeight = FontWeight.Normal,
                    color = colorScheme.secondary
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = item.price.toString() + "DT",
                    fontWeight = FontWeight.Normal,
                    color = colorScheme.secondary
                )
            }

            IconButton(
                onClick = { }
            ) {
                Icon(imageVector = Icons.Filled.ShoppingCartCheckout, contentDescription = "Add to Store"
                    , tint = colorScheme.primary
                )
            }
        }
    }
}

