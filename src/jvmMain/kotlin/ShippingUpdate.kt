class ShippingUpdate(stamp: Long, status: String) {
    var previousStatus: String = ""
    var newStatus: String = status
    var timeStamp: Long = stamp
}