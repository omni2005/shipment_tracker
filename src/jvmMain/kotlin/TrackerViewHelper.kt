import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat

class TrackerViewHelper(shipment: Shipment): Observer {
    var shipmentId by mutableStateOf<String>("")
    var shipmentNotes = mutableStateListOf<String>()
    var shipmentUpdateHistory = mutableStateListOf<ShippingUpdate>()
    var expectedShipmentDeliveryDate by mutableStateOf<String>("")
    var shipmentStatus by mutableStateOf<String>("")
    var shipmentLocation by mutableStateOf<String>("")
    init {
        this.shipmentId = shipment.id
        this.shipmentNotes = shipment.notes
        this.shipmentUpdateHistory = shipment.updateHistory
        this.expectedShipmentDeliveryDate = convertDate(shipment.expectedDeliveryDateTimestamp)
        this.shipmentStatus = shipment.status
        this.shipmentLocation = shipment.currentLocation
    }

    // Update with current information whenever observed object is updated
    override fun notify(subject: Observable) {
        if (subject is Shipment) {
            this.shipmentNotes = subject.notes
            this.shipmentUpdateHistory = subject.updateHistory
            this.expectedShipmentDeliveryDate = convertDate(subject.expectedDeliveryDateTimestamp)
            this.shipmentStatus = subject.status
            this.shipmentLocation = subject.currentLocation
        }
    }

    private fun convertDate(milliseconds: Long): String {
        val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS")
        return simpleDateFormat.format(milliseconds)
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