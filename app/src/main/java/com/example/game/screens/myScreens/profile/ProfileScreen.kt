package com.example.game.screens.myScreens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game.R
import com.example.game.navigation.Routes
import com.example.game.screens.myScreens.news.NewsCard
import com.example.game.screens.myScreens.news.NewsItem
import com.example.game.screens.myScreens.store.StoreModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ProfileScreen() {
    val colorScheme = MaterialTheme.colorScheme

    val profileList = listOf(
        StoreModel(R.drawable.store1, "Red Dead Redemption 2", "PS4 Games", 70.0),
        StoreModel(R.drawable.store2, "Grand Theft Auto 5", "PC Games", 55.0),
        StoreModel(R.drawable.store3, "The Legend of Zelda", "PS5 Games", 60.0),
        StoreModel(R.drawable.store4, "God of War Ragnarok", "ATARI", 53.2)
    )

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        containerColor = colorScheme.background,
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                // Avatar
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "Profile avatar",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(top = 16.dp)
                )

                Spacer(Modifier.height(12.dp))

                // Name
                Text(
                    text = "Full Name",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(Modifier.height(4.dp))

                // Email
                Text(
                    text = "Email",
                    fontSize = 15.sp,
                    color = Color.Gray
                )

                Spacer(Modifier.height(16.dp))

                // Edit button
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.primary
                    )
                ) {
                    Text("Edit Profile", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = colorScheme.background)
                }

                Spacer(Modifier.height(24.dp))

                Divider(color = Color.Black, thickness = 1.dp)

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Bookmarks",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF808080),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(Modifier.height(12.dp))
            }

            // Bookmark items
            items(profileList) { item ->
                ProfileCard(item, snackBarHostState, scope)
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
@Composable
fun ProfileCard(item: StoreModel, snackBarHostState: SnackbarHostState, scope: CoroutineScope) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageStoreRes),
                contentDescription = "",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.weight(1f).padding(start = 12.dp)
            ) {
                Text(
                    text = item.titleStoreMode,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(4.dp))
                Text(text = item.consoleType, color = Color.Gray)
                Spacer(Modifier.height(4.dp))
                Text(text = "${item.price} DT", color = Color.Black)
            }

            IconButton(
                onClick = {
                    scope.launch {
                        snackBarHostState.showSnackbar("Coming Soon")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCartCheckout,
                    contentDescription = "Add to cart",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}