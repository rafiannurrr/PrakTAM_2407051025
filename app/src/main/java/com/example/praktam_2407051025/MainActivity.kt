package com.example.praktam_2407051025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praktam_2407051025.model.BeasiswaSource
import com.example.praktam_2407051025.ui.theme.PrakTAM_2407051025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM_2407051025Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    // Langkah 6: Memanggil data pertama dari list (index ke-0)
    val beasiswa = BeasiswaSource.dummyBeasiswa[0]
    
    Column(modifier = modifier.fillMaxSize().padding(24.dp)) {
        Text(text = "Nama: ${beasiswa.nama}")
        Text(text = "Deskripsi: ${beasiswa.deskripsi}")
        Text(text = "Deadline: ${beasiswa.deadline}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrakTAM_2407051025Theme {
        Greeting()
    }
}
