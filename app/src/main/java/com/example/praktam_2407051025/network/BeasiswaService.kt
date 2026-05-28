package com.example.praktam_2407051025.network

import com.example.praktam_2407051025.model.Beasiswa
import retrofit2.http.GET

interface BeasiswaService {
    // Menggunakan URL Raw Gist yang stabil tanpa kode commit panjang
    @GET("rafiannurrr/3bda225b83b89d84bd61954b42743ad5/raw/gistfile1.txt")
    suspend fun getBeasiswa(): List<Beasiswa>
}
