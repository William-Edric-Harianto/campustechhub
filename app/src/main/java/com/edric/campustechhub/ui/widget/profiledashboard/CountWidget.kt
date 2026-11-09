package com.edric.campustechhub.ui.widget.profiledashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterNone
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CountWidget(
    icon: ImageVector,
    iconTint: Color,
    iconBackgroundColor: Color,
    count: Int,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFF3F4F6), RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconTint,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = count.toString(),
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF111827) // Dark gray/black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color(0xFF6B7280) // Light gray
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF9FAFB)
fun CountWidgetPreview() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CountWidget(
            icon = Icons.Default.Groups,
            iconTint = Color(0xFF1967D2), // Blue
            iconBackgroundColor = Color(0xFFE8F0FE), // Light Blue
            count = 3,
            title = "Joined Clubs",
            subtitle = "Komunitas yang Diikuti",
            modifier = Modifier.weight(1f)
        )

        CountWidget(
            icon = Icons.Default.FilterNone,
            iconTint = Color(0xFF55229E), // Purple
            iconBackgroundColor = Color(0xFFF3E8FD), // Light Purple
            count = 4,
            title = "Total Projects",
            subtitle = "Seluruh Proyek Dibuat",
            modifier = Modifier.weight(1f)
        )
    }
}