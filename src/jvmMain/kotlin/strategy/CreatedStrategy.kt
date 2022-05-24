class CreatedStrategy: UpdateTypeStrategy {
    override fun getUpdate(update: List<String>, simulator: TrackingSimulator) {
        val shipment = Shipment(update[1])
        val timestamp = update[2].toLong()
        val newUpdate = ShippingUpdate(timestamp, update.first())
        shipment.addUpdate(newUpdate)
        simulator.addShipment(shipment)
    }
}