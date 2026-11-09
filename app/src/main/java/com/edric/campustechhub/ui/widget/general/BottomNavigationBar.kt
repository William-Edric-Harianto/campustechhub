package com.edric.campustechhub.ui.widget.general

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.edric.campustechhub.navigation.CommunitiesRoute
import com.edric.campustechhub.navigation.DashboardRoute

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        val isDashboardSelected = currentDestination?.hasRoute<DashboardRoute>() == true
        NavigationBarItem(
            selected = isDashboardSelected,
            onClick = {
                navController.navigate(DashboardRoute) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Dashboard"
                )
            },
            label = {
                Text(
                    text = "Dashboard",
                    fontWeight = if (isDashboardSelected) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF1967D2),
                selectedTextColor = Color(0xFF1967D2),
                unselectedIconColor = Color(0xFF9CA3AF),
                unselectedTextColor = Color(0xFF9CA3AF),
                indicatorColor = Color(0xFFE8F0FE)
            )
        )

        val isCommunitiesSelected = currentDestination?.hasRoute<CommunitiesRoute>() == true
        NavigationBarItem(
            selected = isCommunitiesSelected,
            onClick = {
                navController.navigate(CommunitiesRoute) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Groups,
                    contentDescription = "Communities"
                )
            },
            label = {
                Text(
                    text = "Communities",
                    fontWeight = if (isCommunitiesSelected) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF1967D2),
                selectedTextColor = Color(0xFF1967D2),
                unselectedIconColor = Color(0xFF9CA3AF),
                unselectedTextColor = Color(0xFF9CA3AF),
                indicatorColor = Color(0xFFE8F0FE)
            )
        )
    }
}
