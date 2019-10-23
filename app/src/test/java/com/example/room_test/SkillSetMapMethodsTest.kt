package com.example.room_test

import com.example.room_test.entities.SkillSet
import com.example.room_test.entities.SkillSetWithRelations
import com.example.room_test.entity_utils.SkillSetUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.mock_dtos.*
import com.example.room_test.helpers.*

internal class MockSkillSetUtils(dao: MockSkillSetDao) :
    SkillSetUtils<MockMicrotaskDTO, MockSkillSetDTO>(
        dao,
        MockSkillSetDTO::class.java,
        MockMicrotaskDTO::class.java
)

internal class SkillSetMapMethodsTest :
        GenericMapMethodsTest<
                SkillSet,
                SkillSetWithRelations,
                MockSkillSetDTO>()
{
    private val rubricId = Long.newId()

    private val utils: MockSkillSetUtils = MockSkillSetUtils(MockSkillSetDao())
    override val mapEntity = utils.realization::mapEntity
    override val mapFields = utils.realization::mapFields
    override val newEntityWithRelations: SkillSetWithRelations
        get() {
            val skillSet = MockEntityGenerator.skillSetMocks(rubricId).random()
            val microtasks = MockEntityGenerator.microtaskMocks(skillSet.id)
            return SkillSetWithRelations(skillSet, microtasks)
        }

    override val newDTO: MockSkillSetDTO
        get() {
            val skillSet = MockSkillSetDTO.new(rubricId)
            skillSet.microtasks = (0..10).map {
                MockMicrotaskDTO(
                    Long.newId(),
                    "$it",
                    "lorem #$it",
                    skillSet.id)
            }
            return skillSet
        }
}
