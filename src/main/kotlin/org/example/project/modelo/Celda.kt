package org.example.project.modelo

class Celda {
    var esMina: Boolean = false
    var estaDescubierta: Boolean = false
    var tieneBandera: Boolean = false
    var minasAlrededor: Int = 0

    override fun toString(): String {
        if (tieneBandera) return "🚩"
        if (!estaDescubierta) return ""
        if (esMina) return "💣"
        return if (minasAlrededor == 0) "" else minasAlrededor.toString()
    }
}