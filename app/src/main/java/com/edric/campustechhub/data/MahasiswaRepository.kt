package com.edric.campustechhub.data

import com.edric.campustechhub.model.Jurusan
import com.edric.campustechhub.model.Mahasiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object MahasiswaRepository {
    private val _mahasiswas = MutableStateFlow<List<Mahasiswa>>(
        listOf(
            Mahasiswa(
                avatar = TODO(),
                nama = "Aditya Pratama",
                jurusan = Jurusan.TEKNIK_INFORMATIKA,
                angkatan = 2022,
                nim = 2200145
            )
        )
    )
    val mahasiswas: StateFlow<List<Mahasiswa>> = _mahasiswas.asStateFlow()

    //gaperlu CRUD pusing wkwkwk

}