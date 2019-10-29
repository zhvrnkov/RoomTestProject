package com.example.room_test.entity_utils

import androidx.room.*
import com.example.room_test.Tables
import com.example.room_test.entities.Grade

interface GradeFields {
    val id: Long
    val title: String
    val rubricId: Long
    val score: Int
}

@Dao
internal abstract class GradeDao : BaseDao<Grade, Grade> {
    @Query("select * from ${Tables.grades}")
    abstract override fun getAll(): List<Grade>

    @Query("select * from ${Tables.grades} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<Grade>

    @Query("delete from ${Tables.grades} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(entity: Grade)

    @Insert
    abstract override fun insert(entity: Grade)
}

internal open class GradeUtils<GradeDTO : GradeFields>(
    dao: BaseDao<Grade, Grade>,
    private val dtoClass: Class<GradeDTO>
) : BaseUtils<GradeDTO, Grade, Grade, BaseDao<Grade, Grade>>()
{
    internal companion object {
        fun <GradeDTO : GradeFields>
                staticMapFields(fields: GradeDTO): Grade {
            return Grade(fields.id, fields.score, fields.title, fields.rubricId)
        }

        fun <GradeDTO : GradeFields>
                staticMapEntity(entity: Grade, dtoClass: Class<GradeDTO>): GradeDTO {
            return dtoClass.constructors.first().newInstance(
                entity.id,
                entity.title,
                entity.rubricId,
                entity.score
            ) as GradeDTO
        }
    }

    final override val realization = object: EntityUtilsRealization<
            Grade,
            Grade,
            GradeDTO,
            BaseDao<Grade, Grade>>
    {
        override val dao: BaseDao<Grade, Grade> = dao
        override fun mapEntity(entity: Grade) = staticMapEntity(
            entity, dtoClass)
        override fun mapFields(fields: GradeDTO) = staticMapFields(fields)
    }
}
