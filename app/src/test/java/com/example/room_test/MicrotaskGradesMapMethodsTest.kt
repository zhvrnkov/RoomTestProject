package com.example.room_test

import com.example.room_test.entities.MicrotaskGrade
import com.example.room_test.entity_utils.MicrotaskGradeUtils
import com.example.room_test.utils.GenericMapMethodsTest
import com.example.room_test.mocks.entity_generator.MockEntityGenerator
import com.example.room_test.mocks.daos.MockMicrotaskGradeDao
import com.example.room_test.mocks.dtos.MockMicrotaskGradeDTO
import com.example.room_test.mocks.utils.MockMicrotaskGradeUtils

internal class MicrotaskGradesMapMethodsTest :
        GenericMapMethodsTest<
                MicrotaskGrade,
                MicrotaskGrade,
                MockMicrotaskGradeDTO>()
{
    private val assessmentId = Long.newId()
    private val utils = MockMicrotaskGradeUtils(MockMicrotaskGradeDTO::class.java)
    override val mapEntity: (MicrotaskGrade) -> MockMicrotaskGradeDTO
        get() = { utils.realization.mapEntities(listOf(it)).first() }
    override val mapFields: (MockMicrotaskGradeDTO) -> MicrotaskGrade
        get() = { utils.realization.mapFields(it).first() }
    override val newEntityWithRelations: MicrotaskGrade
        get() {
            return MockEntityGenerator
                .microtaskGradeMocks(assessmentId, Long.newId()).random()
        }
    override val newDTO: MockMicrotaskGradeDTO
        get() {
            return MockMicrotaskGradeDTO.new(assessmentId)
        }
}