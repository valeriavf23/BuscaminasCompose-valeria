import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.9.20"
    id("org.jetbrains.compose") version "1.5.10"
}

// ... (resto del archivo igual)
group = "org.example"
version = "1.0.0" // CAMBIA ESTO: Quita el "-SNAPSHOT"
// ...

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/informative")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}

compose.desktop {
    application {
        mainClass = "org.example.project.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Msi, TargetFormat.Deb)
        }
    }
}