package org.example.project.vista

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.unit.dp
import org.example.project.vistamodelo.EstadoJuego

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BuscaminasUI(estado: EstadoJuego) {
    // 1. LEER LOS ESTADOS (Muy importante para que Compose reaccione)
    val horaActual = estado.horaActual
    val _refresco = estado.refresco

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // 2. EL RELOJ
        Text("🕒 RELOJ: $horaActual", style = MaterialTheme.typography.h5)
        Spacer(Modifier.height(16.dp))

        // 3. EL TABLERO
        for (f in 0 until estado.filas) {
            Row {
                for (c in 0 until estado.columnas) {
                    val celda = estado.juego.obtenerCelda(f, c)

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(45.dp)
                            .padding(1.dp)
                            .background(if (celda.estaDescubierta) Color.LightGray else Color.Gray)
                            .onClick(
                                matcher = PointerMatcher.mouse(PointerButton.Primary),
                                onClick = { estado.clicar(f, c, false) } // Clic normal
                            )
                            .onClick(
                                matcher = PointerMatcher.mouse(PointerButton.Secondary),
                                onClick = { estado.clicar(f, c, true) } // Clic derecho (bandera)
                            )
                    ) {
                        Text(celda.toString())
                    }
                }
            }
        }

        // 4. MENSAJE DE FIN DE JUEGO
        if (estado.juego.juegoFinalizado) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = if (estado.juego.victoria) "¡GANASTE! 🎉" else "¡BOOM! 💣",
                style = MaterialTheme.typography.h4,
                color = if (estado.juego.victoria) Color.Blue else Color.Red
            )
        }
    }
}