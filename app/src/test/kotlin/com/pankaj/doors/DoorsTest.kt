package com.pankaj.doors

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DoorsTest {

    @Test
    fun `throws exception when number of doors is negative`() {
        val ex = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Doors(-1)
        }

        assertEquals(Messages.NEGATIVE_DOOR_NUMBER, ex.message)
    }

    @Test
    fun `returns empty list as status when no doors are present`() {
        val doors = Doors(0)

        assertEquals(emptyList<Door>(), doors.status())
    }

    @Test
    fun `returns door number and status as closed`() {
        val doors = Doors(1)

        assertEquals(listOf(Door(1, Status.CLOSED)), doors.status())
    }
}