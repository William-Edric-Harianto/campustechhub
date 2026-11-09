package com.edric.campustechhub.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterNone
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edric.campustechhub.model.Community
import com.edric.campustechhub.ui.widget.general.EmptyStateWidget
import com.edric.campustechhub.ui.widget.profiledashboard.AvailableProjectsWidget
import com.edric.campustechhub.ui.widget.profiledashboard.CountWidget
import com.edric.campustechhub.ui.widget.profiledashboard.DashboardHeaderWidget
import com.edric.campustechhub.ui.widget.profiledashboard.JoinedCommunityWidget
import com.edric.campustechhub.ui.widget.profiledashboard.StudentProfileCardWidget
import com.edric.campustechhub.viewmodel.CampusViewModel

@Composable
fun ProfileDashboard(
    viewModel: CampusViewModel = viewModel(),
    onCommunityClick: (String) -> Unit = {},
    onProjectClick: (String) -> Unit = {}
) {
    val mahasiswa by viewModel.mahasiswa.collectAsStateWithLifecycle()
    val communities by viewModel.communities.collectAsStateWithLifecycle()
    val projects by viewModel.projects.collectAsStateWithLifecycle()

    val joinedCommunities = communities.filter { it.isJoined }
    val joinedClubsCount = joinedCommunities.size
    val totalProjectsCount = projects.size

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FAFB)),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top App Header
        item(key = "header") {
            DashboardHeaderWidget(initials = "AP")
        }

        // Student Profile Card
        item(key = "student_profile") {
            StudentProfileCardWidget(mahasiswa = mahasiswa)
        }

        // Two Counter Badges
        item(key = "counters") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CountWidget(
                    icon = Icons.Default.Groups,
                    iconTint = Color(0xFF1967D2),
                    iconBackgroundColor = Color(0xFFE8F0FE),
                    count = joinedClubsCount,
                    title = "Joined Clubs",
                    subtitle = "Komunitas yang Diikuti",
                    modifier = Modifier.weight(1f)
                )

                CountWidget(
                    icon = Icons.Default.FilterNone,
                    iconTint = Color(0xFF55229E),
                    iconBackgroundColor = Color(0xFFF3E8FD),
                    count = totalProjectsCount,
                    title = "Total Projects",
                    subtitle = "Seluruh Proyek Dibuat",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Section: Semua Komunitas yang Diikuti
        item(key = "joined_communities_header") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "★ Semua Komunitas yang Diikuti ($joinedClubsCount)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Status: Joined",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1967D2)
                )
            }
        }

        if (joinedCommunities.isEmpty()) {
            item(key = "joined_communities_empty") {
                EmptyStateWidget(
                    message = "Belum bergabung dengan komunitas",
                    icon = Icons.Default.Groups
                )
            }
        } else {
            items(
                items = joinedCommunities,
                key = { "comm_${it.id}" }
            ) { community ->
                val projectCount = projects.count { it.communityId == community.id }
                JoinedCommunityWidget(
                    community = community,
                    projectCount = projectCount,
                    onClick = { onCommunityClick(community.id) }
                )
            }
        }

        // Section: Semua Proyek Mahasiswa
        item(key = "projects_header") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "❏ Semua Proyek Mahasiswa ($totalProjectsCount)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Total: $totalProjectsCount Proyek",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF6B7280)
                )
            }
        }

        if (projects.isEmpty()) {
            item(key = "projects_empty") {
                EmptyStateWidget(
                    message = "Belum ada proyek yang dicatat",
                    icon = Icons.Default.FilterNone
                )
            }
        } else {
            items(
                items = projects,
                key = { "proj_${it.id}" }
            ) { project ->
                val community = communities.firstOrNull { it.id == project.communityId }
                    ?: Community(
                        id = project.communityId,
                        name = "Community",
                        topic = "",
                        description = "",
                        memberAmount = 0,
                        isJoined = false,
                        foregroundColor = Color(0xFF1967D2),
                        backgroundColor = Color(0xFFE8F0FE),
                        icon = Icons.Default.Groups
                    )

                AvailableProjectsWidget(
                    project = project,
                    community = community,
                    onClick = { onProjectClick(project.id) }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ProfileDashboardPreview() {
    ProfileDashboard()
}
