package com.pankaj.doors

data class Door(val number: Int, val status: Status)

enum class Status {
    OPEN, CLOSED
}