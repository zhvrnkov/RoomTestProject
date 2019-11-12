package com.example.room_test.entity_utils

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.room_test.Tables
import com.example.room_test.entities.School

interface SchoolFields {
    val id: Long
    val name: String
}

@Dao
internal abstract class SchoolDao : BaseDao<School, School> {
    override val tableName: String
        get() = Tables.schools

    @Query("select * from ${Tables.schools}")
    abstract override fun getAll(): List<School>

    @Query("select * from ${Tables.schools} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<School>

    @Query("delete from ${Tables.schools} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(vararg entity: School)

    @Insert
    abstract override fun insert(vararg entitiy: School)

    @RawQuery
    abstract override fun pget(query: SupportSQLiteQuery): List<School>
}

internal open class SchoolUtils<SchoolDTO : SchoolFields>(
    dao: BaseDao<School, School>,
    private val dtoClass: Class<SchoolDTO>
) : BaseUtils<SchoolDTO, School, School, BaseDao<School, School>>()
{
    internal companion object {
        fun <SchoolDTO : SchoolFields> staticMapFields(
            fields: SchoolDTO
        ): School {
            return School(
                fields.id, fields.name
            )
        }

        fun <SchoolDTO : SchoolFields> staticMapEntity(
            dtoClass: Class<SchoolDTO>
        ): (School) -> SchoolDTO = {
            dtoClass.constructors.first().newInstance(
                it.id,
                it.name
            ) as SchoolDTO
        }
    }

    final override val realization =
        object: EntityUtilsRealization<
                School,
                School,
                SchoolDTO,
                BaseDao<School, School>>
        {
            override val dao = dao
            override fun mapFields(vararg fields: SchoolDTO) = fields.map(::staticMapFields).toTypedArray()
            override fun mapEntities(entities: List<School>) = entities.map(staticMapEntity(dtoClass))
        }

}