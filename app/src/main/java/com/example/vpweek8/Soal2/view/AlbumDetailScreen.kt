package com.example.vpweek8.Soal2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.vpweek8.Soal2.ui.viewmodel.AlbumDetailViewModel
import com.example.artistexplorer.model.AlbumDetailUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    idAlbum: String,
    navController: NavController,
    viewModel: AlbumDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(idAlbum) {
        viewModel.fetchTracks(idAlbum)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Album Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            }

            uiState.isError -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) { Text("Gagal memuat data album") }
            }

            else -> {
                val album: AlbumDetailUiModel? = uiState.albumDetail

                album?.let {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {
                        item {
                            Image(
                                painter = rememberAsyncImagePainter(it.coverUrl),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(280.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(Modifier.height(12.dp))
                            Text(it.name, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                            Text("${it.year} • ${it.genre}", fontSize = 14.sp)
                            Spacer(Modifier.height(8.dp))
                            Text(it.description.ifBlank { "No description available." })
                            Spacer(Modifier.height(16.dp))
                            Text("Tracks", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                            Spacer(Modifier.height(8.dp))
                        }

                        itemsIndexed(it.tracks) { index, track ->
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("${index + 1}", modifier = Modifier.width(24.dp))
                                Spacer(Modifier.width(8.dp))
                                Text(track.name, modifier = Modifier.weight(1f))
                                Text(track.duration, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
