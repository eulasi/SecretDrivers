package com.example.secretdrivers.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.secretdrivers.ui.screens.DriversScreen
import com.example.secretdrivers.ui.screens.InfoScreen
import com.example.secretdrivers.ui.screens.LoginScreen

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun BottomNavBarPreview() {
    val navController = rememberNavController()
    BottomNavBar(navController)
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Exit,
        BottomNavItem.Drivers,
        BottomNavItem.Info
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier
                    .background(Color.White)
                    .navigationBarsPadding(),
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                label = {
                    val isSelected = currentRoute == screen.route
                    val textColor = if (isSelected) Color.Red else Color.Black
                    val fontWeight = if (isSelected) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal
                    Text(
                        text = screen.title,
                        color = textColor,
                        fontWeight = fontWeight
                    )
                },
                selected = currentRoute == screen.route,

                onClick = {
                    navController.navigate(screen.destination)

                }
            )
        }
    }
    Divider(color = Color.Gray, thickness = 1.dp)
}

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val destination: String // Destination screen for navigation
) {
    object Exit : BottomNavItem("login", "Exit", Icons.Default.Home, "login")
    object Drivers : BottomNavItem("drivers", "Drivers", Icons.Default.Face, "drivers")
    object Info : BottomNavItem("info", "Info", Icons.Default.Info, "info")
}
