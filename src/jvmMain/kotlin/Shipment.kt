class Shipment(num: String) {
    var status: String = ""
    var id: String = num
    var notes = arrayListOf<String>()
        private set
    var updateHistory = arrayListOf<ShippingUpdate>()
        private set
    var expectedDeliveryDateTimestamp: Long = 0
    var currentLocation: String = ""

    fun addNote(note: String) {
        notes.add(note)
    }

    fun addUpdate(update: ShippingUpdate) {
        if (updateHistory.isEmpty() == false) {
            val previousStatus = updateHistory.first().newStatus
            update.previousStatus = previousStatus
        }

        updateHistory.add(update)
        this.status = update.newStatus
    }

}