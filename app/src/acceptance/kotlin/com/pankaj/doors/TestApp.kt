package com.pankaj.doors

import kotlin.concurrent.thread

class TestApp(mockReader: MockReader, mockWriter: MockWriter) {
    private val app = App(mockReader::read, mockWriter::write)
    private lateinit var thread: Thread

    fun start() {
        thread = thread { app.start() }
    }

    fun stop() {
        thread.stop()
    }
}
