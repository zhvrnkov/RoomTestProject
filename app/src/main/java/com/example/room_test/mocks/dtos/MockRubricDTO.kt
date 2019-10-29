package com.example.room_test.mocks.dtos

import com.example.room_test.entities.Rubric
import com.example.room_test.entities.RubricWithRelations
import com.example.room_test.entity_utils.RubricFields

data class MockRubricDTO(
    override val id: Long,
    override val title: String = "",
    override val grades: List<MockGradeDTO> = emptyList(),
    override val skillSets: List<MockSkillSetDTO> = emptyList()
) : RubricFields<MockGradeDTO, MockMicrotaskDTO, MockSkillSetDTO> {
    companion object {
        fun new(id: Long, grades: List<MockGradeDTO>): MockRubricDTO {
            return MockRubricDTO(
                id = id,
                title = "Lorem",
                grades = grades
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Rubric -> {
                listOf(
                    id == other.id,
                    title == other.title
                ).all { it }
            }
            is RubricWithRelations -> {
                listOf(
                    this.equals(other.rubric),
                    this.grades == other.grades,
                    this.skillSets == other.skillSets
                ).all { it }
            }
            else -> super.equals(other)
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}