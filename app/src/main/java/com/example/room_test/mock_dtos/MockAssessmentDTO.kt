package com.example.room_test.mock_dtos

import com.example.room_test.Assessment
import com.example.room_test.AssessmentWithRelations
import com.example.room_test.entity_utils.AssessmentFields

data class MockAssessmentDTO(
    override var id: Long = -1,
    override var date: Long = -1,
    override var isAddedToServer: Boolean = false,
    override var isSynced: Boolean = false,
    override var schooldId: Long = -1,
    override var instructorId: Long = -1,
    override var rubricId: Long = -1,
    override var microtaskGrades: List<MockMicrotaskGradeDTO> = emptyList(),
    override var studentIds: List<Long> = emptyList()
) : AssessmentFields<MockMicrotaskGradeDTO> {
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