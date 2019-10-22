package com.example.room_test.entity_utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room_test.Assessment
import com.example.room_test.AssessmentWithRelations

interface AssessmentFields<MicrotaskGradeDTO : MicrotaskGradeFields> {
    var id: Long
    var date: Long
    var isAddedToServer: Boolean
    var isSynced: Boolean

    var schooldId: Long
    var instructorId: Long
    var rubricId: Long
    var microtaskGrades: List<MicrotaskGradeDTO>
    var studentIds: List<Long>
}

@Dao
abstract class AssessmentDao : BaseDao<Assessment, AssessmentWithRelations> {
    override fun get(ids: List<Long>): List<AssessmentWithRelations> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(entity: Assessment) = pUpdate(entity)
    override fun insert(entity: Assessment) = pInsert(entity)

    @Query("delete from assessments where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: Assessment)

    @Insert
    abstract fun pInsert(entity: Assessment)

    @Query("select * from assessments where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<AssessmentWithRelations>
}

open class AssessmentUtils
<MicrotaskGradeDTO : MicrotaskGradeFields, AssessmentDTO : AssessmentFields<MicrotaskGradeDTO>>(
    override val dao: BaseDao<Assessment, AssessmentWithRelations>,
    private val dtoClass: Class<AssessmentDTO>,
    private val microtaskGradeDtoClass: Class<MicrotaskGradeDTO>
) : BaseUtils<
        AssessmentDTO,
        Assessment,
        AssessmentWithRelations,
        BaseDao<Assessment,
                AssessmentWithRelations>
        >()
{
    companion object {
        fun <MicrotaskGradeDTO : MicrotaskGradeFields,
                AssessmentDTO : AssessmentFields<MicrotaskGradeDTO>>
                staticMapFields(fields: AssessmentDTO): Assessment {
            return Assessment(
                id = fields.id,
                date = fields.date,
                isAddedToServer = fields.isAddedToServer,
                isSynced = fields.isSynced,
                schooldId = fields.schooldId,
                instructorId = fields.instructorId,
                rubricId = fields.rubricId,
                studentIds = fields.studentIds
            )
        }

        fun <MicrotaskGradeDTO : MicrotaskGradeFields,
                AssessmentDTO : AssessmentFields<MicrotaskGradeDTO>> staticMapEntity(
            entity: AssessmentWithRelations,
            dtoClass: Class<AssessmentDTO>,
            microtaskGradeDtoClass: Class<MicrotaskGradeDTO>
        ): AssessmentDTO {
            val dto = dtoClass.newInstance()
            dto.id = entity.assessment.id
            dto.date = entity.assessment.date
            dto.isAddedToServer = entity.assessment.isAddedToServer
            dto.isSynced = entity.assessment.isSynced
            dto.schooldId = entity.assessment.schooldId
            dto.instructorId = entity.assessment.instructorId
            dto.rubricId = entity.assessment.rubricId
            dto.studentIds = entity.assessment.studentIds
            dto.microtaskGrades = entity.microtaskGrades.map {
                MicrotaskGradeUtils.staticMapEntity(it, microtaskGradeDtoClass)
            }

            return dto
        }
    }

    override fun mapFields(fields: AssessmentDTO) = staticMapFields(fields)
    override fun mapEntity(entity: AssessmentWithRelations) = staticMapEntity(
        entity, dtoClass, microtaskGradeDtoClass)

}