class CreatedStrategy: UpdateTypeStrategy {
    override fun getUpdate(update: List<String>, simulator: TrackingSimulator) {
        val shipment = Shipment(update[1])
        val deliveryDate = update[2].toLong()
        val update = ShippingUpdate(update[2].toLong(), update.first())
        shipment.addUpdate(update)
        shipment.expectedDeliveryDateTimestamp = deliveryDate
        simulator.addShipment(shipment)
    }
}