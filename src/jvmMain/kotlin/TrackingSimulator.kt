import androidx.compose.runtime.mutableStateListOf
import java.io.File
import java.io.InputStream

class TrackingSimulator {
        var shipments = mutableStateListOf<Shipment>()
                private set

        fun findShipment(id: String): Shipment? {
                var foundShipment = false
                shipments.forEach{
                        if (id == it.id) {
                                foundShipment = true
                                return(it)
                        }
                }
                if (foundShipment == false) {
                        println("No shipment was found with that id.")
                        return null
                }
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
                                val shipment: Shipment = Shipment(it[1], it.first())
                                addShipment(shipment)
                        }
                }
        }
}