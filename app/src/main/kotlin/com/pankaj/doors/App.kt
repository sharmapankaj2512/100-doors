/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.pankaj.doors

class App(reader: () -> String, writer: (String) -> Unit) {
    private val processor = CommandProcessor(reader, writer)

    fun start() {
        while (true) {
            processor.process()
        }
    }
}

fun main() {
    println(App({ readLine()!! }, { println(it) }).start())
}