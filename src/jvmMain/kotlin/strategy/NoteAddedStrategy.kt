class NoteAddedStrategy: UpdateTypeStrategy {
    override fun getUpdate(update: List<String>, simulator: TrackingSimulator) {
        val id = update[1]
        val note = update[3]
        simulator.findShipment(id)?.addNote(note)
    }
}