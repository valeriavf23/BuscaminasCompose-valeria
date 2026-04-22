package org.example.project.vista

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.vistamodelo.EstadoJuego

@Composable
fun BuscaminasApp() {
    var estadoJuego by remember { mutableStateOf<EstadoJuego?>(null) }

    MaterialTheme {
        if (estadoJuego == null) {
            // --- MENÚ PRINCIPAL ---
            var nStr by remember { mutableStateOf("10") }
            var mStr by remember { mutableStateOf("10") }
            var errorMsg by remember { mutableStateOf("") }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Buscaminas", style = MaterialTheme.typography.h4)
                Spacer(Modifier.height(20.dp))

                OutlinedTextField(
                    value = nStr,
                    onValueChange = { nStr = it.filter { c -> c.isDigit() } },
                    label = { Text("Tamaño del tablero (N x N)") }
                )

                OutlinedTextField(
                    value = mStr,
                    onValueChange = { mStr = it.filter { c -> c.isDigit() } },
                    label = { Text("Número de minas") }
                )

                if (errorMsg.isNotEmpty()) {
                    Text(errorMsg, color = Color.Red, modifier = Modifier.padding(8.dp))
                }

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = {
                        val n = nStr.toIntOrNull() ?: 10
                        val m = mStr.toIntOrNull() ?: 10
                        val maxMinas = (n * n) - 1

                        if (n < 2) {
                            errorMsg = "Tamaño mínimo: 2"
                        } else if (m > maxMinas) {
                            // VALIDACIÓN: No más minas que casillas
                            errorMsg = "Máximo $maxMinas minas para este tamaño"
                        } else {
                            errorMsg = ""
                            estadoJuego = EstadoJuego(n, n, m)
                        }
                    },
                    modifier = Modifier.width(200.dp).height(50.dp)
                ) {
                    Text("¡JUGAR!")
                }
            }
        } else {
            // --- JUEGO ---
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { estadoJuego = null }, modifier = Modifier.padding(8.dp)) {
                    Text("Volver al Menú")
                }
                BuscaminasUI(estadoJuego!!)
            }
        }
    }
}