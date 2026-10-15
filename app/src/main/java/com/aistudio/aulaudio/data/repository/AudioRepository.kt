package com.aistudio.aulaudio.data.repository

import android.content.Context
import com.aistudio.aulaudio.data.model.AudioRecording
import com.aistudio.aulaudio.data.model.RecordingMarker
import com.aistudio.aulaudio.data.model.Subject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class AudioRepository(private val context: Context) {

    private val subjectsFile = File(context.filesDir, "subjects.json")
    private val recordingsFile = File(context.filesDir, "recordings.json")

    private val _subjects = MutableStateFlow<List<Subject>>(loadInitialSubjects())
    val subjects: StateFlow<List<Subject>> = _subjects.asStateFlow()

    private val _recordings = MutableStateFlow<List<AudioRecording>>(loadInitialRecordings())
    val recordings: StateFlow<List<AudioRecording>> = _recordings.asStateFlow()

    init {
        loadFromDisk()
    }

    private fun loadInitialSubjects(): List<Subject> {
        return listOf(
            Subject(
                id = "calculo",
                name = "Cálculo Integral",
                classroom = "Aula 204",
                professor = "Prof. Méndez",
                colorHex = "#18181B"
            ),
            Subject(
                id = "fisica",
                name = "Física II",
                classroom = "Laboratorio B",
                professor = "Prof. García",
                colorHex = "#3B82F6"
            ),
            Subject(
                id = "historia",
                name = "Historia",
                classroom = "Aula Magna",
                professor = "Prof. Torres",
                colorHex = "#8B5CF6"
            ),
            Subject(
                id = "neuro",
                name = "Neurobiología",
                classroom = "Pabellón C",
                professor = "Dra. Soto",
                colorHex = "#10B981"
            ),
            Subject(
                id = "libre",
                name = "Grabación Libre",
                classroom = "Sin aula",
                professor = "General",
                colorHex = "#71717A"
            )
        )
    }

    private fun loadInitialRecordings(): List<AudioRecording> {
        return listOf(
            AudioRecording(
                id = "rec_1",
                subjectId = "calculo",
                title = "Sesión 14: Integración por Partes",
                dateString = "Hoy, 10:30",
                durationSeconds = 74 * 60 + 20, // 1h 14m 20s
                isCleanAudio = true,
                transcriptSummary = "Transcrito",
                markers = listOf(
                    RecordingMarker("m1", 12 * 60 + 10, "Teorema fundamental y sustitución"),
                    RecordingMarker("m2", 28 * 60 + 40, "Ejemplo práctico para examen"),
                    RecordingMarker("m3", 42 * 60 + 5, "Dudas sobre la tarea y entrega")
                ),
                isFavorite = true
            ),
            AudioRecording(
                id = "rec_2",
                subjectId = "fisica",
                title = "Principio de Incertidumbre y Dualidad",
                dateString = "Ayer, 16:00",
                durationSeconds = 52 * 60, // 52m
                isCleanAudio = true,
                transcriptSummary = "Audio limpio",
                markers = listOf(
                    RecordingMarker("m4", 8 * 60, "Postulado de De Broglie"),
                    RecordingMarker("m5", 34 * 60, "Función de onda y probabilidad")
                ),
                isFavorite = false
            ),
            AudioRecording(
                id = "rec_3",
                subjectId = "historia",
                title = "Revolución Industrial y Consecuencias",
                dateString = "12 Oct",
                durationSeconds = 105 * 60, // 1h 45m
                isCleanAudio = false,
                transcriptSummary = "Resumen disponible",
                markers = listOf(
                    RecordingMarker("m6", 15 * 60, "Máquina de vapor y minería"),
                    RecordingMarker("m7", 55 * 60, "Movimientos obreros y leyes fabriles")
                ),
                isFavorite = false
            ),
            AudioRecording(
                id = "rec_4",
                subjectId = "neuro",
                title = "Estructura de la Membrana Plasmática",
                dateString = "10 Oct",
                durationSeconds = 48 * 60, // 48m
                isCleanAudio = true,
                transcriptSummary = "Transcrito",
                markers = listOf(
                    RecordingMarker("m8", 10 * 60, "Bicapa lipídica y canales iónicos"),
                    RecordingMarker("m9", 30 * 60, "Potencial de acción y gradiente")
                ),
                isFavorite = true
            )
        )
    }

    private fun loadFromDisk() {
        try {
            if (subjectsFile.exists()) {
                val jsonStr = subjectsFile.readText()
                val jsonArray = JSONArray(jsonStr)
                val list = mutableListOf<Subject>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    list.add(
                        Subject(
                            id = obj.getString("id"),
                            name = obj.getString("name"),
                            classroom = obj.optString("classroom", ""),
                            professor = obj.optString("professor", ""),
                            colorHex = obj.optString("colorHex", "#18181B")
                        )
                    )
                }
                if (list.isNotEmpty()) {
                    _subjects.value = list
                }
            }
        } catch (_: Exception) {}

        try {
            if (recordingsFile.exists()) {
                val jsonStr = recordingsFile.readText()
                val jsonArray = JSONArray(jsonStr)
                val list = mutableListOf<AudioRecording>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val markersArray = obj.optJSONArray("markers")
                    val markers = mutableListOf<RecordingMarker>()
                    if (markersArray != null) {
                        for (m in 0 until markersArray.length()) {
                            val mObj = markersArray.getJSONObject(m)
                            markers.add(
                                RecordingMarker(
                                    id = mObj.getString("id"),
                                    timestampSeconds = mObj.getInt("timestampSeconds"),
                                    note = mObj.getString("note")
                                )
                            )
                        }
                    }
                    list.add(
                        AudioRecording(
                            id = obj.getString("id"),
                            subjectId = obj.getString("subjectId"),
                            title = obj.getString("title"),
                            dateString = obj.getString("dateString"),
                            durationSeconds = obj.optInt("durationSeconds", 0),
                            filePath = if (obj.has("filePath") && !obj.isNull("filePath")) obj.getString("filePath") else null,
                            isCleanAudio = obj.optBoolean("isCleanAudio", true),
                            transcriptSummary = obj.optString("transcriptSummary", "Transcrito"),
                            markers = markers,
                            isFavorite = obj.optBoolean("isFavorite", false)
                        )
                    )
                }
                if (list.isNotEmpty()) {
                    _recordings.value = list
                }
            }
        } catch (_: Exception) {}
    }

    private fun saveToDisk() {
        try {
            val sArray = JSONArray()
            for (s in _subjects.value) {
                val obj = JSONObject()
                obj.put("id", s.id)
                obj.put("name", s.name)
                obj.put("classroom", s.classroom)
                obj.put("professor", s.professor)
                obj.put("colorHex", s.colorHex)
                sArray.put(obj)
            }
            subjectsFile.writeText(sArray.toString())

            val rArray = JSONArray()
            for (r in _recordings.value) {
                val obj = JSONObject()
                obj.put("id", r.id)
                obj.put("subjectId", r.subjectId)
                obj.put("title", r.title)
                obj.put("dateString", r.dateString)
                obj.put("durationSeconds", r.durationSeconds)
                obj.put("filePath", r.filePath ?: "")
                obj.put("isCleanAudio", r.isCleanAudio)
                obj.put("transcriptSummary", r.transcriptSummary)
                obj.put("isFavorite", r.isFavorite)

                val mArray = JSONArray()
                for (m in r.markers) {
                    val mObj = JSONObject()
                    mObj.put("id", m.id)
                    mObj.put("timestampSeconds", m.timestampSeconds)
                    mObj.put("note", m.note)
                    mArray.put(mObj)
                }
                obj.put("markers", mArray)
                rArray.put(obj)
            }
            recordingsFile.writeText(rArray.toString())
        } catch (_: Exception) {}
    }

    fun addRecording(recording: AudioRecording) {
        val current = _recordings.value.toMutableList()
        current.add(0, recording)
        _recordings.value = current
        saveToDisk()
    }

    fun deleteRecording(id: String) {
        val recording = _recordings.value.find { it.id == id }
        if (recording?.filePath != null) {
            try {
                File(recording.filePath).delete()
            } catch (_: Exception) {}
        }
        _recordings.value = _recordings.value.filter { it.id != id }
        saveToDisk()
    }

    fun toggleFavorite(id: String) {
        _recordings.value = _recordings.value.map {
            if (it.id == id) it.copy(isFavorite = !it.isFavorite) else it
        }
        saveToDisk()
    }

    fun addSubject(subject: Subject) {
        if (_subjects.value.none { it.id == subject.id }) {
            _subjects.value = _subjects.value + subject
            saveToDisk()
        }
    }

    fun getSubject(id: String): Subject? {
        return _subjects.value.find { it.id == id }
    }
}
