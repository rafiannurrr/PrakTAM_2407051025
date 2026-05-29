package com.example.praktam_2407051025.data.repository

import com.example.praktam_2407051025.data.api.RetrofitClient
import com.example.praktam_2407051025.data.model.Beasiswa

class BeasiswaRepository {
    suspend fun getBeasiswa(): List<Beasiswa> {
        return try {
            RetrofitClient.instance.getBeasiswa()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
