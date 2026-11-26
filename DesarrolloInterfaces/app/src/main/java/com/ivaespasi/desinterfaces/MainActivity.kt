package com.ivaespasi.desinterfaces

import android.R
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
                    Ejer8(Modifier.padding(innerPadding))
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

@Composable
fun Ejer7(modifier: Modifier){
    val moradoBtn = Color(0xFF7B5FD1)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("MiApp", color = moradoBtn, fontSize = 44.sp, fontWeight = FontWeight.Bold)
        Text("Organiza tus tareas del dia")
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {},
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = moradoBtn
            )
        ) {
            Text("Iniciar sesión")
        }
        OutlinedButton(
            onClick = {},
            modifier = Modifier.width(300.dp),
            border = BorderStroke(1.dp, moradoBtn),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = moradoBtn
            )
        ) {
            Text("Crear cuenta")
        }
        TextButton(onClick = {}) {
            Text("Continuar como Invitado", color = moradoBtn)
        }
    }
}

@Composable
fun Ejer8(modifier: Modifier = Modifier) {
    val grisBox = Color(0xFFE8E2D8)

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .background(grisBox, RoundedCornerShape(16.dp))
                .padding(15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Text("¿Eliminar el archivo?", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Text("Se borrará foto_imaginaria.jpg", fontWeight = FontWeight.Light, fontSize = 15.sp)
            Text("Esta acción no se puede deshacer", fontWeight = FontWeight.Light, fontSize = 15.sp)
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End)
            ) {
                TextButton(onClick = {}) {
                    Text("Cancelar")
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text("Eliminar")
                }
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
