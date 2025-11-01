package com.example.vpweek8.Soal2.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vpweek8.Soal2.data.dto.Artist

@Composable
fun ArtistHeader(
    artist: Artist,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        AsyncImage(
            model = artist.strArtistBanner ?: "https://via.placeholder.com/600x180",
            contentDescription = "Banner ${artist.strArtist}",
            modifier = Modifier.fillMaxWidth().height(250.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = artist.strArtist ?: "Nama Artis",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                fontSize = 32.sp,
            )
            Text(
                text = artist.strGenre ?: "Genre",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
@Preview(
    showSystemUi = true
)
fun headerPriview(){
    ArtistHeader(
        artist = Artist(
            idArtist = "1",
            strArtist = "John Mayer",
            strGenre = "Indie",
            strBiographyEN = "",
            strArtistThumb = "",
            strArtistBanner = ""
        )
    )
}