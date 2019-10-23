package com.example.room_test.mock_dtos

import com.example.room_test.entities.Grade
import com.example.room_test.entity_utils.GradeFields

data class MockGradeDTO(
    override var id: Long = -1,
    override var title: String = "Bad title",
    override var rubricId: Long = -1,
    override var score: Int = 0
) : GradeFields {
    companion object {
        fun new(rubricId: Long): MockGradeDTO = MockGradeDTO(
            id = Long.newId(), rubricId = rubricId
        )
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Grade -> {
                listOf(
                    id == other.id,
                    title == other.title,
                    rubricId == other.rubricId,
                    score == other.score
                ).all { it }
            }
            else -> super.equals(other)
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}