// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.text.SimpleDateFormat

@Composable
fun ShipmentView(viewHelper: TrackerViewHelper, remove: () -> Unit) {
    Column {
        Row(modifier = Modifier.padding(16.dp)) {
            Text("Shipment: " + viewHelper.shipmentId)

            Column(modifier = Modifier.padding(16.dp)) {
                Text("Status: " + viewHelper.shipmentStatus)
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    Text("Expected Delivery Date: " + viewHelper.expectedShipmentDeliveryDate)
                }
                Row {
                    Text("Location: " + viewHelper.shipmentLocation)
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                IconButton(remove) {
                    Icon(Icons.Filled.Close, "Close")
                }
            }
        }
        Row(modifier = Modifier.padding(16.dp)) {
            Text("Updates: ")
            Column {
                val updates = viewHelper.shipmentUpdateHistory.size
                var i = 0
                repeat(updates) {
                    if (viewHelper.shipmentUpdateHistory[i].previousStatus != "") {
                        val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS")
                        val dateString = simpleDateFormat.format(viewHelper.shipmentUpdateHistory[i].timeStamp)
                        Text(
                            "Shipment went from " + viewHelper.shipmentUpdateHistory[i].previousStatus +
                                    " to " + viewHelper.shipmentUpdateHistory[i].newStatus + " on " + dateString
                        )
                    }
                    i++
                }
            }
        }
        Row(modifier = Modifier.padding(16.dp)) {
            Text("Notes: ")
            Column {
                val notes = viewHelper.shipmentNotes.size
                var i = 0
                repeat(notes) {
                    Text(viewHelper.shipmentNotes[i])
                    i++
                }
            }
        }
    }
}


@Composable
fun App() {
    val simulator = TrackingSimulator()
    simulator.runSimulation()

    MaterialTheme {
        var id by remember { mutableStateOf("") }
        val viewHelpers = remember { mutableStateListOf<TrackerViewHelper>() }

        Column {
            Row {
                Text("Please enter a shipping id to track: ")
            }
            Row {
                TextField(id, onValueChange = { id = it })
                Button({
                    val shipment = simulator.findShipment(id)
                    if (shipment != null) {
                        val viewHelper = TrackerViewHelper(shipment)
                        viewHelper.trackShipment(viewHelpers, shipment)
                    }
                }) {
                    Text("Track")
                }
            }
            Row {
                LazyColumn {
                    items(viewHelpers) { viewHelper ->
                        ShipmentView(viewHelper) {
                            val shipment = simulator.findShipment(viewHelper.shipmentId)
                            if (shipment != null) {
                                viewHelper.stopTracking(viewHelpers, shipment)
                            }
                        }
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
