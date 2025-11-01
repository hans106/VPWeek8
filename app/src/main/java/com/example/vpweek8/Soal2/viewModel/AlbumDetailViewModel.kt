package com.example.vpweek8.Soal2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artistexplorer.model.AlbumDetailUiModel
import com.example.artistexplorer.model.TrackUiModel
import com.example.vpweek8.Soal2.data.repository.ArtistRepository
import com.example.vpweek8.Soal2.data.repository.NetworkArtistRepository
import com.example.vpweek8.Soal2.data.service.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// State untuk UI Halaman Detail Album
data class AlbumDetailUiState(
    val albumDetail: AlbumDetailUiModel? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

class AlbumDetailViewModel : ViewModel() {

    // Repository setup
    private val BASE_URL = "https://www.theaudiodb.com/api/v1/json/123/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    private val apiService = retrofit.create(ApiService::class.java)
    private val repository: ArtistRepository = NetworkArtistRepository(apiService)

    // StateFlow
    private val _uiState = MutableStateFlow(AlbumDetailUiState())
    val uiState: StateFlow<AlbumDetailUiState> = _uiState.asStateFlow()

    // Fungsi untuk fetch data tracks dari album tertentu
    fun fetchTracks(idAlbum: String) {
        _uiState.update { it.copy(isLoading = true, isError = false) }

        viewModelScope.launch {
            try {
                // Ambil daftar lagu dari API
                val tracks = repository.getTracksByAlbum(idAlbum)

                // Karena endpoint album detail gak lengkap, kita isi data album dummy sementara
                // Biasanya info lengkap sudah didapat dari halaman sebelumnya
                val albumDetail = AlbumDetailUiModel(
                    id = idAlbum,
                    name = "Album Name", // Bisa kamu update kalau ingin fetch detail tambahan
                    artist = "John Mayer",
                    year = tracks.firstOrNull()?.intTrackNumber ?: "Unknown",
                    genre = "Unknown",
                    description = "",
                    coverUrl = "",
                    tracks = tracks.map { track ->
                        TrackUiModel(
                            id = track.idTrack ?: "",
                            number = track.intTrackNumber ?: "",
                            name = track.strTrack ?: "-",
                            duration = track.intDuration ?: ""
                        )
                    }
                )

                _uiState.update {
                    it.copy(
                        albumDetail = albumDetail,
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
