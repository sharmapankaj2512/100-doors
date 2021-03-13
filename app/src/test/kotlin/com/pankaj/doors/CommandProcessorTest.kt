package com.pankaj.doors

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CommandProcessorTest {

    @Test
    fun `parses valid create door command`() {
        val reader = MockReader()
        val writer = MockWriter()
        val doors = mockk<Doors>()
        val doorsFactory = mockk<DoorsFactory>()
        val processor = CommandProcessor(reader::read, writer::write, doorsFactory)
        val capturedNumberOfSlots = slot<Int>()

        every { doorsFactory.make(capture(capturedNumberOfSlots)) } returns doors

        reader.createDoors(2)
        processor.process()

        assertEquals("2 doors created", writer.capturedOutput)
        assertEquals(2, capturedNumberOfSlots.captured)
    }

    @Test
    fun `reports error on create door command when the number of doors is negative`() {
        val reader = MockReader()
        val writer = MockWriter()
        val doorsFactory = mockk<DoorsFactory>()
        val processor = CommandProcessor(reader::read, writer::write, doorsFactory)

        every { doorsFactory.make(-1) } throws IllegalArgumentException(Messages.NEGATIVE_DOOR_NUMBER)

        reader.createDoors(-1)
        processor.process()

        assertEquals(Messages.NEGATIVE_DOOR_NUMBER, writer.capturedOutput)
    }

    @Test
    fun `parses valid status command`() {
        val reader = MockReader()
        val writer = MockWriter()
        val doors = mockk<Doors>()
        val doorFactory = mockk<DoorsFactory>()
        val processor = CommandProcessor(reader::read, writer::write, doorFactory)

        every { doorFactory.make(2) } returns doors
        every { doors.status() } returns listOf(Door(1, Status.CLOSED), Door(2, Status.CLOSED))

        reader.createDoors(2)
        reader.status()
        processor.process()
        processor.process()

        assertEquals("Door\tStatus\n1\tCLOSED\n2\tCLOSED", writer.capturedOutput)
    }
}