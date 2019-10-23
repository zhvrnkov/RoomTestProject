package com.example.room_test

import org.junit.Test
import org.junit.Assert.*
import java.util.*

class AppConverterTest {
    private val converter = AppTypeConverter()
    private val uuid get() =  UUID.randomUUID()

    @Test
    fun stringToListOfLongs() {
        val list = (0..10)
            .map { uuid.mostSignificantBits }
        assertEquals(list, converter.stringToListOfLongs(list.toString()))
    }

    @Test
    fun stringToListOfStrings() {
        val list = (0..10)
            .map { uuid.toString() }
        assertEquals(list, converter.stringToListOfStrings(list.toString()))
    }

    @Test
    fun stringToMapIntInt() {
        val map = (0..10)
            .map { Pair(uuid.hashCode(), uuid.hashCode()) }
            .toMap()
        assertEquals(map, converter.stringToMapIntInt(map.toString()))
    }
}