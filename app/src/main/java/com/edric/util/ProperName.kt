package com.edric.util

import com.edric.campustechhub.model.Jurusan
import com.edric.campustechhub.model.ProjectStatus
import com.edric.campustechhub.model.TechStack

val Enum<*>.properName: String
    get() = name
        .split("_")
        .joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { it.uppercase() }
        }

val TechStack.displayName: String
    get() = when (this) {
        TechStack.KOTLIN -> "Kotlin"
        TechStack.JETPACK_COMPOSE -> "Jetpack Compose"
        TechStack.ROOM -> "Room"
        TechStack.FIREBASE -> "Firebase"
        TechStack.KTOR -> "Ktor"
        TechStack.PYTHON -> "Python"
        TechStack.GOOGLE_MAPS_SDK -> "Google Maps SDK"
        TechStack.CAMERAX -> "CameraX"
    }

val ProjectStatus.displayName: String
    get() = when (this) {
        ProjectStatus.IN_PROGRESS -> "In Progress"
        ProjectStatus.COMPLETED -> "Completed"
    }

val Jurusan.displayName: String
    get() = when (this) {
        Jurusan.TEKNIK_INFORMATIKA -> "Teknik Informatika"
        Jurusan.TEKNIK_SIPIL -> "Teknik Sipil"
        Jurusan.TEKNIK_ELEKTRO -> "Teknik Elektro"
        Jurusan.MANAJEMEN -> "Manajemen"
        Jurusan.AKUTANSI -> "Akuntansi"
        Jurusan.PENDIDIKAN_DOKTER -> "Pendidikan Dokter"
    }
