package com.edric.campustechhub.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edric.campustechhub.ui.widget.communities.CommunitiesHeaderWidget
import com.edric.campustechhub.ui.widget.communities.CommunityCardWidget
import com.edric.campustechhub.viewmodel.CampusViewModel
import kotlinx.coroutines.launch

@Composable
fun CommunitiesDirectory(
    viewModel: CampusViewModel = viewModel(),
    onCommunityClick: (String) -> Unit = {}
) {
    val communities by viewModel.communities.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color(0xFFF7FAFB)
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(key = "header") {
                CommunitiesHeaderWidget()
            }

            items(
                items = communities,
                key = { "comm_${it.id}" }
            ) { community ->
                CommunityCardWidget(
                    community = community,
                    onOpenProjects = {
                        onCommunityClick(community.id)
                    },
                    onBlockedClick = {
                        coroutineScope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(
                                message = "Harap bergabung ke komunitas terlebih dahulu!",
                                actionLabel = "OK"
                            )
                        }
                    },
                    onToggleJoin = {
                        viewModel.toggleJoinCommunity(community.id)
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CommunitiesDirectoryPreview() {
    CommunitiesDirectory()
}
