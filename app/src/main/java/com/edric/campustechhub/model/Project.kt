package com.edric.campustechhub.model

data class Project(
    val id: String,
    val communityId: String,
    val name: String,
    val description: String,
    val techStack: List<TechStack>,
    val status: ProjectStatus = ProjectStatus.IN_PROGRESS
)
