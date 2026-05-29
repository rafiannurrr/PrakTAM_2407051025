package com.example.praktam_2407051025.data.api

import com.example.praktam_2407051025.data.model.Beasiswa
import retrofit2.http.GET

interface BeasiswaService {
    @GET("https://gist.githubusercontent.com/rafiannurrr/3bda225b83b89d84bd61954b42743ad5/raw/c8ea5f28b159351cbf9bc59c060c40ceb10b18a4/gistfile1.txt")
    suspend fun getBeasiswa(): List<Beasiswa>
}
