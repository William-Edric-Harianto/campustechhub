package com.edric.campustechhub.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.edric.campustechhub.ui.screen.ClubProjectsDetail
import com.edric.campustechhub.ui.screen.CommunitiesDirectory
import com.edric.campustechhub.ui.screen.ProfileDashboard
import com.edric.campustechhub.ui.screen.ProjectDetail
import com.edric.campustechhub.ui.widget.general.BottomNavigationBar
import com.edric.campustechhub.viewmodel.CampusViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    sharedViewModel: CampusViewModel = viewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isTopLevelRoute = currentDestination?.hasRoute<DashboardRoute>() == true ||
            currentDestination?.hasRoute<CommunitiesRoute>() == true

    Scaffold(
        bottomBar = {
            if (isTopLevelRoute) {
                BottomNavigationBar(navController = navController)
            }
        },
        containerColor = Color(0xFFF7FAFB),
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DashboardRoute,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable<DashboardRoute> {
                ProfileDashboard(
                    viewModel = sharedViewModel,
                    onCommunityClick = { communityId ->
                        navController.navigate(ClubProjectsRoute(communityId = communityId))
                    },
                    onProjectClick = { projectId ->
                        navController.navigate(ProjectDetailRoute(projectId = projectId))
                    }
                )
            }

            composable<CommunitiesRoute> {
                CommunitiesDirectory(
                    viewModel = sharedViewModel,
                    onCommunityClick = { communityId ->
                        navController.navigate(ClubProjectsRoute(communityId = communityId))
                    }
                )
            }

            composable<ClubProjectsRoute> { backStackEntry ->
                val route = backStackEntry.toRoute<ClubProjectsRoute>()
                ClubProjectsDetail(
                    viewModel = sharedViewModel,
                    communityId = route.communityId,
                    onBackClick = { navController.popBackStack() },
                    onProjectClick = { projectId ->
                        navController.navigate(ProjectDetailRoute(projectId = projectId))
                    }
                )
            }

            composable<ProjectDetailRoute> { backStackEntry ->
                val route = backStackEntry.toRoute<ProjectDetailRoute>()
                ProjectDetail(
                    viewModel = sharedViewModel,
                    projectId = route.projectId,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
