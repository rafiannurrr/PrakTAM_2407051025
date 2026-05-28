package com.example.praktam_2407051025.model

import com.google.gson.annotations.SerializedName

data class Beasiswa(
    @SerializedName("nama")
    val nama: String = "",
    @SerializedName("deskripsi")
    val deskripsi: String = "",
    @SerializedName("deadline")
    val deadline: String = "",
    @SerializedName(value = "image_url", alternate = ["imageUrl", "gambar"])
    val imageUrl: Any = ""
)
