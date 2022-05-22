class StatusChangeStrategy: UpdateTypeStrategy {
    override fun getUpdate(update: List<String>, simulator: TrackingSimulator) {
        val id = update[1]
        val newUpdate = ShippingUpdate(update[2].toLong(), update.first())
        simulator.findShipment(id)?.addUpdate(newUpdate)
    }
}