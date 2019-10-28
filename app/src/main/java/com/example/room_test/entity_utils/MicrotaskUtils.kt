package com.example.room_test.entity_utils

import androidx.room.*
import com.example.room_test.Tables
import com.example.room_test.entities.Microtask

interface MicrotaskFields {
    val id: Long
    val title: String
    val content: String
    val skillSetId: Long
}

@Dao
internal abstract class MicrotaskDao : BaseDao<Microtask, Microtask> {
    override fun get(ids: List<Long>): List<Microtask> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(entity: Microtask) = pUpdate(entity)
    override fun insert(entity: Microtask) = pInsert(entity)

    @Query("delete from ${Tables.microtasks} where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: Microtask)

    @Insert
    abstract fun pInsert(entity: Microtask)

    @Query("select * from ${Tables.microtasks} where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<Microtask>
}

internal open class MicrotaskUtils<MicrotaskDTO : MicrotaskFields>(
    dao: BaseDao<Microtask, Microtask>,
    private val dtoClass: Class<MicrotaskDTO>
) : BaseUtils<MicrotaskDTO, Microtask, Microtask, BaseDao<Microtask, Microtask>>()
{
    internal companion object {
        fun <MicrotaskDTO : MicrotaskFields> staticMapFields(fields: MicrotaskDTO): Microtask {
            return Microtask(fields.id, fields.title, fields.content, fields.skillSetId)
        }

        fun <MicrotaskDTO : MicrotaskFields> staticMapEntity(
            entity: Microtask, dtoClass: Class<MicrotaskDTO>
        ): MicrotaskDTO {
            return dtoClass.constructors.first().newInstance(
                entity.id,
                entity.title,
                entity.content,
                entity.skillSetId
            ) as MicrotaskDTO
        }
    }

    override val realization = object: EntityUtilsRealization<
            Microtask,
            Microtask,
            MicrotaskDTO,
            BaseDao<Microtask, Microtask>>
    {
        override val dao = dao
        override fun mapFields(fields: MicrotaskDTO) = staticMapFields(fields)
        override fun mapEntity(entity: Microtask) = staticMapEntity(
            entity, dtoClass)
    }
}