package com.example.room_test

import com.example.room_test.entities.SkillSet
import com.example.room_test.entities.SkillSetWithRelations
import com.example.room_test.entity_utils.SkillSetUtils
import com.example.room_test.utils.GenericMapMethodsTest
import com.example.room_test.mocks.dtos.MockMicrotaskDTO
import com.example.room_test.mocks.dtos.MockSkillSetDTO
import com.example.room_test.mocks.daos.MockSkillSetDao
import com.example.room_test.mocks.entity_generator.MockEntityGenerator
import com.example.room_test.mocks.utils.MockSkillSetUtils

internal class SkillSetMapMethodsTest :
        GenericMapMethodsTest<
                SkillSet,
                SkillSetWithRelations,
                MockSkillSetDTO>()
{
    private val rubricId = Long.newId()

    private val utils = MockSkillSetUtils(
        MockMicrotaskDTO::class.java, MockSkillSetDTO::class.java)
    override val mapEntity: (SkillSetWithRelations) -> MockSkillSetDTO
        get() = { utils.realization.mapEntities(listOf(it)).first() }
    override val mapFields: (MockSkillSetDTO) -> SkillSet
        get() = { utils.realization.mapFields(it).first() }
    override val newEntityWithRelations: SkillSetWithRelations
        get() {
            val skillSet = MockEntityGenerator.skillSetMocks(rubricId).random()
            val microtasks = MockEntityGenerator.microtaskMocks(skillSet.id)
            return SkillSetWithRelations(skillSet, microtasks)
        }

    override val newDTO: MockSkillSetDTO
        get() {
            val id = Long.newId()
            return MockSkillSetDTO.new(
                id,
                rubricId,
                (0..10).map {
                    MockMicrotaskDTO(
                        Long.newId(),
                        "$it",
                        "lorem #$it",
                        id
                    )
                }
            )
        }
}
