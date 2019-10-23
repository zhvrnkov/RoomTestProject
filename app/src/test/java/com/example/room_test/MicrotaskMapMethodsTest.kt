package com.example.room_test

import com.example.room_test.entities.Microtask
import com.example.room_test.entity_utils.MicrotaskUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.helpers.MockMicrotaskDao
import com.example.room_test.mock_dtos.MockMicrotaskDTO
import com.example.room_test.mock_dtos.newId

class MockMicrotaskUtils(dao: MockMicrotaskDao) :
        MicrotaskUtils<MockMicrotaskDTO>(
            dao,
            MockMicrotaskDTO::class.java)

class MicrotaskMapMethodsTest :
    GenericMapMethodsTest<Microtask, Microtask, MockMicrotaskDTO>()
{
    private val skillSetId = Long.newId()

    private val utils = MockMicrotaskUtils(MockMicrotaskDao())
    override val mapEntity = utils.realization::mapEntity
    override val mapFields = utils.realization::mapFields

    override val newEntityWithRelations: Microtask
        get() = MockEntityGenerator.microtaskMocks(skillSetId).random()
    override val newDTO: MockMicrotaskDTO
        get() = MockMicrotaskDTO.new(skillSetId)
}
