package com.example.room_test.entities

import androidx.room.*
import com.example.room_test.Tables

@Entity(tableName = Tables.schools)
internal data class School(
    @PrimaryKey val id: Long,
    val name: String
)

@Entity(
    tableName = Tables.instructors,
    indices = [Index("login_username", unique = true)],
    foreignKeys = [ForeignKey(
        entity = School::class,
        parentColumns = ["id"],
        childColumns = ["school_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
internal data class Instructor(
    @PrimaryKey val id: Long,
    val address: String,
    val address2: String,
    val avatar: String,
    val city: String,
    val country: String,
    val credentials: String,
    val depiction: String,
    val email: String,
    val firstName: String,
    val lang: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "login_username")
    val loginUsername: String,
    @ColumnInfo(name = "nauticed_status")
    val nauticedStatus: String,
    val phone: String,
    @ColumnInfo(name = "phone_student")
    val phoneStudent: String,
    val state: String,
    val zip: String,

    @ColumnInfo(name = "school_id")
    val schoolId: Long,
    @ColumnInfo(name = "assessment_ids")
    val assessmentIds: List<Long>,
    @ColumnInfo(name = "student_ids")
    val studentIds: List<Long>,

    @ColumnInfo(name = "grade_colors")
    val gradeColors: List<String>,
    val flags: List<String>,
    val fbid: Map<Int, Int>
)