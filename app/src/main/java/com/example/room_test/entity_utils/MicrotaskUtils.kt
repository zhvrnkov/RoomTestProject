package com.example.room_test.entity_utils

import androidx.room.*
import com.example.room_test.Microtask

interface MicrotaskFields {
    var id: Long
    var title: String
    var content: String
    var skillSetId: Long
}

@Dao
abstract class MicrotaskDao : BaseDao<Microtask, Microtask> {
    override fun get(ids: List<Long>): List<Microtask> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(entity: Microtask) = pUpdate(entity)
    override fun insert(entity: Microtask) = pInsert(entity)

    @Query("delete from microtasks where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: Microtask)

    @Insert
    abstract fun pInsert(entity: Microtask)

    @Query("select * from microtasks where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<Microtask>
}

class MicrotaskUtils<MicrotaskDTO : MicrotaskFields>(
    override val dao: MicrotaskDao,
    private val dtoClass: Class<MicrotaskDTO>
) : BaseUtils<MicrotaskDTO, Microtask, Microtask, MicrotaskDao>()
{
    companion object {
        fun <MicrotaskDTO : MicrotaskFields> staticMapFields(fields: MicrotaskDTO): Microtask {
            return Microtask(fields.id, fields.title, fields.content, fields.skillSetId)
        }

        fun <MicrotaskDTO : MicrotaskFields> staticMapEntity(entity: Microtask, dtoClass: Class<MicrotaskDTO>): MicrotaskDTO {
            val fields = dtoClass.newInstance()
            fields.id = entity.id
            fields.title = entity.title
            fields.content = entity.content
            fields.skillSetId = entity.skillSetId

            return fields
        }
    }

    override fun mapFields(fields: MicrotaskDTO): Microtask = staticMapFields(fields)
    override fun mapEntity(entity: Microtask): MicrotaskDTO = staticMapEntity(entity, dtoClass)
}