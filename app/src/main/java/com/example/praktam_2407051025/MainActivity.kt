package com.example.praktam_2407051025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praktam_2407051025.model.Beasiswa
import com.example.praktam_2407051025.model.BeasiswaSource
import com.example.praktam_2407051025.ui.theme.PrakTAM_2407051025Theme

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Aplikasi Edukasi Beasiswa",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        BeasiswaSource.dummyBeasiswa.forEach { beasiswa ->
            DetailBeasiswaScreen(beasiswa = beasiswa)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DetailBeasiswaScreen(beasiswa: Beasiswa) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = beasiswa.imageRes),
            contentDescription = beasiswa.nama,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = beasiswa.nama,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = beasiswa.deskripsi,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Batas Pendaftaran: ${beasiswa.deadline}",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Daftar Sekarang")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DaftarBeasiswaPreview() {
    PrakTAM_2407051025Theme {
        DaftarBeasiswaScreen()
    }
}
