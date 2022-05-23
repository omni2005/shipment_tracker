import androidx.compose.runtime.mutableStateListOf
import java.io.File
import java.io.InputStream
import java.awt.event.ActionListener
import javax.swing.Timer

class TrackingSimulator {
        private var shipments = mutableStateListOf<Shipment>()

        private val updateStrategies = mapOf<String, UpdateTypeStrategy>(
                Pair("created", CreatedStrategy()),
                Pair("canceled", StatusChangeStrategy()),
                Pair("delayed", DelayedStrategy()),
                Pair("delivered", StatusChangeStrategy()),
                Pair("location", LocationStrategy()),
                Pair("lost", StatusChangeStrategy()),
                Pair("noteadded", NoteAddedStrategy()),
                Pair("shipped", StatusChangeStrategy())
        )


        // Find shipment based on a given id
        fun findShipment(id: String): Shipment? {
                shipments.forEach {
                        if (id == it.id) {
                                return (it)
                        }
                }
                println("No shipment was found with that id.")
                return null
        }


        // Add shipment to simulation
        fun addShipment(shipment: Shipment) {
                shipments.add(shipment)
        }


        fun runSimulation() {
                // Read file and convert each line to a list of Strings
                val inputStream: InputStream = File("test.txt").inputStream()
                val lines = mutableListOf<String>()

                inputStream.bufferedReader().forEachLine { lines.add(it) }
                val lineList = arrayListOf<List<String>>()
                lines.forEach { lineList.add(it.split(",")) }

                // Update simulation with 1 line per second
                val delay = 1000
                var simulateShipments = ActionListener {}
                var timer = Timer(delay, simulateShipments)
                simulateShipments = ActionListener {
                        if (lineList.isEmpty()) {
                                timer.stop()
                        } else {
                                updateStrategies.get(lineList.first().first())?.getUpdate(lineList.first(), this)
                                lineList.remove(lineList.first())
                        }
                }
                timer = Timer(delay, simulateShipments)
                timer.start()
        }
}