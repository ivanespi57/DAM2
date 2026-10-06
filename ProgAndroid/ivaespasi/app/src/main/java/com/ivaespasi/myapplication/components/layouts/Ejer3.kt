package com.ivaespasi.myapplication.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val dia = Color(0xFF01074A)
val psp = Color(0xFFD5DE49)
val accDatos = Color(0xFFE6B34E)
val desInter = Color(0xFFFF421F)
val progMult = Color(0xFFD19FBC)
val PIM = Color(0xFF0086FC)
val IPE = Color(0xFFFFDD00)
val nubPublic = Color(0xFFF0FC7C)
val SGE = Color(0xFF88D7E3)
val digit = Color(0xFFFF7300)
val sos = Color(0xFF1A24EB)
val tutor = Color(0xFFF2DCB8)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Ejer3(modifier: Modifier = Modifier) {
    FlowColumn(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Hora("Lunes", dia, Color.White)
            Hora("Martes", dia, Color.White)
            Hora("Miércoles", dia, Color.White)
            Hora("Jueves", dia, Color.White)
            Hora("Viernes", dia, Color.White)
        }

        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Hora("Prog. de servicios y procesos", psp, Color.Black)
            Hora("", Color.White, Color.Black)
            Hora("", Color.White, Color.Black)
            Hora("Prog. multimedia y disp. moviles", progMult, Color.Black)
            Hora("Sis. de gestión empresarial", SGE, Color.Black)
        }

        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Hora("Prog. de servicios y procesos", psp, Color.Black)
            Hora("Prog. multimedia y disp. moviles", progMult, Color.Black)
            Hora("Nube pública", nubPublic, Color.Black)
            Hora("Prog. multimedia y disp. moviles", progMult, Color.Black)
            Hora("Sis. gestión empresarial", SGE, Color.Black)
        }

        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Hora("Acceso a datos", accDatos, Color.Black)
            Hora("Prog. multimedia y disp. moviles", progMult, Color.Black)
            Hora("Nube pública", nubPublic, Color.Black)
            Hora("Digitalización", digit, Color.Black)
            Hora("IPE", IPE, Color.Black)
        }

        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Hora("Acceso a datos", accDatos, Color.Black)
            Hora("Proyecto intermodular", PIM, Color.Black)
            Hora("Proyecto intermodular", PIM, Color.Black)
            Hora("Sostenibilidad", sos, Color.Black)
            Hora("Acceso a datos", accDatos, Color.Black)
        }

        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Hora("Desarrollo de interfaces", desInter, Color.Black)
            Hora("IPE", IPE, Color.Black)
            Hora("Proyecto intermodular", PIM, Color.Black)
            Hora("Tutoría", tutor, Color.Black)
            Hora("Acceso a datos", accDatos, Color.Black)
        }

        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Hora("Desarrollo de interfaces", desInter, Color.Black)
            Hora("IPE", IPE, Color.Black)
            Hora("Sis. de gestión empresarial", SGE, Color.Black)
            Hora("Desarrollo de interfaces", desInter, Color.Black)
            Hora("", Color.White, Color.Black)
        }

        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Hora("Desarrollo de interfaces", desInter, Color.Black)
            Hora("Nube pública", nubPublic, Color.Black)
            Hora("Sis. de gestión empresarial", SGE, Color.Black)
            Hora("Desarrollo de interfaces", desInter, Color.Black)
            Hora("", Color.White, Color.Black)
        }
    }
}




@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowScope.Hora(texto: String, fondo: Color, colorTexto: Color) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(72.dp)
            .padding(1.dp)
            .background(fondo),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = colorTexto,
            fontSize = 11.sp,
            textAlign = TextAlign.Center
        )
    }
}