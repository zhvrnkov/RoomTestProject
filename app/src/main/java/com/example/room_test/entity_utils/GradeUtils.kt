package com.example.room_test.entity_utils

import androidx.room.*
import com.example.room_test.Tables
import com.example.room_test.entities.Grade

interface GradeFields {
    var id: Long
    var title: String
    var rubricId: Long
    var score: Int
}

@Dao
abstract class GradeDao : BaseDao<Grade, Grade> {
    override fun get(ids: List<Long>): List<Grade> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(entity: Grade) = pUpdate(entity)
    override fun insert(entity: Grade) = pInsert(entity)

    @Query("delete from ${Tables.grades} where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: Grade)

    @Insert
    abstract fun pInsert(entity: Grade)

    @Query("select * from ${Tables.grades} where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<Grade>
}

open class GradeUtils<GradeDTO : GradeFields>(
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
            val fields = dtoClass.newInstance()
            fields.id = entity.id
            fields.title = entity.title
            fields.rubricId = entity.rubricId
            fields.score = entity.score

            return fields
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
