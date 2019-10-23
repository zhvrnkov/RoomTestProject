package com.example.room_test.entities

import androidx.room.*

@Entity(
    tableName = "students",
    indices = [Index("name", "email")],
    foreignKeys = [
        ForeignKey(
            entity = Instructor::class,
            parentColumns = ["id"],
            childColumns = ["instructor_id"],
            onDelete = ForeignKey.CASCADE)]
)
data class Student(
    @PrimaryKey val id: Long,
    val name: String,
    val email: String,
    val level: String,
    @ColumnInfo(name = "logbook_pass")
    val logbookPass: String,
    @ColumnInfo(name = "qualified_days")
    val qualifiedDays: Int,
    val rank: String,

    @ColumnInfo(name = "instructor_id")
    val instructorId: Long,
    @ColumnInfo(name = "assessment_ids")
    val assessmentIds: List<Long>,
    @ColumnInfo(name = "microtask_grade_ids")
    val microtaskGradeIds: List<Long>
)