package com.edric.campustechhub.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edric.campustechhub.ui.widget.general.EmptyStateWidget
import com.edric.campustechhub.ui.widget.projectdetail.ProjectDetailDescriptionWidget
import com.edric.campustechhub.ui.widget.projectdetail.ProjectDetailHeroWidget
import com.edric.campustechhub.ui.widget.projectdetail.ProjectDetailStatusWidget
import com.edric.campustechhub.ui.widget.projectdetail.ProjectDetailTechStackWidget
import com.edric.campustechhub.viewmodel.CampusViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetail(
    viewModel: CampusViewModel = viewModel(),
    projectId: String,
    onBackClick: () -> Unit = {}
) {
    val projects by viewModel.projects.collectAsStateWithLifecycle()
    val communities by viewModel.communities.collectAsStateWithLifecycle()

    val project = projects.firstOrNull { it.id == projectId }
    val community = project?.let { p ->
        communities.firstOrNull { it.id == p.communityId }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Project Detail",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111827)
                    )
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
        containerColor = Color(0xFFF7FAFB)
    ) { innerPadding ->
        if (project == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                EmptyStateWidget(
                    message = "Proyek tidak ditemukan",
                    icon = Icons.Default.Info
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Hero banner with community badge and project title
                ProjectDetailHeroWidget(
                    project = project,
                    community = community
                )

                // Interactive Status Toggle (Reactive sync with Shared ViewModel)
                ProjectDetailStatusWidget(
                    project = project,
                    onToggleStatus = {
                        viewModel.toggleProjectStatus(project.id)
                    }
                )

                // Full Description Card
                ProjectDetailDescriptionWidget(
                    description = project.description
                )

                // Selected Tech Stack Badges
                ProjectDetailTechStackWidget(
                    techStacks = project.techStack
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ProjectDetailPreview(projectId: String = "proj-gdsc-01") {
    ProjectDetail(projectId = projectId)
}
