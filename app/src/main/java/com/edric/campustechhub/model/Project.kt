package com.edric.campustechhub.model

data class Project(
    val name: String,
    val description: String,
    val techStack: List<TechStack>,
    val id: Int,
    val communityId: String,
    val status: ProjectStatus
)
