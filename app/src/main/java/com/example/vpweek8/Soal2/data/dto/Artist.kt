package com.example.vpweek8.Soal2.data.dto
import com.google.gson.annotations.SerializedName
data class Artist(
    @SerializedName("idArtist")
    val idArtist: String?,

    @SerializedName("strArtist")
    val strArtist: String?,

    @SerializedName("strGenre")
    val strGenre: String?,

    @SerializedName("strBiographyEN")
    val strBiographyEN: String?,

    @SerializedName("strArtistThumb")
    val strArtistThumb: String?,

    @SerializedName("strArtistBanner")
    val strArtistBanner: String?
)
