package com.example.room_test

import com.example.room_test.entities.Assessment
import com.example.room_test.entities.AssessmentWithRelations
import com.example.room_test.entity_utils.AssessmentUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.MockAssessmentDao
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.mock_dtos.MockAssessmentDTO
import com.example.room_test.mock_dtos.MockMicrotaskGradeDTO

class MockAssessmentUtils(dao: MockAssessmentDao) :
        AssessmentUtils<MockMicrotaskGradeDTO, MockAssessmentDTO>(
            dao,
            MockAssessmentDTO::class.java,
            MockMicrotaskGradeDTO::class.java
), Mapper<Assessment, AssessmentWithRelations, MockAssessmentDTO>

class AssessmentMapMethodsTest :
        GenericMapMethodsTest<
                Assessment,
                AssessmentWithRelations,
                MockAssessmentDTO,
                MockAssessmentUtils>()
{
    override val utils = MockAssessmentUtils(MockAssessmentDao())
    override val newEntityWithRelations: AssessmentWithRelations
        get() {
            val assessment = MockEntityGenerator.assessment()
            val microtaskGrades = MockEntityGenerator
                .microtaskGradeMocks(assessment.id)
            return AssessmentWithRelations(assessment, microtaskGrades)
        }
    override val newDTO: MockAssessmentDTO
        get() {
            return MockAssessmentDTO.new()
        }
}