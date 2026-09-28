package components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Preview
@Composable
fun MiBox(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment =
        Alignment.CenterEnd){
        Box(modifier = Modifier
            .width(75.dp)
            .height(65.dp)
            .background(Color.Yellow)
            .verticalScroll(rememberScrollState())
        ){
            Text("¡Hola Caracola!")
        }
    }
}
