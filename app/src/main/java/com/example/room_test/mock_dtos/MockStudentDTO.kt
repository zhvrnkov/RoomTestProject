package com.example.room_test.mock_dtos

import com.example.room_test.entities.Student
import com.example.room_test.entity_utils.StudentFields

data class MockStudentDTO(
    override val id: Long,
    override val name: String = "",
    override val email: String = "",
    override val level: String = "",
    override val logbookPass: String = "",
    override val qualifiedDays: Int = -1,
    override val rank: String = "",
    override val instructorId: Long = -1,
    override val assessmentIds: List<Long> = emptyList(),
    override val microtaskGradeIds: List<Long> = emptyList()
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