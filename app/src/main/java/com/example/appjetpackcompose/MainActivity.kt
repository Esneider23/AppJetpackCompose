package com.example.appjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MyApp()
        }
    }
}

@Composable
private fun MyApp()
{
    Calculadora()
}


@Composable
private fun Calculadora()
{
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    )
    {

        Text(
            text = "Calcule su indice de masa muscular",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 28.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )
        )
        var nombre by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            placeholder = { Text("¿Cual es su nombre?") }
        )

        var peso by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso") },
            placeholder = { Text("¿Cual es su peso en kg?") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        var altura by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("altura") },
            placeholder = {Text("¿Cual es su altura en metros?")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        var masa by remember{ mutableStateOf(0.0)}
        var alturam by remember {mutableStateOf(0.0)}
        var altura2 by remember{ mutableStateOf(0.0)}

        var calculo = {
            alturam = altura.toDouble() / 100
            altura2 = alturam * alturam
            masa = peso.toDouble() / altura2
        }
        var resultado = when(masa)
        {
            in 1.0..15.0 -> "Delgades Extrema"
            in 15.0..15.9 -> "Delgades severa"
            in 16.0..18.4 -> "Delgades"
            in 18.5..24.9 -> "Peso saludable"
            in 25.0..29.9 -> "Sobrepeso"
            in 30.0..34.9 -> "Obesidad moderada"
            in 35.0..39.9 -> "Obesidad severa"
            else -> "Obesidad extrema"
        }
        Button(onClick = calculo)
        {
            Text(text = "Calcular")
        }
        Text(if (masa == 0.0 ) "" else "$nombre su masa corporal es de $masa y su composición corporal es de $resultado ")
    }
}


@Preview(showBackground = true, showSystemUi = true, device = Devices.DEFAULT)
@Composable
private fun DefaultPreview() {
        MyApp()
}