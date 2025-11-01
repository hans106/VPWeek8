package com.example.vpweek8

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artistexplorer.ui.theme.VpWeek8Theme
import com.example.vpweek8.Soal2.ui.screen.ArtistDetailScreen
import com.example.vpweek8.Soal2.ui.screen.AlbumDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VpWeek8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "artistDetail"
    ) {
        composable("artistDetail") {
            ArtistDetailScreen(navController = navController)
        }

        // Navigasi ke detail album (cukup kirim ID saja)
        composable(
            route = "albumDetail/{albumId}",
            arguments = listOf(
                navArgument("albumId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val albumId = Uri.decode(backStackEntry.arguments?.getString("albumId") ?: "")
            AlbumDetailScreen(
                idAlbum = albumId,
                navController = navController
            )
        }
    }
}
