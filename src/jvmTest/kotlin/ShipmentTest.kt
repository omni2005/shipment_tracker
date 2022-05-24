import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ShipmentTest {
    val shipment = Shipment("12345")
    val shipment2 = Shipment("00000")
    val note = "Note1"
    val note2 = "Note2"
    val note3 = ""
    val update = ShippingUpdate(101010101, "created")
    val update2 = ShippingUpdate(555555555, "lost")
    var viewHelper = TrackerViewHelper(shipment)
    var viewHelper2 = TrackerViewHelper(shipment2)

    @Test
    fun addNote() {
        shipment.addNote(note)
        assertEquals(shipment.notes.first(), "Note1")
        shipment.addNote(note2)
        assertEquals(shipment.notes.last(), "Note2")
        shipment.addNote(note3)
        assertEquals(shipment.notes.last(), "")
    }

    @Test
    fun addUpdate() {
        shipment.addUpdate(update)
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

    @Test
    fun addObserver() {
        assertEquals(viewHelper.shipmentId, "12345")
        assertEquals(viewHelper2.shipmentId, "00000")
    }

    @Test
    fun notifyObservers() {
        viewHelper = TrackerViewHelper(shipment)
        viewHelper2 = TrackerViewHelper(shipment)
        assertEquals(viewHelper.shipmentId, "12345")
        assertEquals(viewHelper2.shipmentId, "12345")
        shipment.addNote("Note")
        shipment.status = "delayed"
        assertEquals(viewHelper.shipmentNotes.first(), "Note")
        assertEquals(viewHelper2.shipmentNotes.first(), "Note")
        assertEquals(viewHelper.shipmentStatus, "delayed")
        assertEquals(viewHelper2.shipmentStatus, "delayed")
    }

    @Test
    fun removeObserver() {
        val viewHelper = TrackerViewHelper(shipment2)
        shipment2.removeObserver(viewHelper)
        shipment.status = "delayed"
        assertNotEquals(viewHelper.shipmentStatus, shipment.status)
    }
}