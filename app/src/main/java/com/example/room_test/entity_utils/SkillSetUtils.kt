package com.example.room_test.entity_utils

import androidx.room.*
import com.example.room_test.Microtask
import com.example.room_test.SkillSet
import com.example.room_test.SkillSetWithRelations

interface SkillSetFields<MicrotaskDTO : MicrotaskFields> {
    var id: Long
    var title: String
    var rubricId: Long
    var microtasks: List<MicrotaskDTO>
}

@Dao
abstract class SkillSetDao : BaseDao<SkillSet, SkillSetWithRelations> {
    override fun get(ids: List<Long>): List<SkillSetWithRelations> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(entity: SkillSet) = pUpdate(entity)
    override fun insert(entity: SkillSet) = pInsert(entity)

    @Query("delete from skill_sets where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: SkillSet)

    @Insert
    abstract fun pInsert(entity: SkillSet)

    @Query("select * from skill_sets where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<SkillSetWithRelations>
}

class SkillSetUtils<MicrotaskDTO : MicrotaskFields, SkillSetDTO : SkillSetFields<MicrotaskDTO>>(
    override val dao: SkillSetDao,
    private val dtoClass: Class<SkillSetDTO>,
    private val microtaskDtoClass: Class<MicrotaskDTO>
) : BaseUtils<SkillSetDTO, SkillSet, SkillSetWithRelations, SkillSetDao>()
{
    companion object {
        fun <MicrotaskDTO : MicrotaskFields, SkillSetDTO : SkillSetFields<MicrotaskDTO>> staticMapFields(
            fields: SkillSetDTO): SkillSet {
            return SkillSet(fields.id, fields.title, fields.rubricId)
        }

        fun <MicrotaskDTO : MicrotaskFields, SkillSetDTO : SkillSetFields<MicrotaskDTO>> staticMapEntity(
            entity: SkillSetWithRelations,
            dtoClass: Class<SkillSetDTO>,
            microtaskDtoClass: Class<MicrotaskDTO>
        ): SkillSetDTO {
            val fields = dtoClass.newInstance()
            fields.id = entity.skillSet.id
            fields.title = entity.skillSet.title
            fields.rubricId = entity.skillSet.rubricId
            fields.microtasks = entity.microtasks.map { MicrotaskUtils.staticMapEntity(it, microtaskDtoClass) }

            return fields
        }
    }

    override fun mapFields(fields: SkillSetDTO): SkillSet = staticMapFields(fields)
    override fun mapEntity(entity: SkillSetWithRelations): SkillSetDTO = staticMapEntity(entity, dtoClass, microtaskDtoClass)
}