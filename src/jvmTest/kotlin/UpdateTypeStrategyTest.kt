import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UpdateTypeStrategyTest {
    val simulator = TrackingSimulator()
    val createdShipment: List<String> = "created,s10000,1652712855468".split(",")
    val shippedShipment = "shipped,s10000,1652712855468,1652713940874".split(",")
    val shipmentLocation = "location,s10000,1652712855468,Los Angeles CA".split(",")
    val delayedShipment = "delayed,s10000,1652712855468,1652718051403".split(",")
    val noteAdded = "noteadded,s10000,1652712855468,note".split(",")
    val lostShipment = "lost,s10000,1652712855468".split(",")
    val canceledShipment = "canceled,s10000,1652712855468".split(",")
    val deliveredShipment = "delivered,s10000,1652712855468".split(",")

    @Test
    fun getUpdate() {
        simulator.updateStrategies.get(createdShipment.first())?.getUpdate(createdShipment, simulator)
        assertEquals(simulator.shipments.first().id, "s10000")
        simulator.updateStrategies.get(shippedShipment.first())?.getUpdate(shippedShipment, simulator)
        assertEquals(simulator.shipments.first().expectedDeliveryDateTimestamp, 1652713940874)
        simulator.updateStrategies.get(shipmentLocation.first())?.getUpdate(shipmentLocation, simulator)
        assertEquals(simulator.shipments.first().currentLocation, "Los Angeles CA")
        simulator.updateStrategies.get(delayedShipment.first())?.getUpdate(delayedShipment, simulator)
        assertEquals(simulator.shipments.first().expectedDeliveryDateTimestamp, 1652718051403)
        simulator.updateStrategies.get(noteAdded.first())?.getUpdate(noteAdded, simulator)
        assertEquals(simulator.shipments.first().notes.first(), "note")
        simulator.updateStrategies.get(lostShipment.first())?.getUpdate(lostShipment, simulator)
        assertEquals(simulator.shipments.first().status, "lost")
        simulator.updateStrategies.get(canceledShipment.first())?.getUpdate(canceledShipment, simulator)
        assertEquals(simulator.shipments.first().status, "canceled")
        simulator.updateStrategies.get(deliveredShipment.first())?.getUpdate(deliveredShipment, simulator)
        assertEquals(simulator.shipments.first().status, "delivered")
    }
}