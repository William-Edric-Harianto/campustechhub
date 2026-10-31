package com.edric.campustechhub.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


data class Community (
    val id: String,
    val name: String,
    val topic: String,
    val description: String,
    val memberAmount: Int,
    val isJoined: Boolean = false,
    val foregroundColor: Color,
    val backgroundColor: Color,
    val icon: ImageVector
)