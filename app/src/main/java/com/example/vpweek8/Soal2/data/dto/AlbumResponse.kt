
package com.example.vpweek8.Soal2.data.dto
import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("album")
    val album: List<Album>?
)