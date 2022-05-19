import androidx.compose.runtime.mutableStateListOf
import strategy.*
import java.io.File
import java.io.InputStream

class TrackingSimulator {
        private var shipments = mutableStateListOf<Shipment>()

        private val operationStrategies = mapOf<String, UpdateTypeStrategy>(
                Pair("created", CreatedStrategy()),
                Pair("canceled", CanceledStrategy()),
                Pair("delayed", DelayedStrategy()),
                Pair("delivered", DeliveredStrategy()),
                Pair("location", LocationStrategy()),
                Pair("lost", LostStrategy()),
                Pair("noteadded", NoteAddedStrategy()),
                Pair("shipped", ShippedStrategy())
        )

        fun findShipment(id: String): Shipment? {
                shipments.forEach {
                        if (id == it.id) {
                                return (it)
                        }
                }
                println("No shipment was found with that id.")
                return null
        }

        fun addShipment(shipment: Shipment) {
                shipments.add(shipment)
        }

        fun runSimulation() {
                val inputStream: InputStream = File("test.txt").inputStream()
                val lines = mutableListOf<String>()

                inputStream.bufferedReader().forEachLine { lines.add(it) }
                val lineList = mutableListOf<List<String>>()
                lines.forEach { lineList.add(it.split(",")) }

                lineList.forEach {
                        if (it.first() == "created") {
                                val shipment = Shipment(it[1], it.first(), it[2].toLong())
                                addShipment(shipment)
                        }
                }
        }
}