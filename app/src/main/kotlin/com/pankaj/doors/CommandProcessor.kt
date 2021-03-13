package com.pankaj.doors

class CommandProcessor(
    val reader: () -> String,
    val writer: (String) -> Unit,
    private val doorsFactory: DoorsFactory = DoorsFactory()
) {

    fun process() {
        val command = reader().toLowerCase()
        when {
            CREATE_DOORS_COMMAND.matches(command) -> processCreateDoorCommand(command)
            STATUS_COMMAND.matches(command) -> processStatusCommand(command)
        }
    }

    private fun processCreateDoorCommand(command: String) {
        CREATE_DOORS_COMMAND.matchEntire(command)?.let { result ->
            val (numberOfDoors) = result.destructured
            kotlin.runCatching { doors = doorsFactory.make(numberOfDoors.toInt()) }
                .onSuccess { writer("$numberOfDoors doors created") }
                .onFailure { ex -> writer(ex.message!!) }
        }
    }

    private fun processStatusCommand(command: String) {
        val header = "Door\tStatus"
        val rows = doors.status().joinToString("\n") { door -> "${door.number}\t${door.status}" }
        writer(header + "\n" + rows)
    }

    companion object {
        private val CREATE_DOORS_COMMAND = """^create-doors\s+(-?[0-9]+)$""".toRegex()
        private val STATUS_COMMAND = """^status$""".toRegex()
        private lateinit var doors: Doors
    }
}
