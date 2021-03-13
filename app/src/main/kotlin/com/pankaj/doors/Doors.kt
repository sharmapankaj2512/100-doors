package com.pankaj.doors

class Doors(numberOfDoors: Int) {
    val doors: List<Door> = (1..numberOfDoors).map { number -> Door(number, Status.CLOSED) }

    fun status(): List<Door> {
        return doors
    }

    init {
        require(numberOfDoors >= 0) { Messages.NEGATIVE_DOOR_NUMBER }
    }
}
