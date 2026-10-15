package com.aistudio.aulaudio.data.model

data class AudioRecording(
    val id: String,
    val subjectId: String,
    val title: String,
    val dateString: String,
    val durationSeconds: Int,
    val filePath: String? = null,
    val isCleanAudio: Boolean = true,
    val transcriptSummary: String = "Transcrito",
    val markers: List<RecordingMarker> = emptyList(),
    val isFavorite: Boolean = false
) {
    fun formattedDuration(): String {
        val h = durationSeconds / 3600
        val m = (durationSeconds % 3600) / 60
        val s = durationSeconds % 60
        return when {
            h > 0 -> "${h}h ${m}m"
            m > 0 -> "${m}m"
            else -> "${s}s"
        }
    }

    fun formattedDigitalDuration(): String {
        val h = durationSeconds / 3600
        val m = (durationSeconds % 3600) / 60
        val s = durationSeconds % 60
        return if (h > 0) {
            String.format("%d:%02d:%02d", h, m, s)
        } else {
            String.format("%02d:%02d", m, s)
        }
    }
}
