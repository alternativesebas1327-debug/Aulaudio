package com.aistudio.aulaudio.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.aistudio.aulaudio.audio.AudioPlayerManager
import com.aistudio.aulaudio.audio.AudioRecorderManager
import com.aistudio.aulaudio.data.model.AudioRecording
import com.aistudio.aulaudio.data.model.Subject
import com.aistudio.aulaudio.data.repository.AudioRepository
import com.aistudio.aulaudio.ui.components.AppTab
import com.aistudio.aulaudio.ui.components.BottomNavBar
import com.aistudio.aulaudio.ui.screens.HomeScreen
import com.aistudio.aulaudio.ui.screens.PlayerScreen
import com.aistudio.aulaudio.ui.screens.RecordingScreen
import com.aistudio.aulaudio.ui.screens.SettingsScreen
import com.aistudio.aulaudio.ui.screens.SubjectSelectionModal
import com.aistudio.aulaudio.ui.screens.SubjectsManagementScreen
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AulaAudioApp(
    repository: AudioRepository,
    recorderManager: AudioRecorderManager,
    playerManager: AudioPlayerManager,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val subjects by repository.subjects.collectAsState()
    val recordings by repository.recordings.collectAsState()

    var selectedTab by remember { mutableStateOf(AppTab.AUDIO) }
    var selectedSubjectFilter by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    // Navigation and modal states
    var isSubjectSheetOpen by remember { mutableStateOf(false) }
    var modalSelectedSubjectId by remember { mutableStateOf("calculo") }

    var isRecordingActive by remember { mutableStateOf(false) }
    var activeRecordingSubject by remember { mutableStateOf<Subject?>(null) }

    var selectedPlaybackRecording by remember { mutableStateOf<AudioRecording?>(null) }

    // Recorder states
    val recorderState by recorderManager.state.collectAsState()
    val elapsedSeconds by recorderManager.elapsedSeconds.collectAsState()
    val amplitude by recorderManager.amplitude.collectAsState()

    // Player states
    val isPlaying by playerManager.isPlaying.collectAsState()
    val currentPositionSeconds by playerManager.currentPositionSeconds.collectAsState()
    val totalDurationSeconds by playerManager.totalDurationSeconds.collectAsState()
    val playbackSpeed by playerManager.speed.collectAsState()

    // BackHandler coordination
    BackHandler(enabled = isRecordingActive || selectedPlaybackRecording != null || selectedTab != AppTab.AUDIO || selectedSubjectFilter != null) {
        when {
            selectedPlaybackRecording != null -> {
                playerManager.pause()
                selectedPlaybackRecording = null
            }
            isRecordingActive -> {
                // Keep recording screen up so discard prompt can trigger
            }
            selectedSubjectFilter != null -> {
                selectedSubjectFilter = null
            }
            selectedTab != AppTab.AUDIO -> {
                selectedTab = AppTab.AUDIO
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        when {
            // Live Recording Screen has highest presentation priority
            isRecordingActive && activeRecordingSubject != null -> {
                RecordingScreen(
                    subject = activeRecordingSubject!!,
                    recorderState = recorderState,
                    elapsedSeconds = elapsedSeconds,
                    amplitude = amplitude,
                    onPause = { recorderManager.pauseRecording() },
                    onResume = { recorderManager.resumeRecording() },
                    onStopAndSave = { title, markers ->
                        val savedFile = recorderManager.stopRecording()
                        val newRecording = AudioRecording(
                            id = "rec_${System.currentTimeMillis()}",
                            subjectId = activeRecordingSubject!!.id,
                            title = title,
                            dateString = SimpleDateFormat("Hoy, HH:mm", Locale("es", "ES")).format(Date()),
                            durationSeconds = elapsedSeconds.coerceAtLeast(1),
                            filePath = savedFile?.absolutePath,
                            isCleanAudio = true,
                            transcriptSummary = "Transcrito",
                            markers = markers,
                            isFavorite = false
                        )
                        repository.addRecording(newRecording)
                        isRecordingActive = false
                        activeRecordingSubject = null

                        // Load into player right away
                        selectedPlaybackRecording = newRecording
                        playerManager.loadRecording(newRecording.filePath, newRecording.durationSeconds, autoPlay = true)
                    },
                    onDiscard = {
                        recorderManager.cancelRecording()
                        isRecordingActive = false
                        activeRecordingSubject = null
                    }
                )
            }

            // Player Screen
            selectedPlaybackRecording != null -> {
                val currentRec = selectedPlaybackRecording!!
                val subject = repository.getSubject(currentRec.subjectId)
                PlayerScreen(
                    recording = currentRec,
                    subject = subject,
                    isPlaying = isPlaying,
                    currentPositionSeconds = currentPositionSeconds,
                    totalDurationSeconds = totalDurationSeconds,
                    playbackSpeed = playbackSpeed,
                    onTogglePlayPause = { playerManager.togglePlayPause() },
                    onSeekTo = { seconds -> playerManager.seekTo(seconds) },
                    onRewind15 = { playerManager.rewind15() },
                    onForward15 = { playerManager.forward15() },
                    onCycleSpeed = { playerManager.cycleSpeed() },
                    onBack = {
                        playerManager.pause()
                        selectedPlaybackRecording = null
                    }
                )
            }

            // Main App Shell with Bottom Navigation
            else -> {
                when (selectedTab) {
                    AppTab.AUDIO -> {
                        HomeScreen(
                            recordings = recordings,
                            subjects = subjects,
                            selectedSubjectFilter = selectedSubjectFilter,
                            onSelectSubjectFilter = { selectedSubjectFilter = it },
                            searchQuery = searchQuery,
                            onSearchQueryChange = { searchQuery = it },
                            onRecordingClick = { rec ->
                                selectedPlaybackRecording = rec
                                playerManager.loadRecording(rec.filePath, rec.durationSeconds, autoPlay = true)
                            },
                            onToggleFavorite = { id -> repository.toggleFavorite(id) },
                            onDeleteRecording = { id -> repository.deleteRecording(id) }
                        )
                    }

                    AppTab.MATERIAS -> {
                        SubjectsManagementScreen(
                            subjects = subjects,
                            recordings = recordings,
                            onSelectSubject = { subj ->
                                selectedSubjectFilter = subj.id
                                selectedTab = AppTab.AUDIO
                            },
                            onRecordSubject = { subj ->
                                activeRecordingSubject = subj
                                val outFile = File(context.cacheDir, "rec_${System.currentTimeMillis()}.m4a")
                                recorderManager.startRecording(outFile)
                                isRecordingActive = true
                            },
                            onAddSubject = { newSubj -> repository.addSubject(newSubj) }
                        )
                    }

                    AppTab.GUARDADOS -> {
                        val favoriteRecordings = recordings.filter { it.isFavorite }
                        HomeScreen(
                            recordings = favoriteRecordings,
                            subjects = subjects,
                            selectedSubjectFilter = selectedSubjectFilter,
                            onSelectSubjectFilter = { selectedSubjectFilter = it },
                            searchQuery = searchQuery,
                            onSearchQueryChange = { searchQuery = it },
                            onRecordingClick = { rec ->
                                selectedPlaybackRecording = rec
                                playerManager.loadRecording(rec.filePath, rec.durationSeconds, autoPlay = true)
                            },
                            onToggleFavorite = { id -> repository.toggleFavorite(id) },
                            onDeleteRecording = { id -> repository.deleteRecording(id) }
                        )
                    }

                    AppTab.AJUSTES -> {
                        SettingsScreen(
                            totalRecordingsCount = recordings.size
                        )
                    }
                }

                // Floating Bottom Navigation Bar
                BottomNavBar(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it },
                    onRecordClick = {
                        isSubjectSheetOpen = true
                    },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }

        // Subject Selection Bottom Sheet
        if (isSubjectSheetOpen) {
            SubjectSelectionModal(
                subjects = subjects,
                selectedSubjectId = modalSelectedSubjectId,
                onSelectSubject = { modalSelectedSubjectId = it },
                onStartRecording = { subject ->
                    isSubjectSheetOpen = false
                    activeRecordingSubject = subject
                    val outFile = File(context.cacheDir, "rec_${System.currentTimeMillis()}.m4a")
                    recorderManager.startRecording(outFile)
                    isRecordingActive = true
                },
                onAddNewSubject = { newSubj ->
                    repository.addSubject(newSubj)
                },
                onDismiss = {
                    isSubjectSheetOpen = false
                }
            )
        }
    }
}
