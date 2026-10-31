package com.edric.campustechhub.model

import androidx.compose.ui.graphics.Color

enum class ProjectStatus(
    val foregroundColor: Color,
    val backgroundColor: Color,
) {
    IN_PROGRESS(
        backgroundColor = Color(0xFFF9F4C5),
        foregroundColor = Color(0xFFAB530A),
    ),
    COMPLETED(
        backgroundColor = Color(0xFFD9FCE8),
        foregroundColor = Color(0xFF59A674)
    )
}