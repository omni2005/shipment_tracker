import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ShipmentTest {
    val shipment = Shipment("")
    val note1 = "Note1"
    val note2 = "Note2"
    val note3 = ""
    val update1 = ShippingUpdate(101010101, "created")
    val update2 = ShippingUpdate(555555555, "lost")

    @Test
    fun addNote() {
        shipment.addNote(note1)
        assertEquals(shipment.notes.first(), "Note1")
        shipment.addNote(note2)
        assertEquals(shipment.notes.last(), "Note2")
        shipment.addNote(note3)
        assertEquals(shipment.notes.last(), "")
    }

    @Test
    fun addUpdate() {
        shipment.addUpdate(update1)
        assertEquals(shipment.updateHistory.first().newStatus, "created")
        assertEquals(shipment.updateHistory.first().previousStatus, "")
        assertEquals(shipment.updateHistory.first().timeStamp, 101010101)
        assertEquals(shipment.status, "created")
        shipment.addUpdate(update2)
        assertEquals(shipment.updateHistory.last().newStatus, "lost")
        assertEquals(shipment.updateHistory.last().previousStatus, "created")
        assertEquals(shipment.updateHistory.last().timeStamp, 555555555)
        assertEquals(shipment.status, "lost")
    }
}