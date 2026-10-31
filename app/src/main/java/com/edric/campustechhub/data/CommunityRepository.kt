package com.edric.campustechhub.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import com.edric.campustechhub.model.Community
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object CommunityRepository {
    private val _communities = MutableStateFlow<List<Community>>(
        listOf(
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
                id = "c_mobile",
                name = "Mobile Dev Community",
                topic = "Native Jetpack Compose & iOS",
                description = "Pengembangan aplikasi mobile multiplatform dan native.",
                memberAmount = 95,
                isJoined = false,
                foregroundColor = Color(0xFF027DB4),
                backgroundColor = Color(0xFFE1F5FE),
                icon = Icons.Default.PhoneAndroid
            )
        )
    )
    val communities: StateFlow<List<Community>> = _communities.asStateFlow()
}