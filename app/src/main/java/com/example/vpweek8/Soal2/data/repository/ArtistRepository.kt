// PASTE KODE INI KE: ArtistRepository.kt

package com.example.vpweek8.Soal2.data.repository // Sesuaikan package kamu

import com.example.vpweek8.Soal2.data.dto.Album
import com.example.vpweek8.Soal2.data.dto.Artist
import com.example.vpweek8.Soal2.data.dto.Track // <-- BARU

interface ArtistRepository {

    suspend fun getArtist(artistName: String): Artist?


    suspend fun getAlbums(artistName: String): List<Album>

    suspend fun getTracksByAlbum(idAlbum: String): List<Track>
}