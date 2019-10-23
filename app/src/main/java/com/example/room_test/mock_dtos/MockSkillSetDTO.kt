package com.example.room_test.mock_dtos

import com.example.room_test.entities.SkillSet
import com.example.room_test.entities.SkillSetWithRelations
import com.example.room_test.entity_utils.SkillSetFields

data class MockSkillSetDTO(
    override var id: Long = -1,
    override var title: String = "",
    override var rubricId: Long = -1,
    override var microtasks: List<MockMicrotaskDTO> = emptyList()
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

    override fun equals(other: Any?): Boolean = when (other) {
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

    override fun hashCode() = super.hashCode()
}
