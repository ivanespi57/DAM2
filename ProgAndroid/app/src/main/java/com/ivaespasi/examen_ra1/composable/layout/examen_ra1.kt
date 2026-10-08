package com.ivaespasi.examen_ra1.composable.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.LineHeightStyle

@Composable
fun examen_ra1(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize().background(Color.LightGray)) {//Ocupa toda la pantalla
        val (boxPomo, boxPuerta, boxRedIzq1, boxRedDer1, boxBlue, boxRedMed1, boxRedMed2, boxRedIzq2, boxRedDer2, boxVentIzq, boxVentDer, boxNombre) =
            createRefs()
        Box(Modifier.size(350.dp).background(Color.Blue).constrainAs(boxBlue){
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })

        Box(Modifier.size(70.dp).background(Color.Red).constrainAs(boxRedDer1){
            bottom.linkTo(boxBlue.top)
            end.linkTo(boxBlue.end)
        })

        Box(Modifier.size(70.dp).background(Color.Red).constrainAs(boxRedIzq1
        ){
            bottom.linkTo(boxBlue.top)
            start.linkTo(boxBlue.start)
        })

        Box(Modifier.size(70.dp).background(Color.Red).constrainAs(boxRedMed1
        ){
            start.linkTo(boxRedIzq2.end)
            bottom.linkTo(boxRedDer2.top)
        })

        Box(Modifier.size(70.dp).background(Color.Red).constrainAs(boxRedMed2
        ){
            end.linkTo(boxRedDer1.start)
            start.linkTo(boxRedIzq1.end)
            bottom.linkTo(boxBlue.top)
        })

        Box(Modifier.size(70.dp).background(Color.Red).constrainAs(boxRedIzq2
        ){
            bottom.linkTo(boxRedMed2.top)
            start.linkTo(boxRedIzq1.end)
        })

        Box(Modifier.size(70.dp).background(Color.Red).constrainAs(boxRedDer2
        ){
            bottom.linkTo(boxRedMed2.top)
            start.linkTo(boxRedMed2.end)
        })

        Box(Modifier.size(70.dp).background(Color.Yellow).constrainAs(boxVentIzq
        ){
            bottom.linkTo(boxPuerta.top)
            top.linkTo(boxBlue.top)
            end.linkTo(boxPuerta.start)
        })

        Box(Modifier.size(70.dp).background(Color.Yellow).constrainAs(boxVentDer
        ){
            bottom.linkTo(boxPuerta.top)
            top.linkTo(boxBlue.top)
            start.linkTo(boxPuerta.end)
        })

        Box(Modifier.height(150.dp).width(100.dp).background(Color.Gray).constrainAs(boxPuerta
        ){
            bottom.linkTo(boxBlue.bottom)
            start.linkTo(boxBlue.start)
            end.linkTo(boxBlue.end)
        })

        Box(Modifier.height(10.dp).width(25.dp).background(Color.Black).constrainAs(boxPomo
        ){
            bottom.linkTo(boxPuerta.bottom)
            top.linkTo(boxPuerta.top)
            end.linkTo(boxPuerta.end)
        })

        Box(Modifier.height(10.dp).width(25.dp).background(Color.Black).constrainAs(boxPomo
        ){
            bottom.linkTo(boxPuerta.bottom)
            top.linkTo(boxPuerta.top)
            end.linkTo(boxPuerta.end)

        })
        Text(
            text = "Iván Espí Asins",
            modifier = Modifier
                .padding(horizontal = 140.dp)
                .padding(top = 790.dp)
                .background(Color.LightGray)
        )
    }
}