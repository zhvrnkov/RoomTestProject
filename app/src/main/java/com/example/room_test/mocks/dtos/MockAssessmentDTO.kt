package com.example.room_test.mock_dtos

import com.example.room_test.entities.Assessment
import com.example.room_test.entities.AssessmentWithRelations
import com.example.room_test.entity_utils.AssessmentFields

data class MockAssessmentDTO(
    override val id: Long,
    override val date: Long ,
    override val isAddedToServer: Boolean,
    override val isSynced: Boolean,
    override val schooldId: Long,
    override val instructorId: Long,
    override val rubricId: Long,
    override val microtaskGrades: List<MockMicrotaskGradeDTO>,
    override val studentIds: List<Long>
) : AssessmentFields<MockMicrotaskGradeDTO> {
    companion object {
        fun new() = MockAssessmentDTO(
            id = Long.newId(),
            date = Long.newId(),
            isAddedToServer = true,
            isSynced = true,
            schooldId = Long.newId(),
            instructorId = Long.newId(),
            rubricId = Long.newId(),
            microtaskGrades = emptyList(),
            studentIds = listOf(Long.newId())
        )
    }

    override fun equals(other: Any?): Boolean = when(other) {
        is Assessment -> {
            listOf(
                id == other.id,
                date == other.date,
                isAddedToServer == other.isAddedToServer,
                isSynced == other.isSynced,
                schooldId == other.schooldId,
                instructorId == other.instructorId,
                rubricId == other.rubricId,
                studentIds == other.studentIds
            ).all { it }
        }
        is AssessmentWithRelations -> {
            listOf(
                this.equals(other.assessment),
                this.microtaskGrades == other.microtaskGrades
            ).all { it }
        }
        else -> super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}