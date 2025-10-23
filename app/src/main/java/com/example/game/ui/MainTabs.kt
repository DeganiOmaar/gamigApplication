package com.example.game.ui


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.game.navigation.Routes
import com.example.game.screens.myScreens.news.NewsScreen
import com.example.game.screens.myScreens.profile.ProfileScreen
import com.example.game.screens.myScreens.store.StoreScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTabs() {
    val navController = rememberNavController()

    data class TabItem(val route: String, val label: String, val icon: ImageVector)
    val tabs = listOf(
        TabItem(Routes.MainRoute.News.route, "News", Icons.Filled.Article,),
        TabItem(Routes.MainRoute.Store.route, "Store", Icons.Filled.Store),
        TabItem(Routes.MainRoute.Profile.route, "Profile", Icons.Filled.Person),
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    //My Title
    val title = when (currentRoute) {
        Routes.MainRoute.News.route -> "News"
        Routes.MainRoute.Store.route -> "Store"
        Routes.MainRoute.Profile.route -> "Profile"
        else -> "App"
    }

    // Color
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        topBar = {
            TopAppBar(
                //top Bar color
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = colorScheme.primary
                ),
                title = { Text(text = title, color = colorScheme.background) },

                actions = {
                    Spacer(Modifier.width(12.dp))
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notifications",
                        tint = colorScheme.background
                    )
                    Spacer(Modifier.width(12.dp))
                    if (title == "Store"){
                    Icon(
                        imageVector = Icons.Filled.ShoppingCartCheckout,
                        contentDescription = "Store",
                        tint = colorScheme.background
                    )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar (
                containerColor = colorScheme.primary
            ){

                tabs.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label, tint = colorScheme.secondary) },
                        label = { Text(item.label, color = colorScheme.secondary) }
                    )
                }
            }
        }
    ) { inner ->
        NavHost(
            navController = navController,
            startDestination = Routes.MainRoute.News.route,
            modifier = Modifier.padding(inner)
        ) {
            composable(Routes.MainRoute.News.route)   { NewsScreen() }
            composable(Routes.MainRoute.Store.route)  { StoreScreen() }
            composable(Routes.MainRoute.Profile.route){ ProfileScreen() }
        }
    }
}