package com.example.room_test

import com.example.room_test.entities.SkillSet
import com.example.room_test.entities.SkillSetWithRelations
import com.example.room_test.entity_utils.SkillSetUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.mock_dtos.*
import com.example.room_test.helpers.*

class MockSkillSetUtils(dao: MockSkillSetDao) :
    SkillSetUtils<MockMicrotaskDTO, MockSkillSetDTO>(
        dao,
        MockSkillSetDTO::class.java,
        MockMicrotaskDTO::class.java
), Mapper<SkillSet, SkillSetWithRelations, MockSkillSetDTO>

class SkillSetMapMethodsTest :
        GenericMapMethodsTest<
                SkillSet,
                SkillSetWithRelations,
                MockSkillSetDTO,
                MockSkillSetUtils>()
{
    private val rubricId = Long.newId()

    override val utils: MockSkillSetUtils = MockSkillSetUtils(MockSkillSetDao())
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
