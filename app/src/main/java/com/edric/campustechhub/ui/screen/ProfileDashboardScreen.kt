package com.edric.campustechhub.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edric.campustechhub.viewmodel.CampusViewModel

@Composable
fun ProfileDashboard(
    viewModel: CampusViewModel = viewModel()
){

}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ProfileDashboardPreview(){
    ProfileDashboard()
}