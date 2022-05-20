class LocationStrategy: UpdateTypeStrategy {
    override fun getUpdate(update: List<String>, simulator: TrackingSimulator) {
        val id = update[1]
        val location = update[3]
        simulator.findShipment(id)?.currentLocation = location
    }
}