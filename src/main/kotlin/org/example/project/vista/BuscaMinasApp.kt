package org.example.project.vista

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import org.example.project.vistamodelo.EstadoJuego

@Composable
fun BuscaminasApp() {
    // Estado que controla si estamos en el menú (null) o en el juego (EstadoJuego)
    var estadoJuego by remember { mutableStateOf<EstadoJuego?>(null) }

    MaterialTheme {
        if (estadoJuego == null) {
            // --- PANTALLA DE MENÚ (Sin clase nueva) ---
            var nStr by remember { mutableStateOf("10") } // Para el tamaño n x n
            var minasStr by remember { mutableStateOf("10") }

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
                    label = { Text("Tamaño del tablero") }
                )

                OutlinedTextField(
                    value = minasStr,
                    onValueChange = { minasStr = it.filter { c -> c.isDigit() } },
                    label = { Text("Número de minas") }
                )

                Spacer(Modifier.height(30.dp))

                Button(
                    onClick = {
                        val n = nStr.toIntOrNull() ?: 5
                        val m = minasStr.toIntOrNull() ?: 5
                        // Creamos el estado con las medidas elegidas
                        estadoJuego = EstadoJuego(filas = n, columnas = n, numMinas = m)
                    },
                    modifier = Modifier.width(200.dp).height(50.dp)
                ) {
                    Text("¡JUGAR!")
                }
            }
        } else {
            // --- PANTALLA DE JUEGO ---
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Botón para volver al menú
                TextButton(onClick = { estadoJuego = null }) {
                    Text("< Volver al Menú", color = MaterialTheme.colors.primary)
                }

                // Llamamos a tu interfaz de siempre
                BuscaminasUI(estadoJuego!!)
            }
        }
    }
}