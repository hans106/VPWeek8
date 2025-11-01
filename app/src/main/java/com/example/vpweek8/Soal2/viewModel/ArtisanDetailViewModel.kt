// PASTE INI KE: ArtistDetailViewModel.kt

package com.example.vpweek8.Soal2.ui.viewmodel // Sesuaikan package kamu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vpweek8.Soal2.data.dto.Album
import com.example.vpweek8.Soal2.data.dto.Artist
import com.example.vpweek8.Soal2.data.repository.ArtistRepository
import com.example.vpweek8.Soal2.data.repository.NetworkArtistRepository
import com.example.vpweek8.Soal2.data.service.ApiService

// IMPORT INI PENTING:
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// --- State untuk UI ---
data class ArtistUiState(
    val artist: Artist? = null,
    val albums: List<Album> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

class ArtistDetailViewModel : ViewModel() {

    // --- CARA GAMPANG (Buat Repository Langsung di Sini) ---
    private val BASE_URL = "https://www.theaudiodb.com/api/v1/json/123/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    private val apiService = retrofit.create(ApiService::class.java)
    private val artistRepository: ArtistRepository = NetworkArtistRepository(apiService)
    // --------------------------------------------------------

    // --- GANTI KE STATEFLOW (INI YANG BENAR) ---
    private val _uiState = MutableStateFlow(ArtistUiState())
    val uiState: StateFlow<ArtistUiState> = _uiState.asStateFlow()
    // ---------------------------------------------------------

    fun fetchAllData(artistName: String) {

        _uiState.update { it.copy(isLoading = true, isError = false) }

        viewModelScope.launch {
            try {
                val artistData = artistRepository.getArtist(artistName)
                val albumsData = artistRepository.getAlbums(artistName)

                _uiState.update {
                    it.copy(
                        artist = artistData,
                        albums = albumsData,
                        isLoading = false
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, isError = true)
                }
            }
        }
    }
}
