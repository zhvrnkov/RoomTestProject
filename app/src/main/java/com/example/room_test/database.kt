package com.example.room_test

import androidx.room.*
import com.example.room_test.entities.*
import com.example.room_test.entity_utils.*

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

@Database(
    entities = [
        School::class,
        Instructor::class,
        Rubric::class,
        Grade::class,
        SkillSet::class,
        Microtask::class,
        Assessment::class,
        MicrotaskGrade::class,
        Student::class
    ],
    version = 1
)
@TypeConverters(AppTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun schooldDao(): SchoolDao
    abstract fun instructorDao(): InstructorDao
    abstract fun rubricDao(): RubricDao
    abstract fun gradeDao(): GradeDao
    abstract fun skillSetDao(): SkillSetDao
    abstract fun microtaskDao(): MicrotaskDao
    abstract fun assessmentDao(): AssessmentDao
    abstract fun microtaskGradeDao(): MicrotaskGradeDao
    abstract fun studentDao(): StudentDao
}
