package com.example.room_test

import com.example.room_test.entities.Microtask
import com.example.room_test.entity_utils.MicrotaskUtils
import com.example.room_test.utils.GenericMapMethodsTest
import com.example.room_test.mocks.entity_generator.MockEntityGenerator
import com.example.room_test.mocks.daos.MockMicrotaskDao
import com.example.room_test.mocks.dtos.MockMicrotaskDTO
import com.example.room_test.mocks.utils.MockMicrotaskUtils


internal class MicrotaskMapMethodsTest :
    GenericMapMethodsTest<Microtask, Microtask, MockMicrotaskDTO>()
{
    private val skillSetId = Long.newId()

    private val utils = MockMicrotaskUtils(MockMicrotaskDTO::class.java)
    override val mapEntity: (Microtask) -> MockMicrotaskDTO
        get() = { utils.realization.mapEntities(listOf(it)).first() }
    override val mapFields: (MockMicrotaskDTO) -> Microtask
        get() = { utils.realization.mapFields(it).first() }

    override val newEntityWithRelations: Microtask
        get() = MockEntityGenerator.microtaskMocks(skillSetId).random()
    override val newDTO: MockMicrotaskDTO
        get() = MockMicrotaskDTO.new(skillSetId)
}
