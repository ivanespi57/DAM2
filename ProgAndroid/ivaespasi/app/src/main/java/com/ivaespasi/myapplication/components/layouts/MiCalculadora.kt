package com.ivaespasi.myapplication.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---------- CONSTANTES ----------
// Lista de operadores que se pueden escribir en la pantalla
private val operadores = listOf("+", "-", "×", "÷", "%")

// Distribución de los botones: cada lista interna es una fila de la calculadora
private val filas = listOf(
    listOf("C", "←", "%", "÷"),
    listOf("7", "8", "9", "×"),
    listOf("4", "5", "6", "-"),
    listOf("1", "2", "3", "+"),
    listOf("0", ".", "=")
)

@Composable
fun MiCalculadora(modifier: Modifier = Modifier) {
    var pantalla by rememberSaveable { mutableStateOf("0") }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation ==
            android.content.res.Configuration.ORIENTATION_LANDSCAPE

    val paddingGeneral = if (isLandscape) 4.dp else 12.dp
    val tamanoPantalla = if (isLandscape) 36.sp else 56.sp

    // Columna principal: ocupa toda la pantalla, fondo negro
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(paddingGeneral)
    ) {
        // Caja de la pantalla: pesa 1,2 y alinea el texto abajo a la derecha
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = pantalla,
                color = Color.White,
                fontSize = tamanoPantalla,       // los tamaños de fuente van en sp
                textAlign = TextAlign.End,
                maxLines = 1,                    // una sola línea
                overflow = TextOverflow.Ellipsis, // "..." si no cabe
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Recorremos cada fila de botones
        filas.forEach { fila ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

            }
        }
    }
}



