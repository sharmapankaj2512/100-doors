package com.pankaj.doors

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.concurrent.TimeUnit

@Nested
class CreateDoorsFeature {
    private val mockReader = MockReader()
    private val mockWriter = MockWriter()
    private val app = TestApp(mockReader, mockWriter)

    @BeforeEach
    fun beforeEach() {
        app.start()
    }

    @AfterEach
    fun afterEach() {
        app.stop()
    }

    @Nested
    @DisplayName("Given that no doors are present")
    inner class GivenThatNoDoorsArePresent {

        @Nested
        @DisplayName("When create two doors request is received")
        inner class WhenCreateDoorsRequestIsReceived {

            @BeforeEach
            fun beforeEach() {
                mockReader.createDoors(2)
                mockReader.status()
            }

            @Test
            @DisplayName("Then the two doors are created and their initial state is set to closed")
            @Timeout(value = 2, unit = TimeUnit.SECONDS)
            fun thenCreateTwoDoorsAndSetTheirStateToClosed() {
                mockWriter.waitUntilProcessed(2)

                assertEquals("Door\tStatus\n1\tCLOSED\n2\tCLOSED", mockWriter.pop())
                assertEquals("2 doors created", mockWriter.pop())
            }
        }
    }
}