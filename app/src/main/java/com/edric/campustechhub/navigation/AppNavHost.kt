package com.edric.campustechhub.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edric.campustechhub.ui.screen.ClubProjectsDetail
import com.edric.campustechhub.ui.screen.CommunitiesDirectory
import com.edric.campustechhub.ui.screen.ProfileDashboard
import com.edric.campustechhub.ui.screen.ProjectDetail

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FAFB))
    ) {
        NavHost(
            navController = navController,
            startDestination = ProfileDashboardRoute,
            modifier = modifier
        ) {
            composable<ProfileDashboardRoute> {
                ProfileDashboard(
                    TODO()
                )
//            HomeScreen(
//                onBookingClick = {navController.navigate(BookingRoute){launchSingleTop=true} },
//                onGalleryClick = { galleryId ->
//                    navController.navigate(GalleryDetailRoute(galleryId))
//                }
//            )
            }
            composable<CommunitiesDirectoryRoute> {
                CommunitiesDirectory(
                    TODO()
                )
//            BookingScreen(
//                onBookingSaved = {navController.popBackStack()},
//                onCancel = {navController.popBackStack()}
//            )
            }
            composable<ClubProjectsDetailRoute> {
                ClubProjectsDetail(
                    TODO()
                )
//            BookingScreen(
//                onBookingSaved = {navController.popBackStack()},
//                onCancel = {navController.popBackStack()}
//            )
            }
            composable<ProjectDetailRoute> {
                ProjectDetail(
                    projectId = TODO()
                )
//            GalleryDetailScreen(
//                onBackClick = {navController.popBackStack()},
//            )
            }
        }
    }
}