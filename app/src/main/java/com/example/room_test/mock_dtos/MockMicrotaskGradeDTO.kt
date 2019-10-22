package com.example.room_test.mock_dtos

import com.example.room_test.MicrotaskGrade
import com.example.room_test.entity_utils.MicrotaskGradeFields

data class MockMicrotaskGradeDTO(
    override var id: Long = -1,
    override var isSynced: Boolean = false,
    override var lastUpdate: Long = -1,
    override var assessmentId: Long = -1,
    override var gradeId: Long = -1,
    override var microtaskId: Long = -1,
    override var studentId: Long = -1
) : MicrotaskGradeFields {
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