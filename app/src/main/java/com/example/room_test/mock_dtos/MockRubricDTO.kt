package com.example.room_test.mock_dtos

import com.example.room_test.entities.Rubric
import com.example.room_test.entities.RubricWithRelations
import com.example.room_test.entities.SkillSet
import com.example.room_test.entity_utils.RubricFields

data class MockRubricDTO(
    override var id: Long = -1,
    override var title: String = "",
    override var grades: List<MockGradeDTO> = emptyList(),
    override var skillSets: List<MockSkillSetDTO> = emptyList()
) : RubricFields<MockGradeDTO, MockMicrotaskDTO, MockSkillSetDTO> {
    companion object {
        fun new(): MockRubricDTO {
            return MockRubricDTO(Long.newId(), "Lorem")
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