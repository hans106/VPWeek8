// Di dalam folder: data/dto
package com.example.vpweek8.Soal2.data.dto // Pastikan package-nya sama

import com.google.gson.annotations.SerializedName

data class Track(

    @SerializedName("idTrack")
    val idTrack: String?,

    @SerializedName("strTrack")
    val strTrack: String?,

    @SerializedName("intTrackNumber")
    val intTrackNumber: String?,

    @SerializedName("intDuration")
    val intDuration: String?
)