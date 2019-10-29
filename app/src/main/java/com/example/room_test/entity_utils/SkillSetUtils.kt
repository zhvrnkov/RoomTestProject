package com.example.room_test.entity_utils

import androidx.room.*
import com.example.room_test.Tables
import com.example.room_test.entities.SkillSet
import com.example.room_test.entities.SkillSetWithRelations

interface SkillSetFields<MicrotaskDTO : MicrotaskFields> {
    val id: Long
    val title: String
    val rubricId: Long
    val microtasks: List<MicrotaskDTO>
}

@Dao
internal abstract class SkillSetDao : BaseDao<SkillSet, SkillSetWithRelations> {
    @Query("select * from ${Tables.skillSets}")
    abstract override fun getAll(): List<SkillSetWithRelations>

    @Query("select * from ${Tables.skillSets} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<SkillSetWithRelations>

    @Query("delete from ${Tables.skillSets} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(entity: SkillSet)

    @Insert
    abstract override fun insert(entity: SkillSet)
}

internal open class SkillSetUtils<
        MicrotaskDTO : MicrotaskFields,
        SkillSetDTO : SkillSetFields<MicrotaskDTO>>(
    dao: BaseDao<SkillSet, SkillSetWithRelations>,
    private val dtoClass: Class<SkillSetDTO>,
    private val microtaskDtoClass: Class<MicrotaskDTO>
) : BaseUtils<
        SkillSetDTO,
        SkillSet,
        SkillSetWithRelations,
        BaseDao<SkillSet, SkillSetWithRelations>>()
{
    internal companion object {
        fun <MicrotaskDTO : MicrotaskFields,
                SkillSetDTO : SkillSetFields<MicrotaskDTO>> staticMapFields(
            fields: SkillSetDTO
        ): SkillSet {
            return SkillSet(fields.id, fields.title, fields.rubricId)
        }

        fun <MicrotaskDTO : MicrotaskFields,
                SkillSetDTO : SkillSetFields<MicrotaskDTO>> staticMapEntity(
            entity: SkillSetWithRelations,
            dtoClass: Class<SkillSetDTO>,
            microtaskDtoClass: Class<MicrotaskDTO>
        ): SkillSetDTO {
            return dtoClass.constructors.first().newInstance(
                entity.skillSet.id,
                entity.skillSet.title,
                entity.skillSet.rubricId,
                entity.microtasks.map {
                    MicrotaskUtils.staticMapEntity(it, microtaskDtoClass)
                }
            ) as SkillSetDTO
        }
    }

    final override val realization = object: EntityUtilsRealization<
        SkillSet,
        SkillSetWithRelations,
        SkillSetDTO,
        BaseDao<SkillSet, SkillSetWithRelations>>
    {
        override val dao: BaseDao<SkillSet, SkillSetWithRelations> = dao
        override fun mapEntity(entity: SkillSetWithRelations) = staticMapEntity(
            entity, dtoClass, microtaskDtoClass)

        override fun mapFields(fields: SkillSetDTO) = staticMapFields(fields)
    }
}