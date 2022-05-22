import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TrackerViewHelper(shipment: Shipment): Observer {
    var shipmentId by mutableStateOf<String>("")
    var shipmentNotes = mutableStateListOf<String>()
    var shipmentUpdateHistory = mutableStateListOf<ShippingUpdate>()
    var expectedShipmentDeliveryDate by mutableStateOf<Long>(0)
    var shipmentStatus by mutableStateOf<String>("")
    var shipmentLocation by mutableStateOf<String>("")
    init {
        this.shipmentId = shipment.id
        this.shipmentNotes = shipment.notes
        this.shipmentUpdateHistory = shipment.updateHistory
        this.expectedShipmentDeliveryDate = shipment.expectedDeliveryDateTimestamp
        this.shipmentStatus = shipment.status
        this.shipmentLocation = shipment.currentLocation
    }

    // Update with current information whenever observed object is updated
    override fun notify(shipment: Shipment) {
        this.shipmentNotes = shipment.notes
        this.shipmentUpdateHistory = shipment.updateHistory
        this.expectedShipmentDeliveryDate = shipment.expectedDeliveryDateTimestamp
        this.shipmentStatus = shipment.status
        this.shipmentLocation = shipment.currentLocation
    }

    // Add a viewHelper to list of created
    fun trackShipment(viewHelpers: MutableList<TrackerViewHelper>, shipment: Shipment) {
        viewHelpers.add(this)
        shipment.addObserver(this)
    }

    fun stopTracking(viewHelpers: MutableList<TrackerViewHelper>, shipment: Shipment) {
        viewHelpers.remove(this)
        shipment.removeObserver(this)
    }
}