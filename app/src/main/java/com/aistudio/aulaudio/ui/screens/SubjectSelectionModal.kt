package com.aistudio.aulaudio.ui.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.aulaudio.data.model.Subject
import com.aistudio.aulaudio.theme.CarbonBlack
import com.aistudio.aulaudio.theme.LiveRed
import com.aistudio.aulaudio.theme.SurfaceBorderSubtle
import com.aistudio.aulaudio.theme.SurfaceContainer
import com.aistudio.aulaudio.theme.TextPrimary
import com.aistudio.aulaudio.theme.TextSecondary
import com.aistudio.aulaudio.theme.TextTertiary
import com.aistudio.aulaudio.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectSelectionModal(
    subjects: List<Subject>,
    selectedSubjectId: String,
    onSelectSubject: (String) -> Unit,
    onStartRecording: (Subject) -> Unit,
    onAddNewSubject: (Subject) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var searchQuery by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }

    val filteredSubjects = remember(subjects, searchQuery) {
        if (searchQuery.isBlank()) {
            subjects
        } else {
            subjects.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                it.classroom.contains(searchQuery, ignoreCase = true) ||
                it.professor.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = White,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .width(36.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(Color(0xFFE4E4E7))
            )
        },
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            // Header: Title and Add button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "Asignatura",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary,
                        letterSpacing = (-0.02).sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Selecciona el espacio para organizar el audio",
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                }

                IconButton(
                    onClick = { showAddDialog = true },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainer)
                        .testTag("add_subject_button")
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Crear asignatura",
                        tint = CarbonBlack,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Subtle Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SurfaceContainer)
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar",
                    tint = TextTertiary,
                    modifier = Modifier.size(19.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                androidx.compose.foundation.text.BasicTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp,
                        color = TextPrimary
                    ),
                    modifier = Modifier.weight(1f),
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = "Buscar asignatura o aula...",
                                fontSize = 14.sp,
                                color = TextTertiary
                            )
                        }
                        innerTextField()
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Subjects list
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 280.dp)
            ) {
                items(filteredSubjects, key = { it.id }) { subject ->
                    val isChecked = subject.id == selectedSubjectId
                    SubjectRadioRow(
                        subject = subject,
                        isSelected = isChecked,
                        onClick = { onSelectSubject(subject.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Start Recording Action Button with pulsing red dot
            val infiniteTransition = rememberInfiniteTransition(label = "pulse")
            val pulseAlpha by infiniteTransition.animateFloat(
                initialValue = 0.4f,
                targetValue = 1.0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(800),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "pulseAlpha"
            )

            val activeSubject = subjects.find { it.id == selectedSubjectId } ?: subjects.firstOrNull()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(CarbonBlack)
                    .clickable {
                        if (activeSubject != null) {
                            onStartRecording(activeSubject)
                        }
                    }
                    .testTag("modal_start_record_button"),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(LiveRed)
                            .alpha(pulseAlpha)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Grabar",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = White,
                        letterSpacing = (-0.01).sp
                    )
                }
            }

            // Cancel Button
            TextButton(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 12.dp)
                    .testTag("modal_cancel_button")
            ) {
                Text(
                    text = "Cancelar",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextTertiary
                )
            }
        }
    }

    if (showAddDialog) {
        var newName by remember { mutableStateOf("") }
        var newClassroom by remember { mutableStateOf("") }
        var newProfessor by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = {
                Text(
                    text = "Nueva Asignatura",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = newName,
                        onValueChange = { newName = it },
                        label = { Text("Nombre de la materia") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newClassroom,
                        onValueChange = { newClassroom = it },
                        label = { Text("Aula o laboratorio (opcional)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newProfessor,
                        onValueChange = { newProfessor = it },
                        label = { Text("Profesor/a (opcional)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (newName.isNotBlank()) {
                            val id = "subj_${System.currentTimeMillis()}"
                            val newSubject = Subject(
                                id = id,
                                name = newName.trim(),
                                classroom = newClassroom.trim(),
                                professor = newProfessor.trim()
                            )
                            onAddNewSubject(newSubject)
                            onSelectSubject(id)
                            showAddDialog = false
                        }
                    }
                ) {
                    Text("Crear", color = CarbonBlack, fontWeight = FontWeight.SemiBold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }
}

@Composable
private fun SubjectRadioRow(
    subject: Subject,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Status dot (Black if selected, subtle gray if not)
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) CarbonBlack else Color(0xFFD4D4D8))
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = subject.name,
                        fontSize = 15.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) CarbonBlack else TextPrimary,
                        letterSpacing = (-0.01).sp
                    )
                    if (subject.classroom.isNotBlank() || subject.professor.isNotBlank()) {
                        val desc = buildString {
                            if (subject.classroom.isNotBlank()) append(subject.classroom)
                            if (subject.classroom.isNotBlank() && subject.professor.isNotBlank()) append(" • ")
                            if (subject.professor.isNotBlank()) append(subject.professor)
                        }
                        Text(
                            text = desc,
                            fontSize = 12.sp,
                            color = TextTertiary
                        )
                    }
                }
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Seleccionado",
                    tint = CarbonBlack,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        HorizontalDivider(
            color = SurfaceBorderSubtle,
            thickness = 0.5.dp
        )
    }
}
