package com.example.room_test

import com.example.room_test.entity_utils.RubricDao
import com.example.room_test.entity_utils.RubricUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.Mapper
import com.example.room_test.mock_dtos.*
import java.util.*

private fun newId() = UUID.randomUUID().mostSignificantBits
fun rubricMock(): Rubric = Rubric(newId(), "Lorem")
fun gradeMocks(rubricId: Long): List<Grade> = (0..10).map {
    Grade(newId(), score = it, title = "$it", rubricId = rubricId)
}

class MockRubricUtils(dao: RubricDao) :
    RubricUtils<MockGradeDTO, MockRubricDTO>(dao, MockRubricDTO::class.java, MockGradeDTO::class.java),
    Mapper<Rubric, RubricWithRelations, MockRubricDTO>

class RubricMapMethodsTest :
    GenericMapMethodsTest<Rubric, RubricWithRelations, MockRubricDTO, MockRubricUtils>()
{
    override val newEntityWithRelations: RubricWithRelations
        get() = RubricWithRelations(
            rubric = rubricMock(),
            grades = )
}