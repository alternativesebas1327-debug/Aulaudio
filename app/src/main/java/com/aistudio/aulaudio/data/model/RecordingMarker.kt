package com.aistudio.aulaudio.data.model

data class RecordingMarker(
    val id: String,
    val timestampSeconds: Int,
    val note: String
) {
    fun formattedTime(): String {
        val m = timestampSeconds / 60
        val s = timestampSeconds % 60
        return String.format("%02d:%02d", m, s)
    }
}
