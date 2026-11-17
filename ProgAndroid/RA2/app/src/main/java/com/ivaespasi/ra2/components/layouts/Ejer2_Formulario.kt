package com.ivaespasi.ra2.components.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

// Composable "padre": guarda TODO el estado del formulario y se lo pasa a los hijos
@Composable
fun FormularioPadre(modifier: Modifier = Modifier) {
    // rememberSaveable recuerda el valor y lo conserva al girar la pantalla
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var genero by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var aceptado by rememberSaveable { mutableStateOf(false) } // estado del Switch
    var registrado by rememberSaveable { mutableStateOf(false) } // true tras pulsar el botón

    // El botón solo se habilita si todo está completo y se aceptan las condiciones
    val formularioCompleto = nombre.isNotBlank() &&
            apellidos.isNotBlank() &&
            genero.isNotEmpty() &&
            password.isNotEmpty() &&
            aceptado

    Column(
        modifier = modifier
            .fillMaxWidth()  // ocupa todo el ancho disponible
            .verticalScroll(rememberScrollState()) // permite hacer scroll en pantallas pequeñas
            .padding(16.dp), // margen exterior
        verticalArrangement = Arrangement.spacedBy(8.dp) // separación entre componentes
    ) {
        // Cada hijo recibe el valor actual y una lambda para avisar del cambio.
        // Al modificar cualquier campo se oculta el mensaje de registro.
        MiFormulario(usu = nombre) {
            nombre = it
            registrado = false
        }
        MiSegundoFormulario(valor = apellidos) {
            apellidos = it
            registrado = false
        }
        MiRadioButtonForm(genero = genero) {
            genero = it
            registrado = false
        }
        MiFormularioPassword(valor = password) {
            password = it
            registrado = false
        }
        MiSwitchCondiciones(aceptado = aceptado) {
            aceptado = it
            registrado = false
        }
        MiBotonRegistro(habilitado = formularioCompleto) {
            registrado = true
        }
        // El mensaje de confirmación solo aparece si se ha pulsado "Registrarse"
        if (registrado) {
            Text(
                text = "¡Registro completado! Bienvenido/a, $nombre $apellidos",
                color = Color(0xFF2E7D32) // verde oscuro
            )
        }
    }
}

// Campo de texto para el nombre
@Composable
fun MiFormulario(usu: String, onUserChange: (String) -> Unit) {
    TextField(
        value = usu, // texto que se muestra
        onValueChange = { onUserChange(it) }, // avisa al padre cuando el usuario escribe
        modifier = Modifier.fillMaxWidth(),
        singleLine = true, // una sola línea
        label = { Text("Nombre") }
    )
}

// Campo de texto para los apellidos
@Composable
fun MiSegundoFormulario(valor: String, onValueChange: (String) -> Unit) {
    TextField(
        value = valor,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text("Apellidos") }
    )
}

// Selector de género
@Composable
fun MiRadioButtonForm(
    genero: String,
    modifier: Modifier = Modifier,
    onGeneroChange: (String) -> Unit // al final para poder usar la sintaxis de lambda final
) {
    Text("Género:")
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = genero == "Masculino", // se marca si coincide con el valor guardado
            onClick = { onGeneroChange("Masculino") }
        )
        Text("Masculino")
    }
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = genero == "Femenino",
            onClick = { onGeneroChange("Femenino") }
        )
        Text("Femenino")
    }
}

// Campo de contraseña con opción de mostrar/ocultar el texto
@Composable
fun MiFormularioPassword(valor: String, onValueChange: (String) -> Unit) {
    // Este estado es solo de la interfaz (¿se ve la contraseña?), por eso vive aquí
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextField(
        value = valor,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text("Contraseña") },
        // Teclado especial para contraseñas (pista del enunciado)
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        // Si está oculta se muestran puntos; si no, el texto normal
        visualTransformation = if (passwordHidden) PasswordVisualTransformation()
        else VisualTransformation.None,
        // Texto clicable al final del campo que alterna entre Mostrar y Ocultar
        trailingIcon = {
            Text(
                text = if (passwordHidden) "Mostrar" else "Ocultar",
                modifier = Modifier
                    .clickable { passwordHidden = !passwordHidden }
                    .padding(end = 8.dp)
            )
        }
    )
}

// Switch para aceptar las condiciones de uso, con su texto al lado
@Composable
fun MiSwitchCondiciones(aceptado: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = aceptado, onCheckedChange = onCheckedChange)
        Spacer(Modifier.width(8.dp)) // espacio entre el Switch y el texto
        Text("Aceptar condiciones de uso")
    }
}

// Botón "Registrarse": verde si está habilitado, gris si no
@Composable
fun MiBotonRegistro(habilitado: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = habilitado,                    // depende de formularioCompleto
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4CAF50),  // verde (habilitado)
            contentColor = Color.White,
            disabledContainerColor = Color.Gray, // gris (deshabilitado)
            disabledContentColor = Color.White
        )
    ) {
        Text("Registrarse")
    }
}
