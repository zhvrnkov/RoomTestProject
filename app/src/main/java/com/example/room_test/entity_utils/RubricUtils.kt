package com.example.room_test.entity_utils

import androidx.room.*
import com.example.room_test.Rubric
import com.example.room_test.RubricWithRelations
import com.example.room_test.SkillSet

interface RubricFields<GradeDTO : GradeFields> {
    var id: Long
    var title: String
    var grades: List<GradeDTO>
    var skillSets: List<SkillSet>
}

@Dao
abstract class RubricDao : BaseDao<Rubric, RubricWithRelations> {
    override fun get(ids: List<Long>): List<RubricWithRelations> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(entity: Rubric) = pUpdate(entity)
    override fun insert(entity: Rubric) = pInsert(entity)

    @Query("delete from rubrics where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: Rubric)

    @Insert
    abstract fun pInsert(entity: Rubric)

    @Query("select * from rubrics where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<RubricWithRelations>
}

open class RubricUtils<GradeDTO : GradeFields, RubricDTO : RubricFields<GradeDTO>>(
    override val dao: RubricDao,
    private val dtoClass: Class<RubricDTO>,
    private val gradeDtoClass: Class<GradeDTO>
) : BaseUtils<RubricDTO, Rubric, RubricWithRelations, RubricDao>()
{
    companion object {
        fun <GradeDTO : GradeFields, RubricDTO : RubricFields<GradeDTO>>
                staticMapFields(fields: RubricDTO): Rubric {
            return Rubric(fields.id, fields.title)
        }

        fun <GradeDTO : GradeFields, RubricDTO : RubricFields<GradeDTO>> staticMapEntity(
            entity: RubricWithRelations,
            dtoClass: Class<RubricDTO>,
            gradeDtoClass: Class<GradeDTO>
        ): RubricDTO {
            val fields = dtoClass.newInstance()
            fields.id = entity.rubric.id
            fields.title = entity.rubric.title
            fields.grades = entity.grades.map { GradeUtils.staticMapEntity(it, gradeDtoClass) }
            //fields.skillSets = entity.skillSets.map { SkillSetUtils.staticMapEntity(it, )}

            return fields
        }
    }

    override fun mapFields(fields: RubricDTO): Rubric = staticMapFields(fields)
    override fun mapEntity(entity: RubricWithRelations): RubricDTO = staticMapEntity(entity, dtoClass, gradeDtoClass)
}
