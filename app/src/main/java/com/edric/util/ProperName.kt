package com.edric.util

val Enum<*>.properName: String
    get() = name
        .split("_")
        .joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { it.uppercase() }
        }
