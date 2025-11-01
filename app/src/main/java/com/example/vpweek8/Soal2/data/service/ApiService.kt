// PASTE KODE INI KE: ApiService.kt

package com.example.vpweek8.Soal2.data.service

import com.example.vpweek8.Soal2.data.dto.ArtistResponse
import com.example.vpweek8.Soal2.data.dto.AlbumResponse
import com.example.vpweek8.Soal2.data.dto.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // 1. Ambil data Artist
    @GET("search.php")
    suspend fun getArtist(
        @Query("s") artistName: String
    ): ArtistResponse

    // 2. Ambil data Albums
    @GET("searchalbum.php")
    suspend fun getAlbums(
        @Query("s") artistName: String
    ): AlbumResponse

    // 3. Ambil data Tracks (BARU)
    @GET("track.php")
    suspend fun getTracksByAlbum(
        @Query("m") idAlbum: String
    ): TrackResponse
}