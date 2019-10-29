package com.example.room_test.mocks.dtos

import com.example.room_test.entities.MicrotaskGrade
import com.example.room_test.entity_utils.MicrotaskGradeFields
import com.example.room_test.newId

data class MockMicrotaskGradeDTO(
    override val id: Long,
    override val isSynced: Boolean = false,
    override val lastUpdate: Long = -1,
    override val assessmentId: Long = -1,
    override val gradeId: Long = -1,
    override val microtaskId: Long = -1,
    override val studentId: Long = -1
) : MicrotaskGradeFields {
    companion object {
        fun new(assessmentId: Long) = MockMicrotaskGradeDTO(
            id = Long.newId(),
            isSynced = true,
            lastUpdate = Long.newId(),
            assessmentId = assessmentId,
            gradeId = Long.newId(),
            microtaskId = Long.newId(),
            studentId = Long.newId()
        )
    }

    override fun equals(other: Any?): Boolean = when(other) {
        is MicrotaskGrade -> {
            listOf(
                id == other.id,
                isSynced == other.isSynced,
                lastUpdate == other.lastUpdate,
                assessmentId == other.assessmentId,
                gradeId == other.gradeId,
                microtaskId == other.microtaskId,
                studentId == other.studentId
            ).all { it }
        }
        else -> super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}