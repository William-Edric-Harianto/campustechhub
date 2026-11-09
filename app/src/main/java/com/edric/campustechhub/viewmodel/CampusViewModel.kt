package com.edric.campustechhub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edric.campustechhub.data.CommunityRepository
import com.edric.campustechhub.data.MahasiswaRepository
import com.edric.campustechhub.data.ProjectRepository
import com.edric.campustechhub.model.Community
import com.edric.campustechhub.model.Mahasiswa
import com.edric.campustechhub.model.Project
import com.edric.campustechhub.model.TechStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CampusViewModel : ViewModel() {

    // Repositories act as backing store, or directly expose flows
    val mahasiswa: StateFlow<Mahasiswa> = MutableStateFlow(MahasiswaRepository.defaultMahasiswa).asStateFlow()
    val communities: StateFlow<List<Community>> = CommunityRepository.communities
    val projects: StateFlow<List<Project>> = ProjectRepository.projects

    val joinedCommunitiesCount: StateFlow<Int> = communities.map { list ->
        list.count { it.isJoined }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, 3)

    val totalProjectsCount: StateFlow<Int> = projects.map { list ->
        list.size
    }.stateIn(viewModelScope, SharingStarted.Eagerly, 4)

    fun toggleJoinCommunity(communityId: String) {
        CommunityRepository.toggleJoin(communityId)
    }

    fun addProject(
        communityId: String,
        name: String,
        description: String,
        techStack: List<TechStack>
    ) {
        ProjectRepository.addProject(communityId, name, description, techStack)
    }

    fun deleteProject(projectId: String) {
        ProjectRepository.deleteProject(projectId)
    }

    fun toggleProjectStatus(projectId: String) {
        ProjectRepository.toggleProjectStatus(projectId)
    }

    fun getCommunity(communityId: String): Community? {
        return communities.value.firstOrNull { it.id == communityId }
    }

    fun getProject(projectId: String): Project? {
        return projects.value.firstOrNull { it.id == projectId }
    }

    fun getProjectsForCommunity(communityId: String): List<Project> {
        return projects.value.filter { it.communityId == communityId }
    }
}
