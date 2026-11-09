package com.edric.campustechhub.ui.widget.projectdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edric.campustechhub.model.Community
import com.edric.campustechhub.model.Project

@Composable
fun ProjectDetailHeroWidget(
    project: Project,
    community: Community?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF1E293B)) // Dark slate navy
            .padding(20.dp)
    ) {
        Column {
            // Origin Community Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(community?.backgroundColor ?: Color(0xFF1E40AF))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = community?.name ?: project.communityId,
                    color = community?.foregroundColor ?: Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Project Name
            Text(
                text = project.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                lineHeight = 28.sp
            )
        }
    }
}

