package com.edric.campustechhub.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import com.edric.campustechhub.model.Community
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object CommunityRepository {
    val initialCommunities = listOf(
        Community(
            id = "c_gdsc",
            name = "GDSC",
            topic = "Google Developer Student Club",
            description = "Fokus pada ekosistem Google: Android Compose, Flutter, Firebase, dan Google Cloud Platform.",
            memberAmount = 142,
            isJoined = true,
            foregroundColor = Color(0xFF1967D2),
            backgroundColor = Color(0xFFE8F0FE),
            icon = Icons.Default.Code
        ),
        Community(
            id = "c_cyber",
            name = "CyberSecurity Guild",
            topic = "Security & Pentesting",
            description = "Eksplorasi ethical hacking, CTF challenges, reverse engineering, dan pertahanan jaringan kampus.",
            memberAmount = 88,
            isJoined = true,
            foregroundColor = Color(0xFFC5221F),
            backgroundColor = Color(0xFFFCE8E6),
            icon = Icons.Default.Shield
        ),
        Community(
            id = "c_mobile",
            name = "Mobile Dev Community",
            topic = "Native Jetpack Compose & iOS",
            description = "Pengembangan aplikasi mobile multiplatform dan native Android serta iOS.",
            memberAmount = 95,
            isJoined = true,
            foregroundColor = Color(0xFF027DB4),
            backgroundColor = Color(0xFFE1F5FE),
            icon = Icons.Default.PhoneAndroid
        ),
        Community(
            id = "c_ai",
            name = "AI Society",
            topic = "Machine Learning & LLM",
            description = "Riset terapan deep learning, computer vision, agen cerdas, dan fine-tuning large language models.",
            memberAmount = 115,
            isJoined = false,
            foregroundColor = Color(0xFF8430CE),
            backgroundColor = Color(0xFFF3E8FD),
            icon = Icons.Default.Star
        ),
        Community(
            id = "c_web",
            name = "Web Tech Enthusiasts",
            topic = "Fullstack Modern Web",
            description = "Mendalami ekosistem modern web development dengan React, Next.js, TypeScript, dan edge computing.",
            memberAmount = 120,
            isJoined = false,
            foregroundColor = Color(0xFFE65100),
            backgroundColor = Color(0xFFFFF3E0),
            icon = Icons.Default.Language
        ),
        Community(
            id = "c_cloud",
            name = "Cloud & DevOps Club",
            topic = "Cloud Infrastructure & CI/CD",
            description = "Belajar arsitektur cloud, containerization Docker/Kubernetes, automation pipeline, dan reliability engineering.",
            memberAmount = 76,
            isJoined = false,
            foregroundColor = Color(0xFF00796B),
            backgroundColor = Color(0xFFE0F2F1),
            icon = Icons.Default.Cloud
        ),
        Community(
            id = "c_game",
            name = "Game Development Studio",
            topic = "Unity & Unreal Engine",
            description = "Pengembangan video game 2D & 3D, physics engine, game design, dan shader programming.",
            memberAmount = 64,
            isJoined = false,
            foregroundColor = Color(0xFF5E35B1),
            backgroundColor = Color(0xFFEDE7F6),
            icon = Icons.Default.SportsEsports
        )
    )

    private val _communities = MutableStateFlow<List<Community>>(initialCommunities)
    val communities: StateFlow<List<Community>> = _communities.asStateFlow()

    fun getCommunity(id: String): Community? {
        return _communities.value.firstOrNull { it.id == id }
    }

    fun toggleJoin(communityId: String) {
        _communities.update { list ->
            list.map { comm ->
                if (comm.id == communityId) {
                    comm.copy(isJoined = !comm.isJoined)
                } else {
                    comm
                }
            }
        }
    }
}
