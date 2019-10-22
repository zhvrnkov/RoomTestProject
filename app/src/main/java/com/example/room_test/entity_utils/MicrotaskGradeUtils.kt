package com.example.room_test.entity_utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room_test.Microtask
import com.example.room_test.MicrotaskGrade

interface MicrotaskGradeFields {
    var id: Long
    var isSynced: Boolean
    var lastUpdate: Long

    var assessmentId: Long
    var gradeId: Long
    var microtaskId: Long
    var studentId: Long
}

@Dao
abstract class MicrotaskGradeDao : BaseDao<MicrotaskGrade, MicrotaskGrade> {
    override fun get(ids: List<Long>): List<MicrotaskGrade> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(entity: MicrotaskGrade) = pUpdate(entity)
    override fun insert(entity: MicrotaskGrade) = pInsert(entity)

    @Query("delete from microtask_grades where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: MicrotaskGrade)

    @Insert
    abstract fun pInsert(entity: MicrotaskGrade)

    @Query("select * from microtask_grades where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<MicrotaskGrade>
}

open class MicrotaskGradeUtils<MicrotaskGradeDTO : MicrotaskGradeFields>(
    override val dao: BaseDao<MicrotaskGrade, MicrotaskGrade>,
    private val dtoClass: Class<MicrotaskGradeDTO>
) : BaseUtils<
        MicrotaskGradeDTO,
        MicrotaskGrade,
        MicrotaskGrade,
        BaseDao<MicrotaskGrade, MicrotaskGrade>
        >()
{
    companion object {
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
            val dto = dtoClass.newInstance()
            dto.id = entity.id
            dto.isSynced = entity.isSynced
            dto.lastUpdate = entity.lastUpdate
            dto.assessmentId = entity.assessmentId
            dto.gradeId = entity.gradeId
            dto.microtaskId = entity.microtaskId
            dto.studentId = entity.studentId

            return dto
        }
    }

    override fun mapFields(fields: MicrotaskGradeDTO) = staticMapFields(fields)
    override fun mapEntity(entity: MicrotaskGrade) = staticMapEntity(entity, dtoClass)
}