package com.example.ivaespasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ivaespasi.ui.theme.IvaespasiTheme
import components.layouts.MiBox
import components.layouts.MiColumn
import components.layouts.MiLayoutCombinado
import components.layouts.MiRow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IvaespasiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Greeting(
                      //  name = "Iván",
                    //    modifier = Modifier.padding(innerPadding)
                    //)
                    // MiBox()
                    // MiColumn()
                    // MiRow(Modifier.fillMaxSize())
                    MiLayoutCombinado(Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IvaespasiTheme {
        Greeting("Android")
    }
}