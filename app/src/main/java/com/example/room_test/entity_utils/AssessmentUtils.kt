package com.example.room_test.entity_utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room_test.Tables
import com.example.room_test.entities.Assessment
import com.example.room_test.entities.AssessmentWithRelations

interface AssessmentFields<MicrotaskGradeDTO : MicrotaskGradeFields> {
    val id: Long
    val date: Long
    val isAddedToServer: Boolean
    val isSynced: Boolean
    val schooldId: Long
    val instructorId: Long
    val rubricId: Long
    val microtaskGrades: List<MicrotaskGradeDTO>
    val studentIds: List<Long>
}

@Dao
internal abstract class AssessmentDao : BaseDao<Assessment, AssessmentWithRelations> {
    @Query("select * from ${Tables.assessments}")
    abstract override fun getAll(): List<AssessmentWithRelations>

    @Query("select * from ${Tables.assessments} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<AssessmentWithRelations>

    @Query("delete from ${Tables.assessments} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(entity: Assessment)

    @Insert
    abstract override fun insert(entity: Assessment)
}

internal open class AssessmentUtils
<MicrotaskGradeDTO : MicrotaskGradeFields, AssessmentDTO : AssessmentFields<MicrotaskGradeDTO>>(
    dao: BaseDao<Assessment, AssessmentWithRelations>,
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
    internal companion object {
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
            return dtoClass.constructors.first().newInstance(
                entity.assessment.id,
                entity.assessment.date,
                entity.assessment.isAddedToServer,
                entity.assessment.isSynced,
                entity.assessment.schooldId,
                entity.assessment.instructorId,
                entity.assessment.rubricId,
                entity.microtaskGrades.map {
                    MicrotaskGradeUtils.staticMapEntity(it, microtaskGradeDtoClass)
                },
                entity.assessment.studentIds
            ) as AssessmentDTO
        }
    }

    final override val realization = object : EntityUtilsRealization<
        Assessment,
        AssessmentWithRelations,
        AssessmentDTO,
        BaseDao<Assessment, AssessmentWithRelations>>
    {
            override val dao = dao
            override fun mapEntity(entity: AssessmentWithRelations) = staticMapEntity(
                entity, dtoClass, microtaskGradeDtoClass)

            override fun mapFields(fields: AssessmentDTO) = staticMapFields(fields)
    }
}