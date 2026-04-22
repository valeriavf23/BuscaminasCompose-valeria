package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.vista.BuscaminasApp

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Buscaminas MVVM") {
        BuscaminasApp()
    }
}