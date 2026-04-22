package org.example.project.vista

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.example.project.vistamodelo.EstadoJuego

@Composable
fun BuscaminasApp() {
    MaterialTheme {
        // 'remember' evita que el juego se reinicie con cada tick del reloj
        val estadoJuego = remember { EstadoJuego(filas = 10, columnas = 10, numMinas = 10) }
        BuscaminasUI(estadoJuego)
    }
}