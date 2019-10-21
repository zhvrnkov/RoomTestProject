package com.example.room_test

import androidx.room.*
import com.example.room_test.entity_utils.GradeDao
import com.example.room_test.entity_utils.RubricDao

@Entity(tableName = "rubrics")
data class Rubric(
    @PrimaryKey val id: Long,
    val title: String
)

@Entity(
    tableName = "grades",
    indices = [Index("rubric_id", "title", unique = true)],
    foreignKeys = [ForeignKey(
        entity = Rubric::class,
        parentColumns = ["id"],
        childColumns = ["rubric_id"],
        onDelete = ForeignKey.CASCADE)
    ]
)
data class Grade(
    @PrimaryKey val id: Long,
    val score: Int,
    val title: String,
    @ColumnInfo(name = "rubric_id") val rubricId: Long
)

@Entity(
    tableName = "skill_sets",
    indices = [Index("rubric_id", "title", unique = true)],
    foreignKeys = [ForeignKey(
        entity = Rubric::class,
        parentColumns = ["id"],
        childColumns = ["rubric_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SkillSet(
    @PrimaryKey val id: Long,
    val title: String,
    @ColumnInfo(name = "rubric_id") val rubricId: Long
)

@Entity(
    tableName = "microtasks",
    indices = [Index("skill_set_id", "title", unique = true)],
    foreignKeys = [ForeignKey(
        entity = SkillSet::class,
        parentColumns = ["id"],
        childColumns = ["skill_set_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Microtask(
    @PrimaryKey val id: Long,
    val title: String,
    val content: String,
    @ColumnInfo(name = "skill_set_id") val skillSetId: Long
)

data class SkillSetWithRelations(
    @Embedded
    val skillSet: SkillSet,

    @Relation(
        parentColumn = "id",
        entityColumn = "skill_set_id",
        entity = Microtask::class
    )
    val microtasks: List<Microtask>
)

data class RubricWithRelations(
    @Embedded
    val rubric: Rubric,

    @Relation(
        parentColumn = "id",
        entityColumn = "rubric_id",
        entity = Grade::class
    )
    val grades: List<Grade>,

    @Relation(
        parentColumn = "id",
        entityColumn = "rubric_id",
        entity = SkillSet::class
    )
    val skillSets: List<SkillSet>
)

@Entity(tableName = "schools")
data class School(
    val id: Long,
    val name: String
)

@Entity(
    tableName = "instructors",
    indices = [Index("loginUsername", "id")],
    foreignKeys = [ForeignKey(
        entity = School::class,
        parentColumns = ["id"],
        childColumns = ["school_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Instructor(
    val id: Long,
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
    val id: Long,
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
    val id: Long,
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
    val id: Long,
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
    val microtaskGrades: List<Microtask>
)

@Database(entities = [Rubric::class, Grade::class, SkillSet::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rubricDao(): RubricDao
    abstract fun gradeDao(): GradeDao
}