package com.example.room_test.entity_utils

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
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
    override val tableName: String
        get() = Tables.microtasks

    @Query("select * from ${Tables.microtasks}")
    abstract override fun getAll(): List<Microtask>

    @Query("select * from ${Tables.microtasks} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<Microtask>

    @Query("delete from ${Tables.microtasks} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(vararg entity: Microtask)

    @Insert
    abstract override fun insert(vararg entity: Microtask)

    @RawQuery
    abstract override fun pget(query: SupportSQLiteQuery): List<Microtask>
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
            dtoClass: Class<MicrotaskDTO>
        ): (Microtask) -> MicrotaskDTO = {
            dtoClass.constructors.first().newInstance(
                it.id,
                it.title,
                it.content,
                it.skillSetId
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

        override fun mapFields(vararg fields: MicrotaskDTO) = fields.map(::staticMapFields).toTypedArray()
        override fun mapEntities(entities: List<Microtask>) = entities.map(staticMapEntity(dtoClass))
    }
}