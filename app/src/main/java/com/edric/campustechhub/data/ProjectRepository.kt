package com.edric.campustechhub.data

import com.edric.campustechhub.model.Project
import com.edric.campustechhub.model.ProjectStatus
import com.edric.campustechhub.model.TechStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object ProjectRepository {
    val initialProjects = listOf(
        Project(
            id = "proj-gdsc-01",
            communityId = "c_gdsc",
            name = "Smart Campus Navigation",
            description = "Sistem navigasi peta gedung kampus interaktif berbasis Jetpack Compose dan Google Maps SDK. Memfasilitasi mahasiswa dan dosen menemukan ruang perkuliahan, laboratorium komputer, perpustakaan, serta fasilitas umum kampus secara akurat disertai rute tercepat dan visualisasi lokasi real-time.",
            techStack = listOf(
                TechStack.KOTLIN,
                TechStack.JETPACK_COMPOSE,
                TechStack.ROOM,
                TechStack.GOOGLE_MAPS_SDK
            ),
            status = ProjectStatus.IN_PROGRESS
        ),
        Project(
            id = "proj-gdsc-02",
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
            id = "proj-cyber-01",
            communityId = "c_cyber",
            name = "Network Vulnerability Scanner",
            description = "Tool otomasi scanning celah keamanan port dan analisis paket jaringan lokal kampus.",
            techStack = listOf(
                TechStack.PYTHON
            ),
            status = ProjectStatus.IN_PROGRESS
        ),
        Project(
            id = "proj-mobile-01",
            communityId = "c_mobile",
            name = "Campus Event Hub App",
            description = "Aplikasi pengumuman dan pendaftaran kegiatan mahasiswa dengan notifikasi real-time.",
            techStack = listOf(
                TechStack.KOTLIN,
                TechStack.JETPACK_COMPOSE,
                TechStack.FIREBASE
            ),
            status = ProjectStatus.IN_PROGRESS
        )
    )

    private val _projects = MutableStateFlow<List<Project>>(initialProjects)
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    fun getProject(id: String): Project? {
        return _projects.value.firstOrNull { it.id == id }
    }

    fun addProject(
        communityId: String,
        name: String,
        description: String,
        techStack: List<TechStack>
    ): Project {
        val count = _projects.value.count { it.communityId == communityId } + 1
        val cleanCommunity = communityId.removePrefix("c_")
        val newId = "proj-$cleanCommunity-0$count"
        val newProject = Project(
            id = newId,
            communityId = communityId,
            name = name,
            description = description,
            techStack = techStack,
            status = ProjectStatus.IN_PROGRESS
        )
        _projects.update { listOf(newProject) + it }
        return newProject
    }

    fun deleteProject(id: String) {
        _projects.update { list -> list.filterNot { it.id == id } }
    }

    fun toggleProjectStatus(id: String) {
        _projects.update { list ->
            list.map { proj ->
                if (proj.id == id) {
                    val nextStatus = if (proj.status == ProjectStatus.IN_PROGRESS) {
                        ProjectStatus.COMPLETED
                    } else {
                        ProjectStatus.IN_PROGRESS
                    }
                    proj.copy(status = nextStatus)
                } else {
                    proj
                }
            }
        }
    }
}
