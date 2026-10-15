package com.aistudio.aulaudio.ui.screens

import android.content.Intent
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.aulaudio.data.model.AudioRecording
import com.aistudio.aulaudio.data.model.Subject
import com.aistudio.aulaudio.theme.AccentIndigo
import com.aistudio.aulaudio.theme.CarbonBlack
import com.aistudio.aulaudio.theme.SurfaceBorderSubtle
import com.aistudio.aulaudio.theme.SurfaceContainer
import com.aistudio.aulaudio.theme.TextPrimary
import com.aistudio.aulaudio.theme.TextSecondary
import com.aistudio.aulaudio.theme.TextTertiary
import com.aistudio.aulaudio.theme.White
import com.aistudio.aulaudio.ui.components.AulaIcons
import com.aistudio.aulaudio.ui.components.ZenWaveformPlayer

@Composable
fun PlayerScreen(
    recording: AudioRecording,
    subject: Subject?,
    isPlaying: Boolean,
    currentPositionSeconds: Int,
    totalDurationSeconds: Int,
    playbackSpeed: Float,
    onTogglePlayPause: () -> Unit,
    onSeekTo: (Int) -> Unit,
    onRewind15: () -> Unit,
    onForward15: () -> Unit,
    onCycleSpeed: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isVoiceFilterActive by remember { mutableStateOf(recording.isCleanAudio) }

    val formattedCurrentTime = remember(currentPositionSeconds) {
        val m = currentPositionSeconds / 60
        val s = currentPositionSeconds % 60
        String.format("%02d:%02d", m, s)
    }

    val formattedTotalTime = remember(totalDurationSeconds) {
        val h = totalDurationSeconds / 3600
        val m = (totalDurationSeconds % 3600) / 60
        val s = totalDurationSeconds % 60
        if (h > 0) String.format("%d:%02d:%02d", h, m, s) else String.format("%02d:%02d", m, s)
    }

    val progressFraction = remember(currentPositionSeconds, totalDurationSeconds) {
        if (totalDurationSeconds > 0) {
            (currentPositionSeconds.toFloat() / totalDurationSeconds.toFloat()).coerceIn(0f, 1f)
        } else 0f
    }

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
                    onClick = onBack,
                    modifier = Modifier.size(40.dp).testTag("player_back_button")
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
                        text = subject?.name ?: "AulaAudio",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary,
                        letterSpacing = (-0.01).sp
                    )
                    Text(
                        text = recording.dateString,
                        fontSize = 11.sp,
                        color = TextTertiary
                    )
                }

                IconButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, recording.title)
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Grabación de clase: ${recording.title} (${subject?.name ?: ""}) - Duración: ${recording.formattedDuration()}"
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Compartir clase"))
                    },
                    modifier = Modifier.size(40.dp).testTag("player_share_menu_button")
                ) {
                    Icon(
                        imageVector = AulaIcons.MoreHoriz,
                        contentDescription = "Opciones",
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Main Player Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))

                // Subtitle badge
                Text(
                    text = "GRABACIÓN DE CLASE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextTertiary,
                    letterSpacing = 1.8.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Title
                Text(
                    text = recording.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    letterSpacing = (-0.02).sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Audio procesado con IA • ${recording.formattedDuration()}",
                    fontSize = 13.sp,
                    color = TextTertiary
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Dual time display (Current / Total)
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = formattedCurrentTime,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary,
                        letterSpacing = (-0.03).sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "/ $formattedTotalTime",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextTertiary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Interactive Waveform Track
                ZenWaveformPlayer(
                    currentProgress = progressFraction,
                    onSeek = { fraction ->
                        val targetSeconds = (fraction * totalDurationSeconds).toInt()
                        onSeekTo(targetSeconds)
                    }
                )

                // Time ends
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "00:00", fontSize = 11.sp, color = TextTertiary)
                    Text(text = formattedTotalTime, fontSize = 11.sp, color = TextTertiary)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Playback Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Speed button
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable(onClick = onCycleSpeed)
                            .testTag("speed_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${playbackSpeed}x",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = CarbonBlack
                        )
                    }

                    // Rewind 15s
                    IconButton(
                        onClick = onRewind15,
                        modifier = Modifier.size(44.dp).testTag("rewind_15_button")
                    ) {
                        Icon(
                            imageVector = AulaIcons.Replay15,
                            contentDescription = "Retroceder 15s",
                            tint = CarbonBlack,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Main Play/Pause Button
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .shadow(6.dp, CircleShape)
                            .clip(CircleShape)
                            .background(CarbonBlack)
                            .clickable(onClick = onTogglePlayPause)
                            .testTag("player_play_pause_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isPlaying) AulaIcons.Pause else Icons.Filled.PlayArrow,
                            contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                            tint = White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    // Forward 15s
                    IconButton(
                        onClick = onForward15,
                        modifier = Modifier.size(44.dp).testTag("forward_15_button")
                    ) {
                        Icon(
                            imageVector = AulaIcons.Forward15,
                            contentDescription = "Adelantar 15s",
                            tint = CarbonBlack,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Voice filter toggle
                    IconButton(
                        onClick = { isVoiceFilterActive = !isVoiceFilterActive },
                        modifier = Modifier.size(40.dp).testTag("voice_filter_button")
                    ) {
                        Icon(
                            imageVector = AulaIcons.GraphicEq,
                            contentDescription = "Filtro de voz",
                            tint = if (isVoiceFilterActive) AccentIndigo else TextTertiary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                HorizontalDivider(color = SurfaceBorderSubtle, thickness = 1.dp)
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Key Markers Section
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Marcadores",
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
                                text = "${recording.markers.size}",
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

            if (recording.markers.isEmpty()) {
                item {
                    Text(
                        text = "No se crearon marcadores en esta sesión",
                        fontSize = 12.sp,
                        color = TextTertiary,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            } else {
                items(recording.markers, key = { it.id }) { marker ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onSeekTo(marker.timestampSeconds) }
                            .padding(vertical = 10.dp, horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.weight(1f)
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
                                fontWeight = FontWeight.Medium,
                                color = TextPrimary,
                                maxLines = 1
                            )
                        }

                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Saltar",
                            tint = TextTertiary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    HorizontalDivider(color = SurfaceBorderSubtle, thickness = 0.5.dp)
                }
            }

            // Export / Share Audio action button
            item {
                Spacer(modifier = Modifier.height(28.dp))
                Button(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, recording.title)
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Grabación de clase: ${recording.title} (${subject?.name ?: ""}) - ${recording.formattedDuration()}"
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Exportar clase"))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("export_audio_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SurfaceContainer,
                        contentColor = TextPrimary
                    )
                ) {
                    Icon(
                        imageVector = AulaIcons.Share,
                        contentDescription = "Exportar",
                        tint = TextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Exportar audio y notas",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}
