import androidx.compose.runtime.mutableStateListOf

class Shipment(trackingNum: String): Observable {
    var status: String = ""
        set(value) {
            field = value
            notifyObservers()
        }
    var id: String = trackingNum
    var notes = mutableStateListOf<String>()
        private set
    var updateHistory = mutableStateListOf<ShippingUpdate>()
        private set
    var expectedDeliveryDateTimestamp: Long = 0
        set(value) {
            field = value
            notifyObservers()
        }
    var currentLocation: String = ""
        set(value) {
            field = value
            notifyObservers()
        }
    private val observers = mutableListOf<Observer>()

    override fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach { it.notify(this) }
    }

    fun addNote(note: String) {
        notes.add(note)
        notifyObservers()
    }

    fun addUpdate(update: ShippingUpdate) {
        if (updateHistory.isEmpty() == false) {
            val previousStatus = updateHistory.last().newStatus
            update.previousStatus = previousStatus
        }

        updateHistory.add(update)
        this.status = update.newStatus
        notifyObservers()
    }

}