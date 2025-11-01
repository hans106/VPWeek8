// PASTE KODE INI KE: NetworkArtistRepository.kt

package com.example.vpweek8.Soal2.data.repository

import com.example.vpweek8.Soal2.data.dto.Album
import com.example.vpweek8.Soal2.data.dto.Artist
import com.example.vpweek8.Soal2.data.dto.Track
import com.example.vpweek8.Soal2.data.service.ApiService

class NetworkArtistRepository(
    private val apiService: ApiService
) : ArtistRepository {

    override suspend fun getArtist(artistName: String): Artist? {

        return try {
            apiService.getArtist(artistName).artists?.firstOrNull()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getAlbums(artistName: String): List<Album> {

        return try {
            apiService.getAlbums(artistName).album ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }


    override suspend fun getTracksByAlbum(idAlbum: String): List<Track> {
        return try {

            apiService.getTracksByAlbum(idAlbum).track ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}