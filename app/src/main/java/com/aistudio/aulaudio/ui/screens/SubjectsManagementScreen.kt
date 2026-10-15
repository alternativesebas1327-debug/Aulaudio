package com.aistudio.aulaudio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.aulaudio.data.model.AudioRecording
import com.aistudio.aulaudio.data.model.Subject
import com.aistudio.aulaudio.theme.CarbonBlack
import com.aistudio.aulaudio.theme.SurfaceBorderSubtle
import com.aistudio.aulaudio.theme.SurfaceCanvas
import com.aistudio.aulaudio.theme.SurfaceContainer
import com.aistudio.aulaudio.theme.TextPrimary
import com.aistudio.aulaudio.theme.TextSecondary
import com.aistudio.aulaudio.theme.TextTertiary
import com.aistudio.aulaudio.theme.White
import com.aistudio.aulaudio.ui.components.AulaIcons

@Composable
fun SubjectsManagementScreen(
    subjects: List<Subject>,
    recordings: List<AudioRecording>,
    onSelectSubject: (Subject) -> Unit,
    onRecordSubject: (Subject) -> Unit,
    onAddSubject: (Subject) -> Unit,
    modifier: Modifier = Modifier
) {
    var showCreateDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceCanvas)
                    .statusBarsPadding()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "ORGANIZACIÓN",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextTertiary,
                            letterSpacing = 1.5.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Materias",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary,
                            letterSpacing = (-0.03).sp
                        )
                    }

                    FloatingActionButton(
                        onClick = { showCreateDialog = true },
                        modifier = Modifier.size(44.dp).testTag("fab_add_subject"),
                        shape = CircleShape,
                        containerColor = CarbonBlack,
                        contentColor = White
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Nueva materia",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            HorizontalDivider(color = SurfaceBorderSubtle, thickness = 1.dp)

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
            ) {
                items(subjects, key = { it.id }) { subject ->
                    val sessionCount = recordings.count { it.subjectId == subject.id }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onSelectSubject(subject) }
                            .padding(vertical = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(CarbonBlack)
                                )
                                Spacer(modifier = Modifier.width(14.dp))
                                Column {
                                    Text(
                                        text = subject.name,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = TextPrimary
                                    )
                                    val details = buildString {
                                        if (subject.classroom.isNotBlank()) append(subject.classroom)
                                        if (subject.classroom.isNotBlank() && subject.professor.isNotBlank()) append(" · ")
                                        if (subject.professor.isNotBlank()) append(subject.professor)
                                    }
                                    if (details.isNotBlank()) {
                                        Text(
                                            text = details,
                                            fontSize = 12.sp,
                                            color = TextSecondary
                                        )
                                    }
                                    Text(
                                        text = "$sessionCount sesiones grabadas",
                                        fontSize = 11.sp,
                                        color = TextTertiary
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(SurfaceContainer)
                                    .clickable { onRecordSubject(subject) }
                                    .padding(horizontal = 10.dp, vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = AulaIcons.Mic,
                                        contentDescription = "Grabar",
                                        tint = CarbonBlack,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = "Grabar",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = CarbonBlack
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = SurfaceBorderSubtle, thickness = 0.5.dp)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }

    if (showCreateDialog) {
        var name by remember { mutableStateOf("") }
        var classroom by remember { mutableStateOf("") }
        var professor by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            title = { Text("Nueva Materia", fontWeight = FontWeight.SemiBold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nombre de materia") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = classroom,
                        onValueChange = { classroom = it },
                        label = { Text("Aula") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = professor,
                        onValueChange = { professor = it },
                        label = { Text("Profesor/a") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (name.isNotBlank()) {
                            val newSubj = Subject(
                                id = "subj_${System.currentTimeMillis()}",
                                name = name.trim(),
                                classroom = classroom.trim(),
                                professor = professor.trim()
                            )
                            onAddSubject(newSubj)
                            showCreateDialog = false
                        }
                    }
                ) {
                    Text("Crear", color = CarbonBlack, fontWeight = FontWeight.SemiBold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }
}
