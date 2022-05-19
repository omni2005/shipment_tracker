interface UpdateTypeStrategy {
    fun getUpdate(update: List<String>, simulator: TrackingSimulator)
}