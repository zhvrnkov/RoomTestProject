package com.example.room_test

import com.example.room_test.entities.MicrotaskGrade
import com.example.room_test.entity_utils.MicrotaskGradeUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.helpers.MockMicrotaskGradeDao
import com.example.room_test.mock_dtos.MockMicrotaskGradeDTO
import com.example.room_test.mock_dtos.newId

internal class MockMicrotaskGradeUtils :
        MicrotaskGradeUtils<MockMicrotaskGradeDTO>(
            MockMicrotaskGradeDao(), MockMicrotaskGradeDTO::class.java)

internal class MicrotaskGradesMapMethodsTest :
        GenericMapMethodsTest<
                MicrotaskGrade,
                MicrotaskGrade,
                MockMicrotaskGradeDTO>()
{
    private val assessmentId = Long.newId()
    private val utils = MockMicrotaskGradeUtils()
    override val mapEntity = utils.realization::mapEntity
    override val mapFields = utils.realization::mapFields
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