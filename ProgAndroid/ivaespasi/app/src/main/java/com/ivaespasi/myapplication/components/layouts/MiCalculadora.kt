
package com.ivaespasi.myapplication.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.objecthunter.exp4j.ExpressionBuilder

private val ColorFondo = Color(0xFF181818)
private val ColorNumero = Color(0xFF2B2B2B)
private val ColorFuncion = Color(0xFFBDBDBD)
private val ColorOperador = Color(0xFFFB9902)

@Preview(showBackground = true)
@Composable
fun Calculadora(modifier: Modifier = Modifier) {
    var pantalla by rememberSaveable { mutableStateOf("") }

    // Actualiza la pantalla al pulsar
    val pulsar: (String) -> Unit = { tecla ->
        pantalla = procesarTecla(pantalla, tecla)
    }

    Box(modifier = Modifier.fillMaxSize().background(ColorFondo)) {
        Column(modifier = modifier.fillMaxSize().padding(horizontal = 4.dp)) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(end = 16.dp, bottom = 8.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = pantalla.ifEmpty { "0" },
                    color = Color.White,
                    fontSize = 80.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column(modifier = Modifier.weight(1.5f).fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    Boton("C", ColorFuncion, Color.Black, Modifier.weight(1f), FontWeight.Bold) {
                        pantalla = pulsarTecla(pantalla, "C")
                    }
                    Boton("+ -", ColorFuncion, Color.Black, Modifier.weight(1f), FontWeight.Bold) {
                        pantalla = pulsarTecla(pantalla, "+ -")
                    }
                    Boton("%", ColorFuncion, Color.Black, Modifier.weight(1f), FontWeight.Bold) {
                        pantalla = pulsarTecla(pantalla, "%")
                    }
                    Boton("/", ColorOperador, Color.White, Modifier.weight(1f)) {
                        pantalla = pulsarTecla(pantalla, "/")
                    }
                }

                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    Boton("7", ColorNumero, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "7") }
                    Boton("8", ColorNumero, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "8") }
                    Boton("9", ColorNumero, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "9") }
                    Boton("x", ColorOperador, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "x") }
                }

                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    Boton("4", ColorNumero, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "4") }
                    Boton("5", ColorNumero, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "5") }
                    Boton("6", ColorNumero, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "6") }
                    Boton("-", ColorOperador, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "-") }
                }

                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    Boton("1", ColorNumero, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "1") }
                    Boton("2", ColorNumero, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "2") }
                    Boton("3", ColorNumero, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "3") }
                    Boton("+", ColorOperador, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "+") }
                }

                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    Boton("0", ColorNumero, Color.White, Modifier.weight(2f)) { pantalla = pulsarTecla(pantalla, "0") }
                    Boton(".", ColorNumero, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, ".") }
                    Boton("=", ColorOperador, Color.White, Modifier.weight(1f)) { pantalla = pulsarTecla(pantalla, "=") }
                }
            }
        }
    }
}

@Composable
fun Boton(
    texto: String,
    colorFondo: Color,
    colorTexto: Color,
    modifier: Modifier = Modifier,
    peso: FontWeight = FontWeight.Normal,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(horizontal = 13.dp, vertical = 5.dp).fillMaxHeight(),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorFondo,
            contentColor = colorTexto
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(texto, fontSize = 30.sp, fontWeight = peso)
    }
}

// Gestiona la tecla pulsada
fun procesarTecla(actual: String, tecla: String): String {
    return when (tecla) {
        "C" -> ""
        "+ -" -> cambiarSigno(actual)
        "%" -> porcentaje(actual)
        "=" -> calcular(actual)
        else -> actual + tecla
    }
}

// Cambia el signo del número
fun cambiarSigno(texto: String): String {
    if (texto.isEmpty()) return texto
    if (texto.startsWith("-")) return texto.drop(1)
    return "-$texto"
}

// Calcula el porcentaje
fun porcentaje(texto: String): String {
    val resultado = calcular(texto)
    if (resultado.isEmpty() || resultado == "Error") return resultado

    return try {
        formatear(resultado.toDouble() / 100)
    } catch (e: Exception) {
        "Error"
    }
}

// Calcula la expresión
fun calcular(texto: String): String {
    if (texto.isEmpty()) return ""

    return try {
        val resultado = ExpressionBuilder(texto.replace("x", "*"))
            .build()
            .evaluate()

        formatear(resultado)
    } catch (e: Exception) {
        "Error"
    }
}

// Quita el decimal si es entero
fun formatear(numero: Double): String {
    return if (numero.isFinite() && numero == numero.toLong().toDouble()) {
        numero.toLong().toString()
    } else {
        numero.toString()
    }
}

// Aplica la tecla a la expresión
fun pulsarTecla(actual: String, tecla: String): String {
    return procesarTecla(actual, tecla)
}
