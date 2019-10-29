package com.example.room_test

import com.example.room_test.entities.Assessment
import com.example.room_test.entities.AssessmentWithRelations
import com.example.room_test.entity_utils.AssessmentUtils
import com.example.room_test.utils.GenericMapMethodsTest
import com.example.room_test.mocks.daos.MockAssessmentDao
import com.example.room_test.mocks.entity_generator.MockEntityGenerator
import com.example.room_test.mocks.dtos.MockAssessmentDTO
import com.example.room_test.mocks.dtos.MockMicrotaskGradeDTO
import com.example.room_test.mocks.utils.MockAssessmentUtils


internal class AssessmentMapMethodsTest :
        GenericMapMethodsTest<
                Assessment,
                AssessmentWithRelations,
                MockAssessmentDTO>()
{
    private val utils = MockAssessmentUtils()
    override val mapEntity = utils.realization::mapEntity
    override val mapFields = utils.realization::mapFields
    override val newEntityWithRelations: AssessmentWithRelations
        get() {
            val assessment = MockEntityGenerator.assessment()
            val microtaskGrades = MockEntityGenerator
                .microtaskGradeMocks(assessment.id)
            return AssessmentWithRelations(assessment, microtaskGrades)
        }
    override val newDTO: MockAssessmentDTO
        get() = MockAssessmentDTO.new()
}