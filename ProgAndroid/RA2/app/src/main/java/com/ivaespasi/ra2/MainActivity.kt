package com.ivaespasi.ra2

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
import com.ivaespasi.ra2.components.layouts.FormularioPadre
import com.ivaespasi.ra2.components.layouts.PedidoPadreComida
import com.ivaespasi.ra2.ui.theme.RA2Theme
import com.ivaespasi.ra2.up6.AppConParametros
import com.ivaespasi.ra2.up6.AppConRutasConstantes
import com.ivaespasi.ra2.up6.NavegacionBasica

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RA2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //FormularioPadre(modifier = Modifier.fillMaxSize().padding(innerPadding))
                    //PedidoPadreComida(modifier = Modifier.fillMaxSize().padding(innerPadding))
                    //NavegacionBasica(modifier = Modifier.padding(innerPadding))
                    //AppConParametros(modifier = Modifier.padding(innerPadding))
                    AppConRutasConstantes(modifier = Modifier.padding(innerPadding))
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
    RA2Theme {
        Greeting("Android")
    }
}