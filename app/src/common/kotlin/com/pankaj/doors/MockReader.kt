package com.pankaj.doors

class MockReader {
    var index = 0
    var commands = emptyList<String>()

    private fun addCommand(command: String) {
        commands = commands.plus(command)
    }

    fun read(): String {
        if (commands.isEmpty())
            return ""
        if (index >= commands.size)
            return ""
        return commands[index++]
    }

    fun createDoors(numberOfDoors: Int) {
        addCommand("create-doors $numberOfDoors")
    }

    fun status() {
        addCommand("status")
    }
}