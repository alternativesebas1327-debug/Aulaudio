package com.aistudio.aulaudio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.aulaudio.audio.RecorderState
import com.aistudio.aulaudio.data.model.RecordingMarker
import com.aistudio.aulaudio.data.model.Subject
import com.aistudio.aulaudio.theme.CarbonBlack
import com.aistudio.aulaudio.theme.LiveRed
import com.aistudio.aulaudio.theme.SurfaceBorderSubtle
import com.aistudio.aulaudio.theme.SurfaceContainer
import com.aistudio.aulaudio.theme.TextPrimary
import com.aistudio.aulaudio.theme.TextSecondary
import com.aistudio.aulaudio.theme.TextTertiary
import com.aistudio.aulaudio.theme.White
import com.aistudio.aulaudio.ui.components.AulaIcons
import com.aistudio.aulaudio.ui.components.ZenWaveformRecording
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RecordingScreen(
    subject: Subject,
    recorderState: RecorderState,
    elapsedSeconds: Int,
    amplitude: Float,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onStopAndSave: (title: String, markers: List<RecordingMarker>) -> Unit,
    onDiscard: () -> Unit,
    modifier: Modifier = Modifier
) {
    var sessionTitle by remember { mutableStateOf("Sesión ${subject.name}") }
    val markers = remember { mutableStateListOf<RecordingMarker>() }

    var showDiscardConfirm by remember { mutableStateOf(false) }
    var showAddMarkerDialog by remember { mutableStateOf(false) }
    var markerNoteInput by remember { mutableStateOf("") }

    val formattedDate = remember {
        val sdf = SimpleDateFormat("d MMM", Locale.getDefault())
        "Clase del " + sdf.format(Date())
    }

    val minutes = elapsedSeconds / 60
    val seconds = elapsedSeconds % 60
    val formattedTimer = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        // Zen Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { showDiscardConfirm = true },
                    modifier = Modifier.size(40.dp).testTag("recording_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = TextSecondary,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = subject.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary,
                        letterSpacing = (-0.01).sp
                    )
                    Text(
                        text = formattedDate,
                        fontSize = 11.sp,
                        color = TextTertiary
                    )
                }

                IconButton(
                    onClick = { showDiscardConfirm = true },
                    modifier = Modifier.size(40.dp).testTag("recording_discard_button")
                ) {
                    Icon(
                        imageVector = AulaIcons.DeleteOutline,
                        contentDescription = "Descartar",
                        tint = TextTertiary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Center Content
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                // Section badge
                Text(
                    text = "GRABACIÓN EN CURSO",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (recorderState == RecorderState.RECORDING) LiveRed else TextTertiary,
                    letterSpacing = 1.8.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Editable session title
                Text(
                    text = sessionTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    letterSpacing = (-0.02).sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (recorderState == RecorderState.RECORDING) "Grabando audio..." else "Grabación pausada",
                    fontSize = 13.sp,
                    color = TextTertiary
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Sculpted Chronometer
                Text(
                    text = formattedTimer,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    letterSpacing = (-0.03).sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Animated Zen Waveform
                ZenWaveformRecording(
                    amplitude = amplitude,
                    isPaused = recorderState == RecorderState.PAUSED
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Controls Row: Add Bookmark, Pause/Resume, Stop & Save
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Bookmark Button
                    IconButton(
                        onClick = {
                            markerNoteInput = "Punto clave a los $formattedTimer"
                            showAddMarkerDialog = true
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainer)
                            .testTag("add_marker_button")
                    ) {
                        Icon(
                            imageVector = AulaIcons.BookmarkBorder,
                            contentDescription = "Añadir marcador",
                            tint = CarbonBlack,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    // Main Pause/Resume Button
                    Box(
                        modifier = Modifier
                            .size(68.dp)
                            .shadow(8.dp, CircleShape)
                            .clip(CircleShape)
                            .background(CarbonBlack)
                            .clickable {
                                if (recorderState == RecorderState.RECORDING) {
                                    onPause()
                                } else {
                                    onResume()
                                }
                            }
                            .testTag("pause_resume_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (recorderState == RecorderState.RECORDING) AulaIcons.Pause else Icons.Filled.PlayArrow,
                            contentDescription = if (recorderState == RecorderState.RECORDING) "Pausar" else "Reanudar",
                            tint = White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    // Stop & Save Button
                    IconButton(
                        onClick = {
                            onStopAndSave(sessionTitle, markers.toList())
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainer)
                            .testTag("stop_save_button")
                    ) {
                        Icon(
                            imageVector = AulaIcons.Stop,
                            contentDescription = "Guardar grabación",
                            tint = LiveRed,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(36.dp))
            }

            // Live Markers Section
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Marcadores fijados",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(SurfaceContainer)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "${markers.size}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextSecondary
                            )
                        }
                    }
                    Text(
                        text = "Puntos clave",
                        fontSize = 11.sp,
                        color = TextTertiary
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            if (markers.isEmpty()) {
                item {
                    Text(
                        text = "Pulsa el marcador para guardar instantes importantes",
                        fontSize = 12.sp,
                        color = TextTertiary,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            } else {
                items(markers, key = { it.id }) { marker ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(SurfaceContainer)
                                    .padding(horizontal = 6.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = marker.formattedTime(),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = TextSecondary
                                )
                            }
                            Text(
                                text = marker.note,
                                fontSize = 13.sp,
                                color = TextPrimary,
                                maxLines = 1
                            )
                        }
                    }
                    HorizontalDivider(color = SurfaceBorderSubtle, thickness = 0.5.dp)
                }
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }

    if (showDiscardConfirm) {
        AlertDialog(
            onDismissRequest = { showDiscardConfirm = false },
            title = { Text("¿Descartar grabación?") },
            text = { Text("Se cancelará la grabación actual de ${subject.name} y no se guardará el audio.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDiscardConfirm = false
                        onDiscard()
                    }
                ) {
                    Text("Descartar", color = LiveRed, fontWeight = FontWeight.SemiBold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDiscardConfirm = false }) {
                    Text("Continuar grabando", color = CarbonBlack)
                }
            }
        )
    }

    if (showAddMarkerDialog) {
        AlertDialog(
            onDismissRequest = { showAddMarkerDialog = false },
            title = { Text("Añadir Marcador ($formattedTimer)") },
            text = {
                OutlinedTextField(
                    value = markerNoteInput,
                    onValueChange = { markerNoteInput = it },
                    label = { Text("Nota del punto clave") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (markerNoteInput.isNotBlank()) {
                            markers.add(
                                RecordingMarker(
                                    id = "m_${System.currentTimeMillis()}",
                                    timestampSeconds = elapsedSeconds,
                                    note = markerNoteInput.trim()
                                )
                            )
                        }
                        showAddMarkerDialog = false
                    }
                ) {
                    Text("Fijar", color = CarbonBlack, fontWeight = FontWeight.SemiBold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddMarkerDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }
}
