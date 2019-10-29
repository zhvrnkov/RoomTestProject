package com.example.room_test.helpers

import com.example.room_test.*
import com.example.room_test.entity_utils.EntityUtils
import com.example.room_test.mock_dtos.*

object MockAppDatabase {
    val instructors: EntityUtils<MockInstructorDTO> = MockInstrctorUtils()
    val skillSets: EntityUtils<MockSkillSetDTO> = MockSkillSetUtils()
    val rubrics: EntityUtils<MockRubricDTO> = MockRubricUtils(closure = skillSets::get)
    val microtasks: EntityUtils<MockMicrotaskDTO> = MockMicrotaskUtils()
    val grades: EntityUtils<MockGradeDTO> = MockGradeUtils()
    val microtaskGrades: EntityUtils<MockMicrotaskGradeDTO> = MockMicrotaskGradeUtils()
    val assessments: EntityUtils<MockAssessmentDTO> = MockAssessmentUtils()
    val students: EntityUtils<MockStudentDTO> = MockStudentUtils()
}