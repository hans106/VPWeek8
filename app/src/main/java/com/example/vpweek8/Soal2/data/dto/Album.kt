
package com.example.vpweek8.Soal2.data.dto

import com.google.gson.annotations.SerializedName

data class Album(

    @SerializedName("idAlbum")
    val idAlbum: String?,

    @SerializedName("strAlbum")
    val strAlbum: String?,

    @SerializedName("strAlbumThumb")
    val strAlbumThumb: String?,

    @SerializedName("intYearReleased")
    val intYearReleased: String?,

    @SerializedName("strGenre")
    val strGenre: String?,

    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String?
)