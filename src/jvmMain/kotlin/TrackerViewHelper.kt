import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class TrackerViewHelper {
    var shipmentId: String = ""
    var shipmentTotes = mutableStateListOf<String>()
    var shipmentUpdateHistory = mutableStateListOf<String>()
    var expectedShipmentDeliveryDate = mutableStateListOf<String>()
    var shipmentStatus = mutableStateOf(String)

    fun trackShipment(id: String) {

    }

    fun stopTracking() {

    }
}