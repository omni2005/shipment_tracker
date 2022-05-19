class Shipment(num: String, state: String, date: Long) {
    var status: String = state
    var id: String = num
    var notes = arrayListOf<String>()
        private set
    var updateHistory = arrayListOf<ShippingUpdate>()
        private set
    var expectedDeliveryDateTimestamp: Long = date
    var currentLocation: String = ""

    fun addNote(note: String) {

    }

    fun addUpdate(update: ShippingUpdate) {

    }

}