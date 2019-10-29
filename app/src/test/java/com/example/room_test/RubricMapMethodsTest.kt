package com.example.room_test

import com.example.room_test.entities.Rubric
import com.example.room_test.entities.RubricWithRelations
import com.example.room_test.entity_utils.RubricUtils
import com.example.room_test.entity_utils.SkillSetUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.MockRubricDao
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.mock_dtos.*

internal class MockRubricUtils(closure: (List<Long>) -> List<MockSkillSetDTO>) :
    RubricUtils<MockGradeDTO, MockMicrotaskDTO, MockSkillSetDTO, MockRubricDTO>(
        MockRubricDao(),
        MockRubricDTO::class.java,
        MockGradeDTO::class.java,
        MockSkillSetDTO::class.java,
        MockMicrotaskDTO::class.java,
        closure
    )

internal class RubricMapMethodsTest :
    GenericMapMethodsTest<Rubric, RubricWithRelations, MockRubricDTO>()
{
    private val skillSetUtils = MockSkillSetUtils()

    private val utils: MockRubricUtils by lazy {
        MockRubricUtils(skillSetUtils::get)
    }
    override val mapEntity = utils.realization::mapEntity
    override val mapFields = utils.realization::mapFields


    override val newEntityWithRelations: RubricWithRelations
        get() {
            val rubric = MockEntityGenerator.rubricMock()
            val grades = MockEntityGenerator.gradeMocks(rubric.id)
            val skillSetDtos = (0..9).map { MockSkillSetDTO.new(Long.newId(), rubric.id, emptyList()) }
            skillSetDtos.forEach(skillSetUtils::insert)
            val skillSets = skillSetDtos.map { SkillSetUtils.staticMapFields(it) }
            return RubricWithRelations(rubric, grades, skillSets)
        }

    override val newDTO: MockRubricDTO
        get() {
            val id = Long.newId()
            return MockRubricDTO.new(
                id = id,
                grades = (0..10).map { MockGradeDTO.new(id) })
        }
}
