package com.ivaespasi.desinterfaces

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivaespasi.desinterfaces.ui.theme.DesInterfacesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesInterfacesTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
//                    HolaMundoPers(
//                        name = "Iván",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    //Ejer2()
                    //Ejer4()
                    //Ejer5(Modifier.padding(innerPadding))
                    Ejer6(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HolaMundoPers(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hola, me llamo $name!",
        modifier = modifier,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue
    )
}

@Composable
fun Ejer2(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(onClick = {}) {
            Text("Empezar")
        }
    }
}

@Composable
fun Ejer4() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iván")
        Text("2 DAM")
        Text("Valencia")
    }
}

@Composable
fun Ejer5(modifier: Modifier) {
    val amarillo = Color(0xFFFFA000)
    val verde = Color(0xFF2E7D32)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Circulo(Color.Red)
            Circulo(amarillo)
            Circulo(verde)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Circulo(Color.Red)
            Circulo(amarillo)
            Circulo(verde)
        }
    }
}

@Composable
fun Circulo(color: Color) {
    Box(
        modifier = Modifier
            .size(75.dp)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun Ejer6(modifier: Modifier){
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Box(Modifier.background(Color.Blue).size(200.dp)){
            Text("OFERTA", color = Color.White)
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text("-20 %", color = Color.Yellow, fontSize = 44.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DesInterfacesTheme {
        HolaMundoPers("Iván")
    }
}

@Preview(showBackground = true)
@Composable
fun Ejer2Preview() {
    DesInterfacesTheme {
        Ejer2()
    }
}
