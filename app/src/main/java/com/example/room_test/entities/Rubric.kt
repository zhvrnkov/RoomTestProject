package com.example.room_test.entities

import androidx.room.*
import com.example.room_test.Tables

@Entity(tableName = "rubrics")
internal data class Rubric(
    @PrimaryKey val id: Long,
    val title: String
)

@Entity(
    tableName = Tables.grades,
    indices = [Index("rubric_id", "title", unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = Rubric::class,
            parentColumns = ["id"],
            childColumns = ["rubric_id"],
            onDelete = ForeignKey.CASCADE)
    ]
)
internal data class Grade(
    @PrimaryKey val id: Long,
    val score: Int,
    val title: String,
    @ColumnInfo(name = "rubric_id") val rubricId: Long
)

@Entity(
    tableName = Tables.skillSets,
    indices = [Index("rubric_id", "title", unique = true)],
    foreignKeys = [ForeignKey(
        entity = Rubric::class,
        parentColumns = ["id"],
        childColumns = ["rubric_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
internal data class SkillSet(
    @PrimaryKey val id: Long,
    val title: String,
    @ColumnInfo(name = "rubric_id") val rubricId: Long
)

@Entity(
    tableName = Tables.microtasks,
    indices = [Index("skill_set_id", "title", unique = true)],
    foreignKeys = [ForeignKey(
        entity = SkillSet::class,
        parentColumns = ["id"],
        childColumns = ["skill_set_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
internal data class Microtask(
    @PrimaryKey val id: Long,
    val title: String,
    val content: String,
    @ColumnInfo(name = "skill_set_id") val skillSetId: Long
)

internal data class SkillSetWithRelations(
    @Embedded
    val skillSet: SkillSet,

    @Relation(
        parentColumn = "id",
        entityColumn = "skill_set_id",
        entity = Microtask::class
    )
    val microtasks: List<Microtask>
)

internal data class RubricWithRelations(
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