package com.example.artistexplorer.model


data class ArtistUiModel(
    val id: String,
    val name: String,
    val genre: String,
    val bannerUrl: String,
    val albums: List<AlbumUiModel>
)


data class AlbumUiModel(
    val id: String,
    val name: String,
    val year: String,
    val thumbnailUrl: String
)


data class AlbumDetailUiModel(
    val id: String,
    val name: String,
    val artist: String,
    val year: String,
    val genre: String,
    val description: String,
    val coverUrl: String,
    val tracks: List<TrackUiModel>
)

data class TrackUiModel(
    val id: String,
    val number: String,
    val name: String,
    val duration: String
)
