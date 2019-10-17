package com.example.room_test

import androidx.room.*
import com.example.room_test.entity_utils.GradeDao
import com.example.room_test.entity_utils.RubricDao

@Entity(tableName = "rubrics")
data class Rubric(
    @PrimaryKey val id: Long,
    val title: String
    //@ColumnInfo(name = "grade_ids") var gradeIds: List<Long> = emptyList()
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


@Database(entities = [Rubric::class, Grade::class, SkillSet::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rubricDao(): RubricDao
    abstract fun gradeDao(): GradeDao
}