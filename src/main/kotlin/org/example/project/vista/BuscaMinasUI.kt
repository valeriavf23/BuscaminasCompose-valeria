package org.example.project.vista

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
    val tiempo = estado.tiempo
    val _upd = estado.refresco

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)
    ) {
        Text("⏱️ TIEMPO: $tiempo s", style = MaterialTheme.typography.h4)
        Spacer(Modifier.height(16.dp))

        for (f in 0 until estado.filas) {
            Row {
                for (c in 0 until estado.columnas) {
                    val celda = estado.juego.obtenerCelda(f, c)

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(1.dp)
                            .background(if (celda.estaDescubierta) Color.LightGray else Color.Gray)
                            .onClick(
                                matcher = PointerMatcher.mouse(PointerButton.Primary),
                                onClick = { estado.clicar(f, c, false) }
                            )
                            .onClick(
                                matcher = PointerMatcher.mouse(PointerButton.Secondary),
                                onClick = { estado.clicar(f, c, true) }
                            )
                    ) {
                        Text(celda.toString())
                    }
                }
            }
        }

        if (estado.juego.juegoFinalizado) {
            Text(
                text = if (estado.juego.victoria) "¡HAS GANADO! 🎉" else "¡HAS PERDIDO! 💣",
                style = MaterialTheme.typography.h3,
                color = if (estado.juego.victoria) Color.Blue else Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}