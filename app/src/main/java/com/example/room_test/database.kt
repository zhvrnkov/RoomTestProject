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
    indices = [Index("rubric_id", "rubric_id")],
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

data class RubricWithRelations(
    @Embedded
    val rubric: Rubric,

    @Relation(
        parentColumn = "id",
        entityColumn = "rubric_id",
        entity = Grade::class
    )
    var grades: List<Grade> = emptyList()
)


@Database(entities = [Rubric::class, Grade::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rubricDao(): RubricDao
    abstract fun gradeDao(): GradeDao
}