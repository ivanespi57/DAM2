package com.ivaespasi.myapplication.components.layouts

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun MiState(modifier: Modifier){
    var number = rememberSaveable { mutableStateOf(0) }
    Text(
        text = "Púlsame: ${number.value}",
        modifier = modifier.clickable { number.value += 1 }
    )
}