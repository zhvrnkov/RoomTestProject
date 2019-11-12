package com.example.room_test.entity_utils

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
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
    override val tableName: String
        get() = Tables.skillSets

    @Query("select * from ${Tables.skillSets}")
    abstract override fun getAll(): List<SkillSetWithRelations>

    @Query("select * from ${Tables.skillSets} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<SkillSetWithRelations>

    @Query("delete from ${Tables.skillSets} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(vararg entity: SkillSet)

    @Insert
    abstract override fun insert(vararg entity: SkillSet)

    @RawQuery
    abstract override fun pget(query: SupportSQLiteQuery): List<SkillSetWithRelations>
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
            dtoClass: Class<SkillSetDTO>,
            microtaskDtoClass: Class<MicrotaskDTO>
        ): (SkillSetWithRelations) -> SkillSetDTO = {
            dtoClass.constructors.first().newInstance(
                it.skillSet.id,
                it.skillSet.title,
                it.skillSet.rubricId,
                it.microtasks.map(MicrotaskUtils.staticMapEntity(microtaskDtoClass))
            ) as SkillSetDTO
        }
    }

    final override val realization =
        object: EntityUtilsRealization<
            SkillSet,
            SkillSetWithRelations,
            SkillSetDTO,
            BaseDao<SkillSet, SkillSetWithRelations>>
        {
            override val dao: BaseDao<SkillSet, SkillSetWithRelations> = dao
            override fun mapEntities(entities: List<SkillSetWithRelations>) = entities.map(
                staticMapEntity(dtoClass, microtaskDtoClass))

            override fun mapFields(vararg fields: SkillSetDTO) = fields.map(::staticMapFields).toTypedArray()
        }
}