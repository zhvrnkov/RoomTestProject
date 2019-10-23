package com.example.room_test

import com.example.room_test.entities.Grade
import com.example.room_test.entity_utils.BaseDao
import com.example.room_test.entity_utils.GradeUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.helpers.MockGradeDao
import com.example.room_test.mock_dtos.MockGradeDTO
import com.example.room_test.mock_dtos.newId

class MockGradeUtils(dao: MockGradeDao) :
    GradeUtils<MockGradeDTO>(dao, MockGradeDTO::class.java)

class GradeMapMethodsTest :
        GenericMapMethodsTest<Grade, Grade, MockGradeDTO>()
{
    private val rubricId = Long.newId()
    private val utils = MockGradeUtils(MockGradeDao())
    override val mapEntity = utils.realization::mapEntity
    override val mapFields = utils.realization::mapFields
    override val newEntityWithRelations: Grade
        get() {
            return MockEntityGenerator.gradeMocks(rubricId).random()
        }
    override val newDTO: MockGradeDTO
        get() {
            return MockGradeDTO.new(rubricId)
        }
}