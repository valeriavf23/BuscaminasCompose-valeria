package org.example.project.vistamodelo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import org.example.project.modelo.Buscaminas
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class EstadoJuego(val filas: Int, val columnas: Int, val numMinas: Int) {
    val juego = Buscaminas(filas, columnas, numMinas)

    // Control de refresco de la UI para los clics
    var refresco by mutableStateOf(0)

    // Variable para el temporizador (segundos transcurridos)
    var tiempo by mutableStateOf(0)

    init {
        // Corrutina que gestiona el temporizador de forma asíncrona
        CoroutineScope(Dispatchers.Default).launch {
            // El contador sigue mientras el juego no haya terminado
            while (!juego.juegoFinalizado) {
                delay(1000) // Espera un segundo
                tiempo++    // Incrementa el contador
            }
        }
    }

    fun clicar(f: Int, c: Int, esBandera: Boolean) {
        if (!juego.juegoFinalizado) {
            if (esBandera) {
                juego.conmutarBandera(f, c)
            } else {
                juego.destapar(f, c)
            }
            // Al aumentar refresco, obligamos a BuscaminasUI a redibujarse
            refresco++
        }
    }
}