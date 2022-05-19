import androidx.compose.runtime.mutableStateListOf
import java.io.File
import java.io.InputStream
import java.awt.event.ActionListener
import javax.swing.Timer

class TrackingSimulator {
        var shipments = mutableStateListOf<Shipment>()
                private set

        private val updateStrategies = mapOf<String, UpdateTypeStrategy>(
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
                val lineList = arrayListOf<List<String>>()
                lines.forEach { lineList.add(it.split(",")) }

                /*
                lineList.forEach {
                        updateStrategies.get(it.first())?.getUpdate(it, this)
                }
                 */

                val delay = 1000
                var task = ActionListener() {}
                val timer = Timer(delay, task)
                task = ActionListener() {
                        if (lineList.isEmpty() == true) {
                                timer.stop()
                        }
                        println("Hello")
                        updateStrategies.get(lineList.first().first())?.getUpdate(lineList.first(), this)
                        lineList.remove(lineList.first())
                }
                timer.start()
        }
}