package org.example.project.modelo

import kotlin.random.Random

class Buscaminas(val filas: Int, val columnas: Int, val numMinas: Int) {
    private val tablero = Array(filas) { Array(columnas) { Celda() } }
    var juegoFinalizado = false
    var victoria = false
    private var primerMovimiento = true

    private fun colocarMinas(fEvitar: Int, cEvitar: Int) {
        var colocadas = 0
        while (colocadas < numMinas) {
            val f = Random.nextInt(filas)
            val c = Random.nextInt(columnas)
            val esSegura = Math.abs(f - fEvitar) <= 1 && Math.abs(c - cEvitar) <= 1
            if (!tablero[f][c].esMina && !esSegura) {
                tablero[f][c].esMina = true
                colocadas++
            }
        }
        calcularNumeros()
    }

    private fun calcularNumeros() {
        for (f in 0 until filas) {
            for (c in 0 until columnas) {
                if (!tablero[f][c].esMina) {
                    var contador = 0
                    for (i in -1..1) {
                        for (j in -1..1) {
                            val nf = f + i
                            val nc = c + j
                            if (nf in 0 until filas && nc in 0 until columnas && tablero[nf][nc].esMina) contador++
                        }
                    }
                    tablero[f][c].minasAlrededor = contador
                }
            }
        }
    }

    fun destapar(f: Int, c: Int) {
        if (f !in 0 until filas || c !in 0 until columnas || tablero[f][c].estaDescubierta || tablero[f][c].tieneBandera) return
        if (primerMovimiento) {
            colocarMinas(f, c)
            primerMovimiento = false
        }
        if (tablero[f][c].esMina) {
            explotarTodo()
            juegoFinalizado = true
            victoria = false
        } else {
            tablero[f][c].estaDescubierta = true
            if (tablero[f][c].minasAlrededor == 0) {
                for (i in -1..1) {
                    for (j in -1..1) destapar(f + i, c + j)
                }
            }
            verificarVictoria()
        }
    }

    private fun explotarTodo() {
        for (f in 0 until filas) {
            for (c in 0 until columnas) {
                if (tablero[f][c].esMina) tablero[f][c].estaDescubierta = true
            }
        }
    }

    fun conmutarBandera(f: Int, c: Int) {
        if (f in 0 until filas && c in 0 until columnas && !tablero[f][c].estaDescubierta) {
            tablero[f][c].tieneBandera = !tablero[f][c].tieneBandera
        }
    }

    private fun verificarVictoria() {
        var porDestapar = 0
        for (f in 0 until filas) {
            for (c in 0 until columnas) {
                if (!tablero[f][c].esMina && !tablero[f][c].estaDescubierta) porDestapar++
            }
        }
        if (porDestapar == 0) {
            juegoFinalizado = true
            victoria = true
        }
    }

    fun obtenerCelda(f: Int, c: Int) = tablero[f][c]
}