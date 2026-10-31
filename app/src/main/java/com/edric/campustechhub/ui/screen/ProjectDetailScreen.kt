package com.edric.campustechhub.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edric.campustechhub.viewmodel.CampusViewModel

@Composable
fun ProjectDetail(
    viewModel: CampusViewModel = viewModel(),
    projectId: String
){

}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ProjectDetailPreview(projectId:String = "proj-gdsc-1"){
    ProjectDetail(projectId = projectId)
}