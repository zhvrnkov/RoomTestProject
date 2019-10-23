package com.example.room_test.mock_dtos

import com.example.room_test.entities.Microtask
import com.example.room_test.entity_utils.MicrotaskFields

data class MockMicrotaskDTO(
    override var id: Long = -1,
    override var title: String = "",
    override var content: String = "",
    override var skillSetId: Long = -1
) : MicrotaskFields {
    companion object {
        fun new(skillSetId: Long): MockMicrotaskDTO {
            return MockMicrotaskDTO(
                Long.newId(),
                "lorem",
                "lorem ipsum uel datur nor datum",
                skillSetId
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Microtask -> {
                listOf(
                    id == other.id,
                    title == other.title,
                    content == other.content,
                    skillSetId == other.skillSetId
                ).all { it }
            }
            else -> super.equals(other)
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}