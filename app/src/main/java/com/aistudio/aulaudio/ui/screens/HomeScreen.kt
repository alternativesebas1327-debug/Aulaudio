package com.aistudio.aulaudio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.aulaudio.data.model.AudioRecording
import com.aistudio.aulaudio.data.model.Subject
import com.aistudio.aulaudio.theme.CarbonBlack
import com.aistudio.aulaudio.theme.LiveRed
import com.aistudio.aulaudio.theme.SurfaceBorder
import com.aistudio.aulaudio.theme.SurfaceBorderSubtle
import com.aistudio.aulaudio.theme.SurfaceCanvas
import com.aistudio.aulaudio.theme.TextPrimary
import com.aistudio.aulaudio.theme.TextSecondary
import com.aistudio.aulaudio.theme.TextTertiary
import com.aistudio.aulaudio.theme.White
import com.aistudio.aulaudio.ui.components.AulaIcons
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    recordings: List<AudioRecording>,
    subjects: List<Subject>,
    selectedSubjectFilter: String?,
    onSelectSubjectFilter: (String?) -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onRecordingClick: (AudioRecording) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onDeleteRecording: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showAccountDialog by remember { mutableStateOf(false) }

    val currentDateStr = remember {
        val sdf = SimpleDateFormat("EEEE, d 'de' MMMM", Locale.getDefault())
        val raw = sdf.format(Date())
        raw.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    val filteredRecordings = remember(recordings, selectedSubjectFilter, searchQuery) {
        recordings.filter { rec ->
            val matchesSubject = selectedSubjectFilter == null || rec.subjectId == selectedSubjectFilter
            val matchesQuery = searchQuery.isBlank() ||
                rec.title.contains(searchQuery, ignoreCase = true) ||
                rec.dateString.contains(searchQuery, ignoreCase = true) ||
                rec.transcriptSummary.contains(searchQuery, ignoreCase = true)
            matchesSubject && matchesQuery
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
    ) {
        // Header Editorial Minimalista
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceCanvas)
                .statusBarsPadding()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(CarbonBlack)
                    )
                    Text(
                        text = "AulaAudio",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CarbonBlack,
                        letterSpacing = (-0.02).sp
                    )
                }

                IconButton(
                    onClick = { showAccountDialog = true },
                    modifier = Modifier
                        .size(36.dp)
                        .testTag("account_button")
                ) {
                    Icon(
                        imageVector = AulaIcons.AccountCircle,
                        contentDescription = "Ajustes de cuenta",
                        tint = TextSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        HorizontalDivider(
            color = SurfaceBorderSubtle,
            thickness = 1.dp
        )

        // Main Scrollable Content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 120.dp)
        ) {
            // Title & Date Header
            item {
                Column(modifier = Modifier.padding(bottom = 20.dp)) {
                    Text(
                        text = currentDateStr.uppercase(Locale.getDefault()),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextTertiary,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Grabaciones",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary,
                        letterSpacing = (-0.03).sp
                    )
                }
            }

            // Minimalist Search Bar
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 0.dp, color = Color.Transparent)
                            .padding(bottom = 8.dp),
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
                            onValueChange = onSearchQueryChange,
                            singleLine = true,
                            textStyle = androidx.compose.ui.text.TextStyle(
                                fontSize = 14.sp,
                                color = TextPrimary,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("search_input"),
                            decorationBox = { innerTextField ->
                                if (searchQuery.isEmpty()) {
                                    Text(
                                        text = "Buscar clase, tema o fecha...",
                                        fontSize = 14.sp,
                                        color = TextTertiary
                                    )
                                }
                                innerTextField()
                            }
                        )
                        if (searchQuery.isNotEmpty()) {
                            IconButton(
                                onClick = { onSearchQueryChange("") },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Borrar",
                                    tint = TextTertiary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .align(Alignment.BottomCenter)
                            .background(if (searchQuery.isNotEmpty()) CarbonBlack else SurfaceBorder)
                    )
                }
            }

            // Horizontal Subject Filter Chips
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubjectFilterTab(
                        name = "Todas",
                        isSelected = selectedSubjectFilter == null,
                        onClick = { onSelectSubjectFilter(null) }
                    )
                    subjects.forEach { subject ->
                        SubjectFilterTab(
                            name = subject.name,
                            isSelected = selectedSubjectFilter == subject.id,
                            onClick = { onSelectSubjectFilter(subject.id) }
                        )
                    }
                }
            }

            // Sessions List
            if (filteredRecordings.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No se encontraron grabaciones",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextSecondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Inicia una nueva sesión pulsando el micrófono",
                            fontSize = 13.sp,
                            color = TextTertiary
                        )
                    }
                }
            } else {
                items(
                    items = filteredRecordings,
                    key = { it.id }
                ) { recording ->
                    val subject = subjects.find { it.id == recording.subjectId }
                    RecordingRowItem(
                        recording = recording,
                        subjectName = subject?.name ?: "Grabación Libre",
                        onClick = { onRecordingClick(recording) },
                        onToggleFavorite = { onToggleFavorite(recording.id) },
                        onDelete = { onDeleteRecording(recording.id) }
                    )
                }
            }
        }
    }

    if (showAccountDialog) {
        AlertDialog(
            onDismissRequest = { showAccountDialog = false },
            title = {
                Text(
                    text = "AulaAudio",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Espacio de estudio y archivo acústico de clases.",
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                    Text(
                        text = "Total de materias: ${subjects.size}\nTotal de sesiones: ${recordings.size}",
                        fontSize = 12.sp,
                        color = TextTertiary
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showAccountDialog = false }) {
                    Text("Cerrar", color = CarbonBlack)
                }
            }
        )
    }
}

