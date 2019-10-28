package com.example.room_test.mock_dtos

import com.example.room_test.entities.Grade
import com.example.room_test.entity_utils.GradeFields

data class MockGradeDTO(
    override val id: Long,
    override val title: String,
    override val rubricId: Long,
    override val score: Int
) : GradeFields {
    companion object {
        fun new(rubricId: Long): MockGradeDTO = MockGradeDTO(
            id = Long.newId(), rubricId = rubricId,
            title = "Foo", score = 13
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