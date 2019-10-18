package com.example.room_test.mock_dtos

import java.util.*

fun Long.Companion.newId(): Long = UUID.randomUUID().mostSignificantBits