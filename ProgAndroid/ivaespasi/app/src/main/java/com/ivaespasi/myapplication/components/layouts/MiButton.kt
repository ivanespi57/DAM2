package com.ivaespasi.myapplication.components.layouts

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MiButton(modifier: Modifier) {
    Column(modifier = modifier) {
        Button(onClick = { Log.i("Isabel", "Botón pulsado")},
            border = BorderStroke(2.dp, Color.Magenta),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow,
                contentColor = Color.Blue)
        )
        {
            Text("Pulsame", color = Color.Red)
        }
    }
}