import java.io.File
import java.io.InputStream

class TrackingSimulator {
        var shipments = arrayListOf<Shipment>()

        fun findShipment(id: String): Shipment? {
                return null
        }

        fun addShipment(shipment: Shipment) {

        }

        fun runSimulation() {
                val inputStream: InputStream = File("test.txt").inputStream()
                val lines = mutableListOf<String>()

                inputStream.bufferedReader().forEachLine { lines.add(it) }
                val lineList = mutableListOf<List<String>>()
                lines.forEach{lineList.add(it.split(","))}
                lineList.forEach{it.forEach{(println(it))}}
        }
}