package com.example.room_test

import com.example.room_test.entity_utils.RubricDao
import com.example.room_test.entity_utils.RubricUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.Mapper
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.helpers.MockRubricDao
import com.example.room_test.mock_dtos.*

class MockRubricUtils(dao: MockRubricDao) :
    RubricUtils<MockGradeDTO, MockMicrotaskDTO, MockSkillSetDTO, MockRubricDTO>(
        dao,
        MockRubricDTO::class.java,
        MockGradeDTO::class.java,
        MockSkillSetDTO::class.java,
        MockMicrotaskDTO::class.java
    ), Mapper<Rubric, RubricWithRelations, MockRubricDTO>

class RubricMapMethodsTest :
    GenericMapMethodsTest<Rubric, RubricWithRelations, MockRubricDTO, MockRubricUtils>()
{
    override val utils: MockRubricUtils = fun(): MockRubricUtils {
        return MockRubricUtils(dao = MockRubricDao())
    }()

    override val newEntityWithRelations: RubricWithRelations
        get() {
            val rubric = MockEntityGenerator.rubricMock()
            val grades = MockEntityGenerator.gradeMocks(rubric.id)
            val skillSets = MockEntityGenerator.skillSetMocks(rubric.id)
            return RubricWithRelations(rubric, grades, emptyList())
        }

    override val newDTO: MockRubricDTO
        get() {
            val rubric = MockRubricDTO.new()
            rubric.grades = (0..10).map { MockGradeDTO.new(rubric.id) }
            return rubric
        }
}