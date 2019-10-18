package com.example.room_test.mock_dtos

import com.example.room_test.Grade
import com.example.room_test.Rubric
import com.example.room_test.RubricWithRelations
import com.example.room_test.SkillSet
import com.example.room_test.entity_utils.RubricFields

data class MockRubricDTO(
    override var id: Long = -1,
    override var title: String = "Bad title",
    override var grades: List<MockGradeDTO> = emptyList()
) : RubricFields<MockGradeDTO> {
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
                this.equals(other.rubric) && (this.grades == other.grades)
            }
            else -> super.equals(other)
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

fun main() {
    val dto = MockRubricDTO(grades = listOf(MockGradeDTO(2, "l")))
    val entity = Rubric(-1, "Bad title")
    val entityWithRelation = RubricWithRelations(
        entity,
        listOf(Grade(2, 0, "", -1)),
        emptyList()
    )

    println(dto.equals(entity))
    println(dto.equals(entityWithRelation))
}