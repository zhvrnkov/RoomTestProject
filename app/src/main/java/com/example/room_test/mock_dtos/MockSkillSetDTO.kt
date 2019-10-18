package com.example.room_test.mock_dtos

import com.example.room_test.SkillSet
import com.example.room_test.SkillSetWithRelations
import com.example.room_test.entity_utils.SkillSetFields

data class MockSkillSetDTO(
    override var id: Long,
    override var title: String,
    override var rubricId: Long,
    override var microtasks: List<MockMicrotaskDTO>
) : SkillSetFields<MockMicrotaskDTO> {
    companion object {
        fun new(rubricId: Long): MockSkillSetDTO {
            return MockSkillSetDTO(
                Long.newId(),
                "lorem",
                rubricId,
                emptyList()
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is SkillSet -> {
                listOf(
                    id == other.id,
                    title == other.title,
                    rubricId == other.rubricId
                ).all { it }
            }
            is SkillSetWithRelations -> {
                listOf(
                    this.equals(other.skillSet),
                    this.microtasks == other.microtasks
                ).all { it }
            }
            else -> super.equals(other)
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}