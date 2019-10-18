package com.example.room_test.entity_utils

import androidx.room.*
import com.example.room_test.Rubric
import com.example.room_test.RubricWithRelations
import com.example.room_test.SkillSet
import com.example.room_test.SkillSetWithRelations

interface RubricFields<
        GradeDTO : GradeFields,
        MicrotaskDTO : MicrotaskFields,
        SkillSetDTO : SkillSetFields<MicrotaskDTO>>
{
    var id: Long
    var title: String
    var grades: List<GradeDTO>
    var skillSets: List<SkillSetDTO>
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

open class RubricUtils<
        GradeDTO : GradeFields,
        MicrotaskDTO : MicrotaskFields,
        SkillSetDTO : SkillSetFields<MicrotaskDTO>,
        RubricDTO : RubricFields<GradeDTO, MicrotaskDTO, SkillSetDTO>>
(
    override val dao: BaseDao<Rubric, RubricWithRelations>,
    private val dtoClass: Class<RubricDTO>,
    private val gradeDtoClass: Class<GradeDTO>,
    private val skillSetDtoClass: Class<SkillSetDTO>,
    private val microtaskDtoClass: Class<MicrotaskDTO>
) : BaseUtils<RubricDTO, Rubric, RubricWithRelations, BaseDao<Rubric, RubricWithRelations>>()
{
    companion object {
        fun <GradeDTO : GradeFields,
             MicrotaskDTO : MicrotaskFields,
             SkillSetDTO : SkillSetFields<MicrotaskDTO>,
             RubricDTO : RubricFields<GradeDTO, MicrotaskDTO, SkillSetDTO>
        > staticMapFields(fields: RubricDTO): Rubric {
            return Rubric(fields.id, fields.title)
        }

        fun <GradeDTO : GradeFields,
             MicrotaskDTO : MicrotaskFields,
             SkillSetDTO : SkillSetFields<MicrotaskDTO>,
             RubricDTO : RubricFields<GradeDTO, MicrotaskDTO, SkillSetDTO>
        > staticMapEntity(
            entity: RubricWithRelations,
            skillSetsWithRelations: List<SkillSetWithRelations>,
            dtoClass: Class<RubricDTO>,
            gradeDtoClass: Class<GradeDTO>,
            skillSetDtoClass: Class<SkillSetDTO>,
            microtaskDtoClass: Class<MicrotaskDTO>
        ): RubricDTO {
            val fields = dtoClass.newInstance()
            fields.id = entity.rubric.id
            fields.title = entity.rubric.title
            fields.grades = entity.grades.map { GradeUtils.staticMapEntity(it, gradeDtoClass) }
            fields.skillSets = skillSetsWithRelations.map {
                SkillSetUtils.staticMapEntity(it, skillSetDtoClass, microtaskDtoClass)
            }

            return fields
        }
    }

    override fun mapFields(fields: RubricDTO): Rubric = staticMapFields(fields)
    override fun mapEntity(entity: RubricWithRelations): RubricDTO = staticMapEntity(
        entity, emptyList(), dtoClass, gradeDtoClass, skillSetDtoClass, microtaskDtoClass)
}

fun main() {

}