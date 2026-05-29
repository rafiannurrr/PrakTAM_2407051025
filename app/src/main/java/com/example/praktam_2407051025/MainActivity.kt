package com.example.praktam_2407051025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.praktam_2407051025.data.model.Beasiswa
import com.example.praktam_2407051025.data.repository.BeasiswaRepository
import com.example.praktam_2407051025.ui.theme.PrakTAM_2407051025Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM_2407051025Theme {
                DaftarBeasiswaScreen()
            }
        }
    }
}

@Composable
fun DaftarBeasiswaScreen() {
    val repository = remember { BeasiswaRepository() }
    var beasiswaList by remember { mutableStateOf<List<Beasiswa>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isLoading = true
        val response = repository.getBeasiswa()
        beasiswaList = response
        isLoading = false
        isError = response.isEmpty()
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (isError) {
        Box(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_dialog_alert),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Gagal Memuat Data",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Pastikan koneksi internet Anda menyala atau periksa alamat API Anda",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    text = "Rekomendasi Populer",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(beasiswaList) { item ->
                        BeasiswaRowItem(beasiswa = item)
                    }
                }
                Spacer(modifier = Modifier.height(45.dp))
                Text(
                    text = "Daftar Beasiswa Lengkap",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(beasiswaList) { item ->
                DetailBeasiswaScreen(beasiswa = item)
            }
        }
    }
}

@Composable
fun BeasiswaRowItem(beasiswa: Beasiswa) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(beasiswa.imageUrl)
                    .setHeader("User-Agent", "Mozilla/5.0")
                    .crossfade(true)
                    .build(),
                contentDescription = beasiswa.nama,
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                error = painterResource(id = android.R.drawable.ic_dialog_alert),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = beasiswa.nama,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = beasiswa.deadline,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun DetailBeasiswaScreen(beasiswa: Beasiswa) {
    var isFavorite by remember { mutableStateOf(false) }
    var isRegistering by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(beasiswa.imageUrl)
                            .setHeader("User-Agent", "Mozilla/5.0")
                            .crossfade(true)
                            .build(),
                        contentDescription = beasiswa.nama,
                        placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                        error = painterResource(id = android.R.drawable.ic_dialog_alert),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    IconButton(
                        onClick = { isFavorite = !isFavorite },
                        modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite Icon",
                            tint = if (isFavorite) Color.Red else Color.White
                        )
                    }
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = beasiswa.nama,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = beasiswa.deskripsi,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Batas Pendaftaran: ${beasiswa.deadline}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                isRegistering = true
                                delay(2000)
                                snackbarHostState.showSnackbar("Berhasil mendaftar ke ${beasiswa.nama}!")
                                isRegistering = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isRegistering
                    ) {
                        if (isRegistering) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Memproses...")
                        } else {
                            Text("Daftar Sekarang")
                        }
                    }
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
