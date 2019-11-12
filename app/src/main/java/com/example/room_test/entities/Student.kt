package com.example.room_test.entities

import androidx.room.*
import com.example.room_test.Tables

@Entity(
    tableName = Tables.students,
    indices = [Index("name", "email", unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = Instructor::class,
            parentColumns = ["id"],
            childColumns = ["instructor_id"],
            onDelete = ForeignKey.CASCADE)
    ]
)
internal data class Student(
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
    val instructorId: Long
)

internal data class StudentWithRelations(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "id",
        entityColumn = "student_id",
        entity = MicrotaskGrade::class,
        projection = ["id"]
    ) val microtaskGradeIds: List<Long>
)