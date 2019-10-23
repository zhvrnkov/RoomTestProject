package com.example.room_test

import androidx.room.TypeConverter

class AppTypeConverter {
    @TypeConverter
    fun listOfLongsToString(value: List<Long>): String = value.toString()
    @TypeConverter
    fun stringToListOfLongs(value: String): List<Long> = value
        .substring(1 until (value.length - 1))
        .split(", ")
        .mapNotNull { it.toLongOrNull() }

    @TypeConverter
    fun listOfStringsToString(value: List<String>): String = value.toString()
    @TypeConverter
    fun stringToListOfStrings(value: String): List<String> = value
        .substring(1 until (value.length - 1))
        .split(", ")

    @TypeConverter
    fun mapIntIntToString(value: Map<Int, Int>): String = value.toString()
    @TypeConverter
    fun stringToMapIntInt(value: String): Map<Int, Int> = value
        .substring(1 until value.length - 1)
        .split(", ")
        .mapNotNull {
            val t = it.split("=")
            val fst = t.firstOrNull()?.toIntOrNull()
            val snd = t.lastOrNull()?.toIntOrNull()
            val output = if (fst == null || snd == null) {
                null
            } else { Pair(fst, snd) }

            output
        }.toMap()
}