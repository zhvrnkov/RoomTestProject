package com.example.room_test

import com.example.room_test.entities.Grade
import com.example.room_test.entity_utils.GradeUtils
import com.example.room_test.utils.GenericMapMethodsTest
import com.example.room_test.mocks.entity_generator.MockEntityGenerator
import com.example.room_test.mocks.daos.MockGradeDao
import com.example.room_test.mocks.dtos.MockGradeDTO
import com.example.room_test.mocks.utils.MockGradeUtils

internal class GradeMapMethodsTest :
        GenericMapMethodsTest<Grade, Grade, MockGradeDTO>()
{
    private val rubricId = Long.newId()
    private val utils = MockGradeUtils(MockGradeDTO::class.java)
    override val mapEntity: (Grade) -> MockGradeDTO
        get() = { utils.realization.mapEntities(listOf(it)).first() }
    override val mapFields: (MockGradeDTO) -> Grade
        get() = { utils.realization.mapFields(it).first() }
    override val newEntityWithRelations: Grade
        get() {
            return MockEntityGenerator.gradeMocks(rubricId).random()
        }
    override val newDTO: MockGradeDTO
        get() {
            return MockGradeDTO.new(rubricId)
        }
}