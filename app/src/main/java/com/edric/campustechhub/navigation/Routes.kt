package com.edric.campustechhub.navigation

import kotlinx.serialization.Serializable

@Serializable
object ProfileDashboardRoute

@Serializable
object CommunitiesDirectoryRoute

@Serializable
data class ClubProjectsDetailRoute(val communityId: String)

@Serializable
data class ProjectDetailRoute(val projectId: String)