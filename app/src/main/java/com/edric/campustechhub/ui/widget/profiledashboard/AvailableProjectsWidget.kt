package com.edric.campustechhub.ui.widget.profiledashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import com.edric.campustechhub.data.ProjectRepository
import com.edric.campustechhub.model.Community
import com.edric.campustechhub.model.Project
import com.edric.util.displayName

@Composable
fun AvailableProjectsWidget(
    project: Project,
    community: Community,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFF3F4F6), RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Left part: Community Icon in rounded tinted box
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(community.backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = community.icon,
                    contentDescription = community.name,
                    tint = community.foregroundColor,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Right part: Community and Project Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(community.backgroundColor)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = community.name,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = community.foregroundColor
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = project.name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF111827) // Dark gray
                )

                Spacer(modifier = Modifier.height(3.dp))

                val techStackText = project.techStack.joinToString(" • ") { it.displayName }
                Text(
                    text = techStackText,
                    fontSize = 13.sp,
                    color = Color(0xFF6B7280) // Light gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = Color(0xFFF3F4F6), thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))

        // Cutoff part: Project Status strictly on the bottom right
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(project.status.backgroundColor)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(project.status.foregroundColor)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = project.status.displayName,
                    color = project.status.foregroundColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9FAFB)
@Composable
fun AvailableProjectsWidgetPreview() {
    val sampleProject = ProjectRepository.projects.value.first { it.id == "proj-gdsc-01" }
    val sampleCommunity = CommunityRepository.communities.value.first { it.id == "c_gdsc" }

    AvailableProjectsWidget(
        project = sampleProject,
        community = sampleCommunity,
        modifier = Modifier.padding(16.dp)
    )
}
