package com.edric.campustechhub.ui.widget.profiledashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edric.campustechhub.data.CommunityRepository
import com.edric.campustechhub.model.Community

@Composable
fun JoinedCommunityWidget(
    community: Community,
    projectCount: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFF3F4F6), RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Dynamic Icon Container
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(community.backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = community.icon,
                contentDescription = community.name,
                tint = community.foregroundColor,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Community Title and Info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = community.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF111827) // Dark gray
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${community.topic} • $projectCount Proyek",
                fontSize = 14.sp,
                color = Color(0xFF6B7280) // Light gray
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // "Joined" Badge
        Row(
            modifier = Modifier
                .border(1.dp, Color(0xFFF8D2D0), RoundedCornerShape(12.dp)) // Light pink border
                .background(Color(0xFFFCE8E6), RoundedCornerShape(12.dp)) // Lighter pink bg
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Joined",
                color = Color(0xFFC5221F), // Red text
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Joined Checkmark",
                tint = Color(0xFFC5221F), // Red icon
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9FAFB)
@Composable
fun JoinedCommunityWidgetPreview() {
    val gdscCommunity = CommunityRepository.communities.value.first { it.id == "c_gdsc" }

    JoinedCommunityWidget(
        community = gdscCommunity,
        projectCount = 2,
        modifier = Modifier.padding(16.dp)
    )
}
