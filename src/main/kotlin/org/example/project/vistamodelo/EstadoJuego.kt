package org.example.project.vistamodelo

import androidx.compose.runtime.*
import kotlinx.coroutines.*
import org.example.project.modelo.Buscaminas

class EstadoJuego(val filas: Int, val columnas: Int, val numMinas: Int) {
    val juego = Buscaminas(filas, columnas, numMinas)

    // Estados que la UI observará
    var refresco by mutableStateOf(0)
    var tiempo by mutableStateOf(0)

    init {
        // Temporizador asíncrono con corrutinas
        CoroutineScope(Dispatchers.Default).launch {
            while (!juego.juegoFinalizado) {
                delay(1000)
                tiempo++
            }
        }
    }

    fun clicar(f: Int, c: Int, esBandera: Boolean) {
        if (!juego.juegoFinalizado) {
            if (esBandera) juego.conmutarBandera(f, c)
            else juego.destapar(f, c)
            refresco++ // Notifica a la UI para redibujar
        }
    }
}