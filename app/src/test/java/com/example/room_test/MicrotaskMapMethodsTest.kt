package com.example.room_test

import com.example.room_test.entity_utils.MicrotaskUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.Mapper
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.helpers.MockMicrotaskDao
import com.example.room_test.mock_dtos.MockMicrotaskDTO
import com.example.room_test.mock_dtos.newId

class MockMicrotaskUtils(dao: MockMicrotaskDao) :
        MicrotaskUtils<MockMicrotaskDTO>(
            dao,
            MockMicrotaskDTO::class.java
), Mapper<Microtask, Microtask, MockMicrotaskDTO>

class MicrotaskMapMethodsTest :
    GenericMapMethodsTest<Microtask, Microtask, MockMicrotaskDTO, MockMicrotaskUtils>()
{
    private val skillSetId = Long.newId()

    override val utils = MockMicrotaskUtils(MockMicrotaskDao())

    override val newEntityWithRelations: Microtask
        get() = MockEntityGenerator.microtaskMocks(skillSetId).random()
    override val newDTO: MockMicrotaskDTO
        get() = MockMicrotaskDTO.new(skillSetId)
}
