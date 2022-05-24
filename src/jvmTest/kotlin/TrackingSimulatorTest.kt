import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TrackingSimulatorTest {
    val simulator = TrackingSimulator()
    val shipment = Shipment("12345")
    val shipment2 = Shipment("abcde")
    val shipment3 = Shipment("///")

    @Test
    fun addShipment() {
        simulator.addShipment(shipment)
        assertEquals(simulator.shipments.first().id, "12345")
        simulator.addShipment(shipment2)
        assertEquals(simulator.shipments.last().id, "abcde")
        simulator.addShipment(shipment3)
        assertEquals(simulator.shipments.last().id, "///")
    }

    @Test
    fun findShipment() {
        simulator.addShipment(shipment)
        simulator.addShipment(shipment2)
        simulator.addShipment(shipment3)
        assertEquals(simulator.findShipment("12345"), shipment)
        assertEquals(simulator.findShipment("abcde"), shipment2)
        assertEquals(simulator.findShipment("///"), shipment3)
        assertEquals(simulator.findShipment("000"), null)
    }
}