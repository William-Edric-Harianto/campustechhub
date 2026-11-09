package com.edric.campustechhub.data

import android.graphics.Bitmap
import com.edric.campustechhub.model.Jurusan
import com.edric.campustechhub.model.Mahasiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object MahasiswaRepository {
    val defaultMahasiswa = Mahasiswa(
        nama = "Aditya Pratama",
        jurusan = Jurusan.TEKNIK_INFORMATIKA,
        angkatan = 2022,
        nim = "22001045",
        mahasiswaAktif = true
    )

    private val _mahasiswas = MutableStateFlow<List<Mahasiswa>>(
        listOf(defaultMahasiswa)
    )
    val mahasiswas: StateFlow<List<Mahasiswa>> = _mahasiswas.asStateFlow()

    private const val defaultAngkatan = 2022

    fun getAvatar(nim: String): Bitmap? {
        val mahasiswa = mahasiswas.value.firstOrNull { it.nim == nim }
        return mahasiswa?.avatar
    }

    fun getNama(nim: String): String {
        val mahasiswa = mahasiswas.value.firstOrNull { it.nim == nim }
        return mahasiswa?.nama ?: defaultMahasiswa.nama
    }

    fun getJurusan(nim: String): Jurusan {
        val mahasiswa = mahasiswas.value.firstOrNull { it.nim == nim }
        return mahasiswa?.jurusan ?: defaultMahasiswa.jurusan
    }

    fun getAngkatan(nim: String): Int {
        val mahasiswa = mahasiswas.value.firstOrNull { it.nim == nim }
        return mahasiswa?.angkatan ?: defaultAngkatan
    }

    fun getStatus(nim: String): String {
        val mahasiswa = mahasiswas.value.firstOrNull { it.nim == nim }
        return if (mahasiswa?.mahasiswaAktif != false) "MAHASISWA AKTIF" else "MAHASISWA TIDAK AKTIF"
    }
}
