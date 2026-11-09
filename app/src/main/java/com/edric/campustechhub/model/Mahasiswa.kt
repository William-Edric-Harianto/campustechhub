package com.edric.campustechhub.model

import android.graphics.Bitmap

data class Mahasiswa(
    val avatar: Bitmap? = null,
    val nama: String,
    val jurusan: Jurusan,
    val angkatan: Int,
    val nim: String,
    val mahasiswaAktif: Boolean
)
