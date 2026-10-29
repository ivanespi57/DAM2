package com.ivaespasi.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ivaespasi.myapplication.components.layouts.Calculadora
import com.ivaespasi.myapplication.components.layouts.MiIcono
import com.ivaespasi.myapplication.components.layouts.MiImagen
import com.ivaespasi.myapplication.components.layouts.MiImagenDeInternet
import com.ivaespasi.myapplication.components.layouts.MiProgreso
import com.ivaespasi.myapplication.components.layouts.ProgresoAnimacion

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                //Ejer3(Modifier.padding(innerPadding))
                //MiState(Modifier.fillMaxSize().padding(innerPadding))
                //MiText(Modifier.fillMaxSize().padding(innerPadding))
                //MiTextFieldPadre(Modifier.fillMaxSize().padding(innerPadding))
                //MiButton(Modifier.fillMaxSize().padding(innerPadding))
                //Calculadora(Modifier.padding(innerPadding))
                //MiImagen()
                //MiImagenDeInternet()
                //MiIcono()
                //MiProgreso()
                //CargaAnimacion(Modifier.padding(innerPadding))
                ProgresoAnimacion(Modifier.padding(innerPadding))
            }
        }
    }
}