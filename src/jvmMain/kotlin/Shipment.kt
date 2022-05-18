class Shipment {
    var status: String = ""
    var id: String = ""
    var notes = arrayListOf<String>()
        private set
    var updateHistory = arrayListOf<ShippingUpdate>()
        private set
    var expectedDeliveryDateTimestamp: Long = 0
    var currentLocation: String = ""

    fun addNote(note: String) {

    }

    fun addUpdate(update: ShippingUpdate) {

    }

}