import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class TrackerViewHelper(id: String) {
    var shipmentId = mutableStateOf(String)
    var shipmentNotes = mutableStateListOf<String>()
    var shipmentUpdateHistory = mutableStateListOf<String>()
    var expectedShipmentDeliveryDate = mutableStateListOf<String>()
    var shipmentStatus = mutableStateOf(String)
    init {
        this.trackShipment(id)
    }

    fun trackShipment(id: String) {
        // shipmentId =
    }

    fun stopTracking() {

    }
}