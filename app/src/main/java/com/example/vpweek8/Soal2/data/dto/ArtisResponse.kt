package com.example.vpweek8.Soal2.data.dto
import com.google.gson.annotations.SerializedName
data class ArtistResponse(

    @SerializedName("artists")
    val artists: List<Artist>?
)
