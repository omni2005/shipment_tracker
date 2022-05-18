// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*

@Composable
@Preview
fun App() {
    var simulator: TrackingSimulator = TrackingSimulator()
    simulator.runSimulation()

    var id by remember { mutableStateOf("") }

    MaterialTheme {
        Column {
            Row {
                Text("Please enter a shipping id to track: ")
            }
            TextField(id, onValueChange = {id = it})
            Button({
            }) {
                Text("Track")
            }
        }
    }
}

/*
fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(5000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}
 */

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
