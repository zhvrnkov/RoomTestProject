package com.example.room_test.mocks.dtos

import com.example.room_test.entities.SkillSet
import com.example.room_test.entities.SkillSetWithRelations
import com.example.room_test.entity_utils.SkillSetFields
import com.example.room_test.newId

data class MockSkillSetDTO(
    override val id: Long,
    override val title: String = "",
    override val rubricId: Long = -1,
    override val microtasks: List<MockMicrotaskDTO> = emptyList()
) : SkillSetFields<MockMicrotaskDTO> {
    companion object {
        fun new(id: Long, rubricId: Long, microtasks: List<MockMicrotaskDTO>): MockSkillSetDTO {
            return MockSkillSetDTO(
                Long.newId(),
                "lorem",
                rubricId,
                microtasks
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
