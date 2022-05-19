class CreatedStrategy: UpdateTypeStrategy {
    override fun getUpdate(update: List<String>, simulator: TrackingSimulator) {
        val shipment = Shipment(update[1], update.first(), update[2].toLong())
        simulator.addShipment(shipment)
    }
}