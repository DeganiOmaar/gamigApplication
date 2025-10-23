package com.example.game.screens.myScreens.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.game.R

@Composable
fun NewsScreen() {
    val newsList = listOf(
        NewsItem(
            imageRes = R.drawable.news1,
            title = "Top 10 Gaming News of the Day",
            description = "Here’s the latest news from the gaming and e-sports world."
        ),
        NewsItem(
            imageRes = R.drawable.news2,
            title = "The Legend of Zelda",
            description = "Nintendo reveals new mechanics and exploration details."
        ),
        NewsItem(
            imageRes = R.drawable.news3,
            title = "God of War Ragnarok",
            description = "A major patch improves combat and adds quests."
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(newsList) { item ->
            NewsCard(item)
        }
    }
}

@Composable
fun NewsCard(item: NewsItem) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.background
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )

        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description,
            )
        }
    }
}