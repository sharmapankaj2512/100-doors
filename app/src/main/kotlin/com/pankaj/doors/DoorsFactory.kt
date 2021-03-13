package com.pankaj.doors

class DoorsFactory {
    fun make(numberOfDoors: Int): Doors {
        return Doors(numberOfDoors)
    }
}
