package com.example.room_test

import com.example.room_test.entities.Rubric
import com.example.room_test.entities.RubricWithRelations
import com.example.room_test.entity_utils.RubricUtils
import com.example.room_test.entity_utils.SkillSetUtils
import com.example.room_test.entity_utils.TypeOfGet
import com.example.room_test.utils.GenericMapMethodsTest
import com.example.room_test.mocks.daos.MockRubricDao
import com.example.room_test.mocks.entity_generator.MockEntityGenerator
import com.example.room_test.mocks.dtos.MockGradeDTO
import com.example.room_test.mocks.dtos.MockMicrotaskDTO
import com.example.room_test.mocks.dtos.MockRubricDTO
import com.example.room_test.mocks.dtos.MockSkillSetDTO
import com.example.room_test.mocks.utils.MockRubricUtils
import com.example.room_test.mocks.utils.MockSkillSetUtils


internal class RubricMapMethodsTest :
    GenericMapMethodsTest<Rubric, RubricWithRelations, MockRubricDTO>()
{
    private val skillSetUtils = MockSkillSetUtils(
        MockMicrotaskDTO::class.java, MockSkillSetDTO::class.java)

    private val utils = MockRubricUtils(
        skillSetUtils::get, MockGradeDTO::class.java, MockMicrotaskDTO::class.java, MockSkillSetDTO::class.java, MockRubricDTO::class.java)
    override val mapEntity: (RubricWithRelations) -> MockRubricDTO
        get() = { utils.realization.mapEntities(listOf(it)).first() }
    override val mapFields: (MockRubricDTO) -> Rubric
        get() = { utils.realization.mapFields(it).first() }


    override val newEntityWithRelations: RubricWithRelations
        get() {
            val rubric = MockEntityGenerator.rubricMock()
            val grades = MockEntityGenerator.gradeMocks(rubric.id)
            val skillSetDtos = (0..9).map { MockSkillSetDTO.new(Long.newId(), rubric.id, emptyList()) }
            skillSetUtils.insert(*skillSetDtos.toTypedArray())
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