@Composable
private fun SubjectFilterTab(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(bottom = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isSelected) CarbonBlack else TextSecondary,
            letterSpacing = (-0.01).sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .width(if (isSelected) 24.dp else 0.dp)
                .height(2.dp)
                .background(if (isSelected) CarbonBlack else Color.Transparent)
        )
    }
}

@Composable
private fun RecordingRowItem(
    recording: AudioRecording,
    subjectName: String,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit,
    onDelete: () -> Unit
) {
    var showDeleteConfirm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f).padding(end = 12.dp)) {
                // Subject and Date line
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = subjectName,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                    Text(
                        text = "·",
                        fontSize = 12.sp,
                        color = TextTertiary
                    )
                    Text(
                        text = recording.dateString,
                        fontSize = 12.sp,
                        color = TextTertiary
                    )
                }

                Spacer(modifier = Modifier.height(3.dp))

                // Title
                Text(
                    text = recording.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Duration & Status tag
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = recording.formattedDuration(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = TextTertiary
                    )
                    Text(
                        text = "·",
                        fontSize = 12.sp,
                        color = TextTertiary
                    )
                    Text(
                        text = recording.transcriptSummary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = TextTertiary
                    )
                    if (recording.markers.isNotEmpty()) {
                        Text(
                            text = "· ${recording.markers.size} marcadores",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                            color = TextTertiary
                        )
                    }
                }
            }

            // Right Actions: Favorite & Play button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = if (recording.isFavorite) AulaIcons.Bookmark else AulaIcons.BookmarkBorder,
                        contentDescription = "Favorito",
                        tint = if (recording.isFavorite) CarbonBlack else TextTertiary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Play Button matching code.html (40dp circle)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.dp, SurfaceBorder, CircleShape)
                        .background(White)
                        .clickable(onClick = onClick)
                        .testTag("play_recording_${recording.id}"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Reproducir",
                        tint = CarbonBlack,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))
        HorizontalDivider(
            color = SurfaceBorderSubtle,
            thickness = 1.dp
        )
    }

    if (showDeleteConfirm) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirm = false },
            title = { Text("Eliminar grabación") },
            text = { Text("¿Deseas eliminar '${recording.title}'?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirm = false
                        onDelete()
                    }
                ) {
                    Text("Eliminar", color = LiveRed)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirm = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }
}
