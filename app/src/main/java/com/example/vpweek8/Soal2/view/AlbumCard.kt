package com.example.vpweek8.Soal2.ui.component // Sesuaikan package kamu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vpweek8.Soal2.data.dto.Album

@Composable
fun AlbumCard(
    album: Album,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedCard(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            // Gambar Cover Album
            AsyncImage(
                model = album.strAlbumThumb,
                contentDescription = "Cover album ${album.strAlbum}",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f), // Bikin gambar jadi kotak (1:1)
                contentScale = ContentScale.Crop
            )

            // Nama Album
            Text(
                text = album.strAlbum ?: "Nama Album Tidak Diketahui",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Light
                ),
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
            )
            Row {
                Text(
                    text = album.intYearReleased ?: "Tahun tidak diketahui",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )
                Text(
                    text = album.strGenre ?: "Genre tidak diketahui",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AlbumCardPreview() {
    AlbumCard(
        album = Album(
            idAlbum = "2115166",
            strAlbum = "Sob Rock",
            strAlbumThumb = "",
            intYearReleased = "2021",
            strGenre = "Indie",
            strDescriptionEN = ""
        ),
        onClick = {}
    )
}
