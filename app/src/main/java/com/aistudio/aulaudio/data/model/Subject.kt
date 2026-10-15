package com.aistudio.aulaudio.data.model

data class Subject(
    val id: String,
    val name: String,
    val classroom: String,
    val professor: String,
    val colorHex: String = "#111113"
)
