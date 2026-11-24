package com.ivaespasi.ra2.up6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavegacionBasica(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "pantalla1") {
        composable("pantalla1") {
            Pantalla1(navController)
        }
        composable("pantalla2") {
            Pantalla2()
        }
    }
}
@Composable
fun Pantalla1(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBBDEFB)) // Fondo azul claro
    ) {
        val (texto, boton) = createRefs()
        Text(
            text = "Esta es la Pantalla 1",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.constrainAs(texto) {
                top.linkTo(parent.top, margin = 100.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Button(
            onClick = { navController.navigate("pantalla2") },
            modifier = Modifier.constrainAs(boton) {
                top.linkTo(texto.bottom, margin = 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Ir a Pantalla 2", fontSize = 20.sp)
        }
    }
}

@Composable
fun Pantalla2() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9C4)) // Fondo amarillo claro
    ) {
        val texto = createRef()
        Text(
            text = "Esta es la Pantalla 2",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.constrainAs(texto) {
                centerTo(parent)
            }
        )
    }
}
