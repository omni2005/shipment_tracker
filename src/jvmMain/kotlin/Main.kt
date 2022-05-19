// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.text.SimpleDateFormat

@Composable
fun ShipmentView(shipment: Shipment, remove: () -> Unit) {
    Column {
            Row(modifier = Modifier.padding(16.dp)) {
                Text("Shipment: " + shipment.id)

                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Status: " + shipment.status)
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy")
                    val dateString = simpleDateFormat.format(shipment.expectedDeliveryDateTimestamp)
                    Text("Expected Delivery Date: " + dateString)
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    Button(remove) {
                        Text("Close")
                    }
                }
            }
        Row(modifier = Modifier.padding(16.dp)) {
            Text("Notes: ")
        }
    }
}

@Composable
fun App() {
    var simulator: TrackingSimulator = TrackingSimulator()
    simulator.runSimulation()


    MaterialTheme {
        var id by remember { mutableStateOf("") }
        val shipments = remember { mutableStateListOf<Shipment>() }

        Column {
            Row {
                Text("Please enter a shipping id to track: ")
            }
            Row {
                TextField(id, onValueChange = { id = it })
                Button({
                    val shipment = simulator.findShipment(id)
                    if (shipment != null) {
                        shipments.add(shipment)
                    }
                }) {
                    Text("Track")
                }
            }
            Row {
                LazyColumn {
                    items(shipments, key = {it}) {
                        id -> ShipmentView(id) { shipments.remove(id)}
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
