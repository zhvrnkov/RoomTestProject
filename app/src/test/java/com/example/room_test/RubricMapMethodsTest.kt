package com.example.room_test

import com.example.room_test.entities.Rubric
import com.example.room_test.entities.RubricWithRelations
import com.example.room_test.entities.SkillSetWithRelations
import com.example.room_test.entity_utils.RubricUtils
import com.example.room_test.helpers.*
import com.example.room_test.mock_dtos.*
import org.junit.Before

internal class MockRubricUtils(dao: MockRubricDao, closure: (List<Long>) -> List<MockSkillSetDTO>) :
    RubricUtils<MockGradeDTO, MockMicrotaskDTO, MockSkillSetDTO, MockRubricDTO>(
        dao,
        MockRubricDTO::class.java,
        MockGradeDTO::class.java,
        MockSkillSetDTO::class.java,
        MockMicrotaskDTO::class.java,
        closure
    )

internal class RubricMapMethodsTest :
    GenericMapMethodsTest<Rubric, RubricWithRelations, MockRubricDTO>()
{
    private val skillSetDao = MockSkillSetDao()
    private val skillSetUtils = MockSkillSetUtils(skillSetDao)

    private val utils: MockRubricUtils by lazy {
        MockRubricUtils(dao = MockRubricDao(), closure = skillSetUtils::get)
    }
    override val mapEntity = utils.realization::mapEntity
    override val mapFields = utils.realization::mapFields


    override val newEntityWithRelations: RubricWithRelations
        get() {
            val rubric = MockEntityGenerator.rubricMock()
            val grades = MockEntityGenerator.gradeMocks(rubric.id)
            val skillSets = MockEntityGenerator.skillSetMocks(rubric.id)
            skillSets.forEach(skillSetDao::insert)
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
