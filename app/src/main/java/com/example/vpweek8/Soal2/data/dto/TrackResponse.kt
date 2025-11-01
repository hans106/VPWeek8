
package com.example.vpweek8.Soal2.data.dto

import com.google.gson.annotations.SerializedName

data class TrackResponse(

    @SerializedName("track")
    val track: List<Track>?
)