package com.example.vpweek8.Soal2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.vpweek8.Soal2.data.dto.Album
import com.example.vpweek8.Soal2.data.dto.Artist
import com.example.vpweek8.Soal2.ui.component.AlbumCard
import com.example.vpweek8.Soal2.ui.component.ArtistHeader
import com.example.vpweek8.Soal2.ui.viewmodel.ArtistDetailViewModel
import com.example.vpweek8.Soal2.ui.viewmodel.ArtistUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDetailScreen(
    viewModel: ArtistDetailViewModel = viewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAllData("John Mayer")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("John Mayer") }
            )
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.isError -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Gagal memuat data. Cek koneksi internet.")
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    item {
                        uiState.artist?.let { ArtistHeader(it) }
                    }

                    item {
                        Text(
                            text = "Albums",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    item {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier
                                .height(calculateGridHeight(uiState.albums.size))
                                .fillMaxWidth(),
                            userScrollEnabled = false
                        ) {
                            items(uiState.albums) { album ->
                                AlbumCard(
                                    album = album,
                                    onClick = {
                                        album.idAlbum?.let {
                                            navController.navigate("albumDetail/$it")
                                        }
                                    }
                                )
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }
            }
        }
    }
}

@Composable
private fun calculateGridHeight(itemCount: Int): Dp {
    val rows = (itemCount + 1) / 2
    val rowHeight = 220.dp
    return (rows * rowHeight)
}
