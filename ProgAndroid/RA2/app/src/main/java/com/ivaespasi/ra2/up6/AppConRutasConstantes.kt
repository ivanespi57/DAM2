package com.ivaespasi.ra2.up6

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// Definimos las rutas como constantes dentro de un objeto
// Esto evita errores de escritura y facilita el mantenimiento
object Rutas {
    const val HOME = "home"
    const val PERFIL = "perfil"
}
@Composable
fun AppConRutasConstantes(modifier: Modifier = Modifier) {
    // Creamos un NavController que se usará para navegar entre pantallas
    val navController = rememberNavController()
    Box(modifier = modifier.fillMaxSize()) {
        // NavHost define la estructura de navegación
        // startDestination indica la pantalla inicial
        NavHost(navController, startDestination = Rutas.HOME) {
            // Cada pantalla se asocia con una ruta constante
            composable(Rutas.HOME) { HomeScreen(navController) }
            composable(Rutas.PERFIL) { PerfilScreen(navController) }
        }
    }
}
// Pantalla principal (Home)
@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Pantalla Home",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 50.dp)
        )
        // Botón para navegar a la pantalla de Perfil
        // Usamos la constante Rutas.PERFIL en lugar de una string directa
        Button(onClick = { navController.navigate(Rutas.PERFIL) }) {
            Text("Ir a Perfil")
        }
    }
}
// Pantalla de Perfil
@Composable
fun PerfilScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Pantalla de Perfil",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 50.dp)
        )
        // Botón para volver atrás
        // navController.popBackStack() elimina la pantalla actual del stack
        // y regresa a la anterior (en este caso, "home")
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver atrás")
        }
    }
}