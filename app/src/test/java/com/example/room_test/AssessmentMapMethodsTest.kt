package com.example.room_test

import com.example.room_test.entities.Assessment
import com.example.room_test.entities.AssessmentWithRelations
import com.example.room_test.entity_utils.AssessmentUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.MockAssessmentDao
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.mock_dtos.MockAssessmentDTO
import com.example.room_test.mock_dtos.MockMicrotaskGradeDTO

internal class MockAssessmentUtils :
        AssessmentUtils<MockMicrotaskGradeDTO, MockAssessmentDTO>(
            MockAssessmentDao(),
            MockAssessmentDTO::class.java,
            MockMicrotaskGradeDTO::class.java)

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