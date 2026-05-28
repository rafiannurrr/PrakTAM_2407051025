package com.example.praktam_2407051025.model

import androidx.annotation.DrawableRes

data class Beasiswa(
    val nama: String,
    val deskripsi: String,
    val deadline: String,
    @DrawableRes val imageRes: Int
)
