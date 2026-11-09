package com.edric.campustechhub.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterNone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.edric.campustechhub.ui.widget.clubprojects.AddProjectDialogWidget
import com.edric.campustechhub.ui.widget.clubprojects.ClubHeaderCardWidget
import com.edric.campustechhub.ui.widget.clubprojects.ProjectCardWidget
import com.edric.campustechhub.ui.widget.general.EmptyStateWidget
import com.edric.campustechhub.viewmodel.CampusViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubProjectsDetail(
    viewModel: CampusViewModel = viewModel(),
    communityId: String = "c_gdsc",
    onBackClick: () -> Unit = {},
    onProjectClick: (String) -> Unit = {}
) {
    val communities by viewModel.communities.collectAsStateWithLifecycle()
    val allProjects by viewModel.projects.collectAsStateWithLifecycle()

    val community = communities.firstOrNull { it.id == communityId }
        ?: Community(
            id = communityId,
            name = "Community",
            topic = "",
            description = "",
            memberAmount = 0,
            isJoined = true,
            foregroundColor = Color(0xFF1967D2),
            backgroundColor = Color(0xFFE8F0FE),
            icon = Icons.Default.FilterNone
        )

    val clubProjects = allProjects.filter { it.communityId == communityId }
    var showAddDialog by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "${community.name} Projects",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF111827)
                        )
                        if (community.topic.isNotBlank()) {
                            Text(
                                text = community.topic,
                                fontSize = 12.sp,
                                color = Color(0xFF6B7280)
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF111827)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showAddDialog = true },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Project"
                    )
                },
                text = {
                    Text(
                        text = "Add Project",
                        fontWeight = FontWeight.Bold
                    )
                },
                containerColor = Color(0xFFE8F0FE),
                contentColor = Color(0xFF1967D2),
                shape = RoundedCornerShape(16.dp)
            )
        },
        containerColor = Color(0xFFF7FAFB)
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Club Header Card
            item(key = "club_header") {
                ClubHeaderCardWidget(
                    community = community,
                    activeProjectCount = clubProjects.size
                )
            }

            // Section Header
            item(key = "section_header") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "❏ Daftar Proyek Mahasiswa (${clubProjects.size})",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111827)
                    )
                }
            }

            if (clubProjects.isEmpty()) {
                item(key = "empty_state") {
                    EmptyStateWidget(
                        message = "Belum ada proyek untuk komunitas ini. Tekan tombol + Add Project untuk menambahkan.",
                        icon = Icons.Default.FilterNone
                    )
                }
            } else {
                items(
                    items = clubProjects,
                    key = { it.id }
                ) { project ->
                    ProjectCardWidget(
                        project = project,
                        community = community,
                        onProjectClick = { onProjectClick(project.id) },
                        onDeleteClick = { viewModel.deleteProject(project.id) }
                    )
                }
            }
        }

        if (showAddDialog) {
            AddProjectDialogWidget(
                community = community,
                onDismiss = { showAddDialog = false },
                onSave = { name, description, techStack ->
                    viewModel.addProject(
                        communityId = communityId,
                        name = name,
                        description = description,
                        techStack = techStack
                    )
                    showAddDialog = false
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ClubProjectsDetailPreview() {
    ClubProjectsDetail()
}
