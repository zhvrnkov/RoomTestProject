package com.example.room_test

import com.example.room_test.entities.MicrotaskGrade
import com.example.room_test.entity_utils.MicrotaskGradeUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.Mapper
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.helpers.MockMicrotaskGradeDao
import com.example.room_test.mock_dtos.MockMicrotaskGradeDTO
import com.example.room_test.mock_dtos.newId

class MockMicrotaskGradeUtils(dao: MockMicrotaskGradeDao) :
        MicrotaskGradeUtils<MockMicrotaskGradeDTO>(
            dao, MockMicrotaskGradeDTO::class.java),
        Mapper<MicrotaskGrade, MicrotaskGrade, MockMicrotaskGradeDTO>

class MicrotaskGradesMapMethodsTest :
        GenericMapMethodsTest<
                MicrotaskGrade,
                MicrotaskGrade,
                MockMicrotaskGradeDTO,
                MockMicrotaskGradeUtils>()
{
    private val assessmentId = Long.newId()
    override val utils = MockMicrotaskGradeUtils(MockMicrotaskGradeDao())
    override val newEntityWithRelations: MicrotaskGrade
        get() {
            return MockEntityGenerator
                .microtaskGradeMocks(assessmentId).random()
        }
    override val newDTO: MockMicrotaskGradeDTO
        get() {
            return MockMicrotaskGradeDTO.new(assessmentId)
        }
}