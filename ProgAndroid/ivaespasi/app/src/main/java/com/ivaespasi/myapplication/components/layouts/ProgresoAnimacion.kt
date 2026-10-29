package com.ivaespasi.myapplication.components.layouts

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ivaespasi.myapplication.R
import kotlinx.coroutines.delay

//@Composable
//fun CargaAnimacion(modifier: Modifier = Modifier) {
//    var muestraAnimacion by remember { mutableStateOf(true) }
//
//    LaunchedEffect(Unit) {
//        delay(2000)
//        muestraAnimacion = false
//    }
//
//    if (muestraAnimacion) {
//        EjecutaCargaAnimacion(modifier)
//    } else {
//        PantallaPrincipal("Iván", modifier)
//    }
//}
//
//@Composable
//fun EjecutaCargaAnimacion(modifier: Modifier = Modifier) {
//    Box(
//        modifier = modifier
//            .fillMaxSize()
//            .background(Color.White),
//        contentAlignment = Alignment.Center
//    ) {
//        val composition by rememberLottieComposition(
//            LottieCompositionSpec.RawRes(R.raw.loading)
//        )
//        val progress by animateLottieCompositionAsState(
//            composition = composition,
//            iterations = LottieConstants.IterateForever
//        )
//        LottieAnimation(
//            composition = composition,
//            progress = { progress },
//            modifier = Modifier.size(200.dp)
//        )
//    }
//}
//
//@Composable
//fun PantallaPrincipal(name: String, modifier: Modifier = Modifier) {
//    Box(
//        modifier = modifier
//            .fillMaxSize()
//            .background(Color.White ),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "Hola $name!",
//            color = Color.Black,
//            fontSize = 24.sp
//        )
//    }
//}
@Composable
fun ProgresoAnimacion(modifier: Modifier = Modifier) {
    val composicion by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading)
    )

    // Desplazamiento horizontal en dp (negativo = izquierda, positivo = derecha)
    var desplazamiento by rememberSaveable { mutableIntStateOf(0) }

    // Anima el movimiento para que sea suave
    val desplazamientoAnimado by animateDpAsState(targetValue = desplazamiento.dp)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composicion,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .size(200.dp)
                .offset(x = desplazamientoAnimado)
        )

        Row(Modifier.padding(24.dp)) {
            Button(onClick = {
                desplazamiento = (desplazamiento - 40).coerceIn(-150, 150)
            }) { Text("<-") }

            Spacer(Modifier.width(24.dp))

            Button(onClick = {
                desplazamiento = (desplazamiento + 40).coerceIn(-150, 150)
            }) { Text("->") }
        }
    }
}