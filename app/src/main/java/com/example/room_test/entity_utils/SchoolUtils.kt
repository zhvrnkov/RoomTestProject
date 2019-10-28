package com.example.room_test.entity_utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room_test.Tables
import com.example.room_test.entities.School

interface SchoolFields {
    val id: Long
    val name: String
}

@Dao
internal abstract class SchoolDao : BaseDao<School, School> {
    override fun get(ids: List<Long>): List<School> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(entity: School) = pUpdate(entity)
    override fun insert(entity: School) = pInsert(entity)

    @Query("delete from ${Tables.schools} where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: School)

    @Insert
    abstract fun pInsert(entity: School)

    @Query("select * from ${Tables.schools} where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<School>
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
            entity: School,
            dtoClass: Class<SchoolDTO>
        ): SchoolDTO {
            return dtoClass.constructors.first().newInstance(
                entity.id,
                entity.name
            ) as SchoolDTO
        }
    }

    final override val realization = object: EntityUtilsRealization<
            School,
            School,
            SchoolDTO,
            BaseDao<School, School>>
    {
        override val dao = dao
        override fun mapFields(fields: SchoolDTO) = staticMapFields(fields)
        override fun mapEntity(entity: School) = staticMapEntity(
            entity, dtoClass)
    }

}