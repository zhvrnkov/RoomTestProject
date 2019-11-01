package com.example.room_test.entity_utils

import androidx.room.*
import com.example.room_test.Tables
import com.example.room_test.entities.*
import com.example.room_test.entities.Grade
import com.example.room_test.entities.Rubric
import com.example.room_test.entities.RubricWithRelations

interface RubricFields<
        GradeDTO : GradeFields,
        MicrotaskDTO : MicrotaskFields,
        SkillSetDTO : SkillSetFields<MicrotaskDTO>>
{
    val id: Long
    val title: String
    val grades: List<GradeDTO>
    val skillSets: List<SkillSetDTO>
}

@Dao
internal abstract class RubricDao : BaseDao<Rubric, RubricWithRelations> {
    @Query("select * from ${Tables.rubrics}")
    abstract override fun getAll(): List<RubricWithRelations>

    @Query("select * from ${Tables.rubrics} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<RubricWithRelations>

    @Query("delete from ${Tables.rubrics} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(vararg entity: Rubric)

    @Insert
    abstract override fun insert(vararg entity: Rubric)
}

internal open class RubricUtils<
        GradeDTO : GradeFields,
        MicrotaskDTO : MicrotaskFields,
        SkillSetDTO : SkillSetFields<MicrotaskDTO>,
        RubricDTO : RubricFields<GradeDTO, MicrotaskDTO, SkillSetDTO>>
(
    dao: BaseDao<Rubric, RubricWithRelations>,
    private val dtoClass: Class<RubricDTO>,
    private val gradeDtoClass: Class<GradeDTO>,
    private val skillSetDtoClass: Class<SkillSetDTO>,
    private val microtaskDtoClass: Class<MicrotaskDTO>,
    private val getSkillSets: ((TypeOfGet) -> List<SkillSetDTO>)
) : BaseUtils<
        RubricDTO,
        Rubric,
        RubricWithRelations,
        BaseDao<Rubric, RubricWithRelations>>()
{
    internal companion object {
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
            skillSets: List<SkillSetDTO>,
            dtoClass: Class<RubricDTO>,
            gradeDtoClass: Class<GradeDTO>
        ): RubricDTO {
            return dtoClass.constructors.first().newInstance(
                entity.rubric.id,
                entity.rubric.title,
                entity.grades.map(GradeUtils.staticMapEntity(gradeDtoClass)),
                skillSets
            ) as RubricDTO
        }
    }

    override val realization = object: EntityUtilsRealization<
            Rubric,
            RubricWithRelations,
            RubricDTO,
            BaseDao<Rubric, RubricWithRelations>>
    {
        override val dao = dao
        override fun mapFields(vararg fields: RubricDTO) = fields.map(::staticMapFields).toTypedArray()
        override fun mapEntities(entities: List<RubricWithRelations>) = entities.map {
            val skillSets = getSkillSets(
                TypeOfGet.Certain(it.skillSets.map { s -> s.id }))
            staticMapEntity(it, skillSets, dtoClass, gradeDtoClass)
        }
    }

}
