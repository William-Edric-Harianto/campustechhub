package com.edric.campustechhub.navigation

import kotlinx.serialization.Serializable

@Serializable
object DashboardRoute

@Serializable
object CommunitiesRoute

@Serializable
data class ClubProjectsRoute(val communityId: String)

@Serializable
data class ProjectDetailRoute(val projectId: String)

// Backward compatibility type aliases
typealias ProfileDashboardRoute = DashboardRoute
typealias CommunitiesDirectoryRoute = CommunitiesRoute
typealias ClubProjectsDetailRoute = ClubProjectsRoute
