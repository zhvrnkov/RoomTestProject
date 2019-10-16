package com.example.room_test.entity_utils

import androidx.room.*
import com.example.room_test.Grade

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

    @Query("delete from rubrics where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: Grade)

    @Insert
    abstract fun pInsert(entity: Grade)

    @Query("select * from grades where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<Grade>
}

class GradeUtils<GradeDTO : GradeFields>(
    override val dao: GradeDao,
    private val dtoClass: Class<GradeDTO>
) : BaseUtils<GradeDTO, Grade, Grade, GradeDao>()
{
    companion object {
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

    override fun mapFields(fields: GradeDTO): Grade {
        return staticMapFields(fields)
    }

    override fun mapEntity(entity: Grade): GradeDTO {
        return staticMapEntity(entity, dtoClass)
    }
}
