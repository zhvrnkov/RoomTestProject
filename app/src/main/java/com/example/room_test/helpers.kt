package com.example.room_test

import java.util.*

fun Long.Companion.newId(): Long = UUID.randomUUID().mostSignificantBits