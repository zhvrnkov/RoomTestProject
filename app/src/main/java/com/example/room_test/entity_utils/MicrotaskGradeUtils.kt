package com.example.room_test.entity_utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room_test.Tables
import com.example.room_test.entities.MicrotaskGrade

interface MicrotaskGradeFields {
    val id: Long
    val isSynced: Boolean
    val lastUpdate: Long
    val assessmentId: Long
    val gradeId: Long
    val microtaskId: Long
    val studentId: Long
}

@Dao
internal abstract class MicrotaskGradeDao : BaseDao<MicrotaskGrade, MicrotaskGrade> {
    @Query("select * from ${Tables.microtaskGrades}")
    abstract override fun getAll(): List<MicrotaskGrade>

    @Query("select * from ${Tables.microtaskGrades} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<MicrotaskGrade>

    @Query("delete from ${Tables.microtaskGrades} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(entity: MicrotaskGrade)

    @Insert
    abstract override fun insert(entity: MicrotaskGrade)
}

internal open class MicrotaskGradeUtils<MicrotaskGradeDTO : MicrotaskGradeFields>(
    dao: BaseDao<MicrotaskGrade, MicrotaskGrade>,
    private val dtoClass: Class<MicrotaskGradeDTO>
) : BaseUtils<
        MicrotaskGradeDTO,
        MicrotaskGrade,
        MicrotaskGrade,
        BaseDao<MicrotaskGrade, MicrotaskGrade>
        >()
{
    internal companion object {
        fun <MicrotaskGradeDTO : MicrotaskGradeFields> staticMapFields(
            fields: MicrotaskGradeDTO
        ): MicrotaskGrade {
            return MicrotaskGrade(
                id = fields.id,
                isSynced = fields.isSynced,
                lastUpdate = fields.lastUpdate,
                assessmentId = fields.assessmentId,
                gradeId = fields.gradeId,
                microtaskId = fields.microtaskId,
                studentId = fields.studentId
            )
        }

        fun <MicrotaskGradeDTO : MicrotaskGradeFields> staticMapEntity(
            entity: MicrotaskGrade,
            dtoClass: Class<MicrotaskGradeDTO>
        ): MicrotaskGradeDTO {
            return dtoClass.constructors.first().newInstance(
                entity.id,
                entity.isSynced,
                entity.lastUpdate,
                entity.assessmentId,
                entity.gradeId,
                entity.microtaskId,
                entity.studentId
            ) as MicrotaskGradeDTO
        }
    }

    final override val realization = object : EntityUtilsRealization<
            MicrotaskGrade,
            MicrotaskGrade,
            MicrotaskGradeDTO,
            BaseDao<MicrotaskGrade, MicrotaskGrade>>
    {
        override val dao = dao
        override fun mapEntity(entity: MicrotaskGrade) = staticMapEntity(
            entity, dtoClass)
        override fun mapFields(fields: MicrotaskGradeDTO) = staticMapFields(fields)
    }
}