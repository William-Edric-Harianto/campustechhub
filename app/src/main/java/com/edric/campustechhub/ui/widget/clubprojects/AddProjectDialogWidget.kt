package com.edric.campustechhub.ui.widget.clubprojects

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.edric.campustechhub.model.Community
import com.edric.campustechhub.model.TechStack
import com.edric.util.displayName

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddProjectDialogWidget(
    community: Community,
    onDismiss: () -> Unit,
    onSave: (name: String, description: String, techStack: List<TechStack>) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedTechStacks by remember { mutableStateOf(setOf<TechStack>()) }

    val isValid = name.isNotBlank() && description.isNotBlank() && selectedTechStacks.isNotEmpty()

    // Dialog hanya boleh ditutup lewat tombol "Cancel"
    Dialog(
        onDismissRequest = { /* Diabaikan: dialog hanya boleh ditutup lewat tombol "Cancel" */ },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp)),
            color = Color.White,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header
                Text(
                    text = "Add Project",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827)
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Komunitas: ${community.name} (${community.topic})",
                    fontSize = 12.sp,
                    color = Color(0xFF6B7280)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Judul Proyek: OutlinedTextField (single-line)
                Text(
                    text = "Judul Proyek *",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF374151)
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Smart Campus Navigation", fontSize = 14.sp, color = Color(0xFF9CA3AF)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF1967D2),
                        unfocusedBorderColor = Color(0xFFD1D5DB)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Deskripsi: OutlinedTextField (multi-line, minimal 3 baris)
                Text(
                    text = "Deskripsi Proyek (Minimal 3 Baris) *",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF374151)
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = {
                        Text(
                            "Sistem navigasi peta gedung kampus interaktif berbasis Jetpack Compose...",
                            fontSize = 13.sp,
                            color = Color(0xFF9CA3AF)
                        )
                    },
                    minLines = 3,
                    maxLines = 5,
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF1967D2),
                        unfocusedBorderColor = Color(0xFFD1D5DB)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tech Stack: Multi-selection chips
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tech Stack (Pilihan Multi-Selection) *",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF374151)
                    )
                    Text(
                        text = "${selectedTechStacks.size} Dipilih",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (selectedTechStacks.isNotEmpty()) Color(0xFF1967D2) else Color(0xFF9CA3AF)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    TechStack.entries.forEach { tech ->
                        val isSelected = tech in selectedTechStacks
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(if (isSelected) Color(0xFFE8F0FE) else Color(0xFFF3F4F6))
                                .border(
                                    width = 1.dp,
                                    color = if (isSelected) Color(0xFF1967D2) else Color.Transparent,
                                    shape = RoundedCornerShape(50)
                                )
                                .clickable {
                                    selectedTechStacks = if (isSelected) {
                                        selectedTechStacks - tech
                                    } else {
                                        selectedTechStacks + tech
                                    }
                                }
                                .padding(horizontal = 12.dp, vertical = 7.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color(0xFF1967D2),
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                            } else {
                                Text(
                                    text = "+ ",
                                    color = Color(0xFF6B7280),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = tech.displayName,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color(0xFF1967D2) else Color(0xFF4B5563)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Actions: Cancel and Save
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF6B7280)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = {
                            if (isValid) {
                                onSave(name.trim(), description.trim(), selectedTechStacks.toList())
                            }
                        },
                        enabled = isValid,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1967D2),
                            disabledContainerColor = Color(0xFFE5E7EB),
                            contentColor = Color.White,
                            disabledContentColor = Color(0xFF9CA3AF)
                        )
                    ) {
                        Text(
                            text = "Save",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

