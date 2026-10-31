package com.edric.campustechhub.data

import com.edric.campustechhub.model.Project
import com.edric.campustechhub.model.ProjectStatus
import com.edric.campustechhub.model.TechStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ProjectRepository {

    private val _projects = MutableStateFlow<List<Project>>(
        listOf(
            Project(
                id = 1,
                communityId = "c_gdsc",
                name = "Smart Campus Navigation",
                description = "Navigasi peta gedung perkuliahan, lab komputer, dan fasilitas kampus real-time.",
                techStack = listOf(
                    TechStack.KOTLIN,
                    TechStack.JETPACK_COMPOSE,
                    TechStack.ROOM,
                    TechStack.GOOGLE_MAPS_SDK
                ),
                status = ProjectStatus.IN_PROGRESS
            ),
            Project(
                id = 2,
                communityId = "c_gdsc",
                name = "Student Attendance QR Scanner",
                description = "Pemindai barcode & QR presensi kehadiran workshop berbasis CameraX & Firebase.",
                techStack = listOf(
                    TechStack.JETPACK_COMPOSE,
                    TechStack.FIREBASE,
                    TechStack.CAMERAX
                ),
                status = ProjectStatus.COMPLETED
            ),
            Project(
                id = 3,
                communityId = "c_gdsc",
                name = "Developer RSVP Portal",
                description = "Portal pendaftaran webinar dan bootcamp teknologi sinkronisasi Ktor API server.",
                techStack = listOf(
                    TechStack.KOTLIN,
                    TechStack.KTOR,
                    TechStack.JETPACK_COMPOSE
                ),
                status = ProjectStatus.IN_PROGRESS
            )
        )
    )
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()
}