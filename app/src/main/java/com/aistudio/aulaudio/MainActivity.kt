package com.aistudio.aulaudio

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.aistudio.aulaudio.audio.AudioPlayerManager
import com.aistudio.aulaudio.audio.AudioRecorderManager
import com.aistudio.aulaudio.data.repository.AudioRepository
import com.aistudio.aulaudio.theme.AulaAudioTheme
import com.aistudio.aulaudio.theme.SurfaceCanvas
import com.aistudio.aulaudio.ui.AulaAudioApp

class MainActivity : ComponentActivity() {

    private lateinit var repository: AudioRepository
    private lateinit var recorderManager: AudioRecorderManager
    private lateinit var playerManager: AudioPlayerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        repository = AudioRepository(applicationContext)
        recorderManager = AudioRecorderManager(applicationContext)
        playerManager = AudioPlayerManager(applicationContext)

        setContent {
            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { /* Permission result handled gracefully */ }

            LaunchedEffect(Unit) {
                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.RECORD_AUDIO
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                }
            }

            AulaAudioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SurfaceCanvas
                ) {
                    AulaAudioApp(
                        repository = repository,
                        recorderManager = recorderManager,
                        playerManager = playerManager
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerManager.stop()
        recorderManager.cancelRecording()
    }
}
