package com.example.room_test.mock_dtos

import com.example.room_test.entities.Student
import com.example.room_test.entity_utils.StudentFields

data class MockStudentDTO(
    override var id: Long = -1,
    override var name: String = "",
    override var email: String = "",
    override var level: String = "",
    override var logbookPass: String = "",
    override var qualifiedDays: Int = -1,
    override var rank: String = "",
    override var instructorId: Long = -1,
    override var assessmentIds: List<Long> = emptyList(),
    override var microtaskGradeIds: List<Long> = emptyList()
) : StudentFields {
    companion object {
        fun new() = MockStudentDTO(
            id = Long.newId(),
            instructorId = Long.newId()
        )
    }

    override fun equals(other: Any?): Boolean = when(other) {
        is Student -> {
            listOf(
                id == other.id,
                name == other.name,
                email == other.email,
                level == other.level,
                logbookPass == other.logbookPass,
                qualifiedDays == other.qualifiedDays,
                rank == other.rank,
                instructorId == other.instructorId,
                assessmentIds == other.assessmentIds,
                microtaskGradeIds == other.microtaskGradeIds
            ).all { it }
        }
        else -> super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}