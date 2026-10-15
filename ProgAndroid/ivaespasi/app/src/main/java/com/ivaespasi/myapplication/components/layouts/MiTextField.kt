package com.ivaespasi.myapplication.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

//@Composable
//fun MiTextField(modifier: Modifier) {
//    Column(modifier = modifier){
//        var texto by rememberSaveable{mutableStateOf("")}
//        TextField(texto,onValueChange = {texto = it})
//    }
//}

@Composable
fun MiTextFieldPadre(modifier: Modifier){
    var usuario by rememberSaveable{mutableStateOf("Iván")}
    var mivalor by rememberSaveable { mutableStateOf("") }
    var mivalorAvanz by rememberSaveable { mutableStateOf("") }
    var mivalorPass by rememberSaveable { mutableStateOf("") }
    var mivalorOutline by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier) {
        MiTextField(usu = usuario) {usuario = it}
        MiSegundoTextField(valor = mivalor) {mivalor = it}
        MiTextFieldAvanzado(valor = mivalorAvanz) {mivalorAvanz = it}
        MyPasswordTextField(valor = mivalorPass) {mivalorPass = it}
        MiOutlinedTextField(valor = mivalorOutline) {mivalorOutline = it}
    }
}
//@Composable
//fun MiTextField(usu:String, onUserChange:(String) -> Unit) {
//    TextField(usu,onValueChange = {onUserChange(it)},readOnly=usu.isEmpty())
//}
//@Composable
//fun MiSegundoTextField(valor: String, onValueChange: (String) -> Unit) {
//    })
//}
//@Composable
//fun MiSegundoTextField(valor: String, onValueChange: (String) -> Unit) {
//    TextField(valor,onValueChange = {onValueChange(it)},placeholder = {
//        Box(Modifier.size(40.dp).background(Color.Green))
//    })
//}

@Composable
fun MiTextField(usu: String, onUserChange: (String) -> Unit) {
    TextField(usu,
        onValueChange = { onUserChange(it) },
        label = { Text("Introduce tu usuario:") })
}
@Composable
fun MiSegundoTextField(valor: String, onValueChange: (String) -> Unit) {
    TextField(valor,
        onValueChange = { onValueChange(it) },
        label = { Text("Introduce tu email:") })
}

@Composable
fun MiTextFieldAvanzado(valor: String, onValueChange: (String) -> Unit) {
    TextField(valor,
        onValueChange = {
            if(it.contains("a")){
                onValueChange(it.replace("a",""))
            }
            else {
                onValueChange(it)
            }
        })
}
@Composable
fun MyPasswordTextField(valor: String, onValueChange: (String) -> Unit) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextField(value = valor,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        label = { Text("Introduce tu contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordHidden) PasswordVisualTransformation()
        else VisualTransformation.None,
        trailingIcon = {
            Text(text = if (passwordHidden) "Mostrar" else "Ocultar",
                modifier = Modifier.clickable { passwordHidden = !passwordHidden
                })
        })
    Spacer(Modifier.height(20.dp))
}
@Composable
fun MiOutlinedTextField(valor: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = valor, onValueChange = { onValueChange(it) })
}