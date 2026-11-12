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

@Composable
fun FormularioPadre(modifier: Modifier = Modifier) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var genero by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var aceptado by rememberSaveable { mutableStateOf(false) }
    var registrado by rememberSaveable { mutableStateOf(false) }

    // El botón solo se habilita si todo está completo y se aceptan las condiciones
    val formularioCompleto = nombre.isNotBlank() &&
            apellidos.isNotBlank() &&
            genero.isNotEmpty() &&
            password.isNotEmpty() &&
            aceptado

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
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
        if (registrado) {
            Text(
                text = "¡Registro completado! Bienvenido/a, $nombre $apellidos",
                color = Color(0xFF2E7D32)
            )
        }
    }
}

@Composable
fun MiFormulario(usu: String, onUserChange: (String) -> Unit) {
    TextField(
        value = usu,
        onValueChange = { onUserChange(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text("Nombre") }
    )
}

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

@Composable
fun MiRadioButtonForm(
    genero: String,
    modifier: Modifier = Modifier,
    onGeneroChange: (String) -> Unit
) {
    Text("Género:")
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = genero == "Masculino",
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

@Composable
fun MiFormularioPassword(valor: String, onValueChange: (String) -> Unit) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextField(
        value = valor,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text("Contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordHidden) PasswordVisualTransformation()
        else VisualTransformation.None,
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

@Composable
fun MiSwitchCondiciones(aceptado: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = aceptado, onCheckedChange = onCheckedChange)
        Spacer(Modifier.width(8.dp))
        Text("Aceptar condiciones de uso")
    }
}

@Composable
fun MiBotonRegistro(habilitado: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = habilitado,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4CAF50),
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        )
    ) {
        Text("Registrarse")
    }
}