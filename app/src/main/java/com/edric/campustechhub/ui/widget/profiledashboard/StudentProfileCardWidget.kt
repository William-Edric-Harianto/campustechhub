package com.edric.campustechhub.ui.widget.profiledashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edric.campustechhub.data.MahasiswaRepository
import com.edric.campustechhub.model.Mahasiswa
import com.edric.campustechhub.viewmodel.CampusViewModel
import com.edric.util.displayName

@Composable
fun StudentProfileCardWidget(
    mahasiswa: Mahasiswa,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1E3A8A),
                        Color(0xFF3B82F6)
                    )
                )
            )
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                if (mahasiswa.avatar != null) {
                    Image(
                        bitmap = mahasiswa.avatar.asImageBitmap(),
                        contentDescription = "Avatar ${mahasiswa.nama}",
                    )
                } else {
                    Text(
                        text = "AP",
                        color = Color(0xFF1E40AF),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (mahasiswa.mahasiswaAktif) "MAHASISWA AKTIF" else "TIDAK AKTIF",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = mahasiswa.nama,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${mahasiswa.jurusan.displayName} • Angkatan ${mahasiswa.angkatan}",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "NIM: ${mahasiswa.nim}",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun StudentProfileCardWidget(
    viewModel: CampusViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val mahasiswa by viewModel.mahasiswa.collectAsState()
    StudentProfileCardWidget(mahasiswa = mahasiswa, modifier = modifier)
}

@Composable
@Preview(showBackground = true)
fun StudentProfileWidgetCardWidgetPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        StudentProfileCardWidget(mahasiswa = MahasiswaRepository.defaultMahasiswa)
    }
}
