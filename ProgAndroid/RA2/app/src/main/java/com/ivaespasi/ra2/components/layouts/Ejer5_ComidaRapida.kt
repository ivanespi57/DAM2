package com.ivaespasi.ra2.components.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// Composable padre: guarda todo el estado del pedido y se lo pasa a los hijos
@Composable
fun PedidoPadreComida(modifier: Modifier = Modifier) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var hamburguesa by rememberSaveable { mutableStateOf("") }      // "" = ninguna elegida
    var picante by rememberSaveable { mutableFloatStateOf(0f) }     // nivel de picante (0 a 10)
    var extraQueso by rememberSaveable { mutableStateOf(false) }
    var confirmado by rememberSaveable { mutableStateOf(false) }    // true al pulsar "Confirmar pedido"

    // Color de la pista según el nivel de picante
    val colorPicante = when {
        picante <= 3f -> Color(0xFF4CAF50)  // verde: suave
        picante <= 6f -> Color(0xFFFF9800)  // naranja: medio
        else -> Color.Red                   // rojo: muy picante
    }

    Column(modifier = modifier.padding(16.dp)) {
        MiTextFieldComida(valor = nombre, etiqueta = "Nombre", teclado = KeyboardType.Text) {
            nombre = it
            confirmado = false
        }
        Spacer(Modifier.height(8.dp))
        MiTextFieldComida(valor = telefono, etiqueta = "Teléfono", teclado = KeyboardType.Phone) {
            telefono = it
            confirmado = false
        }
        Spacer(Modifier.height(8.dp))
        MiDropDownHamburguesaComida(seleccionada = hamburguesa) {
            hamburguesa = it
            confirmado = false
        }
        Spacer(Modifier.height(16.dp))

        Text("Nivel de picante: ${String.format("%.0f", picante)}")
        MiSliderPicanteComida(valor = picante, colorPista = colorPicante) {
            picante = it
            confirmado = false
        }

        MiSwitchQuesoComida(extraQueso = extraQueso) {
            extraQueso = it
            confirmado = false
        }
        Spacer(Modifier.height(16.dp))

        // El botón solo se habilita si nombre y teléfono están completos
        MiBotonConfirmarComida(habilitado = nombre != "" && telefono != "") {
            confirmado = true
        }
        Spacer(Modifier.height(16.dp))

        // Resumen del pedido, siempre visible debajo
        MiResumenComida(
            nombre = nombre,
            telefono = telefono,
            hamburguesa = hamburguesa,
            picante = picante,
            extraQueso = extraQueso
        )
        if (confirmado) {
            Spacer(Modifier.height(8.dp))
            Text("¡Pedido confirmado!", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
        }
    }
}

// Campo de texto reutilizable para nombre y teléfono
@Composable
fun MiTextFieldComida(
    valor: String,
    etiqueta: String,
    teclado: KeyboardType,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = valor,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text(etiqueta) },
        keyboardOptions = KeyboardOptions(keyboardType = teclado) // teclado de texto o de teléfono
    )
}

// Menú desplegable para elegir la hamburguesa (Box + Button + DropdownMenu)
@Composable
fun MiDropDownHamburguesaComida(seleccionada: String, onSeleccion: (String) -> Unit) {
    var expandido by rememberSaveable { mutableStateOf(false) }
    val hamburguesas = listOf("Clásica", "Doble carne", "Pollo crujiente", "Vegetariana")

    Box {
        // El botón muestra la opción elegida (o un texto si no hay ninguna)
        Button(onClick = { expandido = true }) {
            Text(if (seleccionada.isEmpty()) "Elegir hamburguesa" else seleccionada)
        }
        DropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
            hamburguesas.forEach { opcion ->
                DropdownMenuItem(text = { Text(opcion) }, onClick = {
                    onSeleccion(opcion)  // guarda la opción en el padre
                    expandido = false    // pliega el menú
                })
            }
        }
    }
}

// Slider normal de picante: el color de la pista y de la bolita lo decide el padre
@Composable
fun MiSliderPicanteComida(valor: Float, colorPista: Color, onValueChange: (Float) -> Unit) {
    Slider(
        value = valor,
        onValueChange = { onValueChange(it) },
        valueRange = 0f..10f,   // picante de 0 a 10
        steps = 9,              // valores enteros
        colors = SliderDefaults.colors(
            thumbColor = colorPista,
            activeTrackColor = colorPista
        )
    )
}

// Switch para pedir extra de queso
@Composable
fun MiSwitchQuesoComida(extraQueso: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = extraQueso, onCheckedChange = onCheckedChange)
        Spacer(Modifier.width(8.dp))
        Text("Extra de queso")
    }
}

// Botón de confirmar (deshabilitado hasta que nombre y teléfono estén rellenos)
@Composable
fun MiBotonConfirmarComida(habilitado: Boolean, onClick: () -> Unit) {
    Button(onClick = onClick, enabled = habilitado) {
        Text("Confirmar pedido")
    }
}

// Resumen del pedido que se actualiza en directo
@Composable
fun MiResumenComida(
    nombre: String,
    telefono: String,
    hamburguesa: String,
    picante: Float,
    extraQueso: Boolean
) {
    Text("Resumen del pedido", fontWeight = FontWeight.Bold)
    Text("Nombre: $nombre")
    Text("Teléfono: $telefono")
    Text("Hamburguesa: ${if (hamburguesa.isEmpty()) "Sin elegir" else hamburguesa}")
    Text("Picante: ${String.format("%.0f", picante)}")
    Text("Extra de queso: ${if (extraQueso) "Sí" else "No"}")
}