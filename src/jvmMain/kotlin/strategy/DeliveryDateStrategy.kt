class DeliveryDateStrategy: UpdateTypeStrategy {
    override fun getUpdate(update: List<String>, simulator: TrackingSimulator) {
        val id = update[1]
        val newDeliveryTime = update[3]
        val newUpdate = ShippingUpdate(update[2].toLong(), update.first())
        simulator.findShipment(id)?.addUpdate(newUpdate)
        simulator.findShipment(id)?.expectedDeliveryDateTimestamp = newDeliveryTime.toLong()
    }
}