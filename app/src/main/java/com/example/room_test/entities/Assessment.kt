package com.example.room_test.entities

import androidx.room.*

@Entity(
    tableName = "assessments",
    foreignKeys = [ForeignKey(
        entity = Instructor::class,
        parentColumns = ["id"],
        childColumns = ["instructor_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Assessment(
    @PrimaryKey val id: Long,
    val date: Long,
    val isAddedToServer: Boolean,
    val isSynced: Boolean,

    @ColumnInfo(name = "school_id")
    val schooldId: Long,
    @ColumnInfo(name = "instructor_id")
    val instructorId: Long,
    @ColumnInfo(name = "rubric_id")
    val rubricId: Long,
    @ColumnInfo(name = "student_ids")
    val studentIds: List<Long>
)

@Entity(
    tableName = "microtask_grades",
    indices = [Index("assessment_id", "microtask_id", "student_id")],
    foreignKeys = [
        ForeignKey(
            entity = Assessment::class,
            parentColumns = ["id"],
            childColumns = ["assessment_id"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["student_id"],
            onDelete = ForeignKey.CASCADE)
    ]
)
data class MicrotaskGrade(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean,
    @ColumnInfo(name = "last_update")
    val lastUpdate: Long,

    @ColumnInfo(name = "assessment_id")
    val assessmentId: Long,
    @ColumnInfo(name = "grade_id")
    val gradeId: Long,
    @ColumnInfo(name = "microtask_id")
    val microtaskId: Long,
    @ColumnInfo(name = "student_id")
    val studentId: Long
)

data class AssessmentWithRelations(
    @Embedded val assessment: Assessment,
    @Relation(
        parentColumn = "id",
        entityColumn = "assessment_id",
        entity = MicrotaskGrade::class
    )
    val microtaskGrades: List<MicrotaskGrade>
)