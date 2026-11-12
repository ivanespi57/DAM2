package com.ivaespasi.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ivaespasi.myapplication.components.layouts.Calculadora
import com.ivaespasi.myapplication.components.layouts.MiDropDownItem
import com.ivaespasi.myapplication.components.layouts.MiDropDownMenu
import com.ivaespasi.myapplication.components.layouts.MiExposedDropDownMenu
import com.ivaespasi.myapplication.components.layouts.MiIcono
import com.ivaespasi.myapplication.components.layouts.MiImagen
import com.ivaespasi.myapplication.components.layouts.MiImagenDeInternet
import com.ivaespasi.myapplication.components.layouts.MiListaRadioButton
import com.ivaespasi.myapplication.components.layouts.MiProgreso
import com.ivaespasi.myapplication.components.layouts.MiRadioButton
import com.ivaespasi.myapplication.components.layouts.MiRangeSlider
import com.ivaespasi.myapplication.components.layouts.MiSlider
import com.ivaespasi.myapplication.components.layouts.MiSliderAvanzado
import com.ivaespasi.myapplication.components.layouts.MiSwitch
import com.ivaespasi.myapplication.components.layouts.PadreCheckBoxes
import com.ivaespasi.myapplication.components.layouts.ProgresoAnimacion
import com.ivaespasi.myapplication.components.layouts.TercerEstadoCheckBox

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                //Ejer3(Modifier.padding(innerPadding))
                //MiState(Modifier.fillMaxSize().padding(innerPadding))
                //MiText(Modifier.fillMaxSize().padding(innerPadding))
                //MiTextFieldPadre(Modifier.fillMaxSize().padding(innerPadding))
                //MiButton(Modifier.fillMaxSize().padding(innerPadding))
                //Calculadora(Modifier.padding(innerPadding))
                //MiImagen()
                //MiImagenDeInternet()
                //MiIcono()
                //MiProgreso()
                //CargaAnimacion(Modifier.padding(innerPadding))
                //ProgresoAnimacion(Modifier.padding(innerPadding))
                //MiSwitch(Modifier.padding(innerPadding))
                //MiCheckBox(Modifier.fillMaxSize().padding(innerPadding))
                //PadreCheckBoxes(modifier = Modifier.padding(innerPadding))
                //TercerEstadoCheckBox(modifier = Modifier.padding(innerPadding))
                //MiRadioButton(modifier = Modifier.padding(innerPadding))
                //MiListaRadioButton(modifier = Modifier.padding(innerPadding))
                //MiRangeSlider(modifier = Modifier.padding(innerPadding))
                //MiDropDownMenu(modifier = Modifier.padding(innerPadding))
                //MiExposedDropDownMenu(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}