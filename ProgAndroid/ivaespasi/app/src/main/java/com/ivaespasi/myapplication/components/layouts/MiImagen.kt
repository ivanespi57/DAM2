package com.ivaespasi.myapplication.components.layouts

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ivaespasi.myapplication.R


@Preview
@Composable
fun MiImagen() {
    Image(
        painter = painterResource(
            R.drawable.vela
        ),
        contentDescription = "foreground",
        modifier = Modifier.size(300.dp)
            .clip(RoundedCornerShape(50))
            .border(width = 15.dp,shape = CircleShape, brush = Brush.linearGradient(colors =
                listOf(Color.Red, Color.Blue, Color.Yellow))),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun MiImagenDeInternet() {
    AsyncImage(
        model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRfdPGQsZmRtySPnNPwjUsZaucN_MX844X2d49EA7yWg&s=10",
        contentDescription = "Imagen de prueba",
        modifier = Modifier.size(250.dp)
            .border(width = 15.dp,shape = CircleShape, brush = Brush.linearGradient(colors =
                listOf(Color.Red, Color.Blue, Color.Yellow))),
        onError = {
            Log.i("Image", "Ha ocurrido un error ${it.result.throwable.message}")
        }
    )
}
@Preview
@Composable
fun MiIcono() {
    Column {
        Icon(
            painter = painterResource(id = R.drawable.emoji_persona_saludando),
            contentDescription = null,
            modifier = Modifier.size(300.dp),
            tint = Color.Blue
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_cruelty_free_24),
            contentDescription = null,
            modifier = Modifier.size(300.dp),
            tint = Color.Blue
        )
    }
}