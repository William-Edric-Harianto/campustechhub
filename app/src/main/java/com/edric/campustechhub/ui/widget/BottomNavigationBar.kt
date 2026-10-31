package com.edric.campustechhub.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.edric.campustechhub.navigation.CommunitiesDirectoryRoute
import com.edric.campustechhub.navigation.ProfileDashboardRoute

@Composable
fun NavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            selected = currentDestination?.hasRoute<ProfileDashboardRoute>() == true,
            onClick = {
                navController.navigate(ProfileDashboardRoute) {
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
            label = { Text("Dashboard") }
        )
        NavigationBarItem(
            selected = currentDestination?.hasRoute<CommunitiesDirectoryRoute>() == true,
            onClick = {
                navController.navigate(CommunitiesDirectoryRoute) {
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
            label = { Text("Communities") }
        )
    }
}