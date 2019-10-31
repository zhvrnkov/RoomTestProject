package com.example.room_test.mocks.database

import com.example.room_test.*
import com.example.room_test.entity_utils.EntityUtils
import com.example.room_test.entity_utils.*
import com.example.room_test.mocks.dtos.*
import com.example.room_test.mocks.utils.*

class MockAppDatabase
<SchoolDTO : SchoolFields,
        InstructorDTO : InstructorFields,
        GradeDTO : GradeFields,
        MicrotaskDTO : MicrotaskFields,
        SkillSetDTO : SkillSetFields<MicrotaskDTO>,
        RubricDTO : RubricFields<GradeDTO, MicrotaskDTO, SkillSetDTO>,
        MicrotaskGradeDTO : MicrotaskGradeFields,
        AssessmentDTO : AssessmentFields<MicrotaskGradeDTO>,
        StudentDTO : StudentFields>(
    schoolDtoClass: Class<SchoolDTO>,
    instructorDtoClass: Class<InstructorDTO>,
    rubricDtoClass: Class<RubricDTO>,
    gradeDtoClass: Class<GradeDTO>,
    skillSetDtoClass: Class<SkillSetDTO>,
    microtaskDtoClass: Class<MicrotaskDTO>,
    microtaskGradeDtoClass: Class<MicrotaskGradeDTO>,
    assessmentDtoClass: Class<AssessmentDTO>,
    studentDtoClass: Class<StudentDTO>
) {
    val instructors: EntityUtils<InstructorDTO> = MockInstrctorUtils(instructorDtoClass)
    val skillSets: EntityUtils<SkillSetDTO> = MockSkillSetUtils(microtaskDtoClass, skillSetDtoClass)
    val rubrics: EntityUtils<RubricDTO> = MockRubricUtils(
        skillSets::get, gradeDtoClass, microtaskDtoClass, skillSetDtoClass, rubricDtoClass)
    val microtasks: EntityUtils<MicrotaskDTO> = MockMicrotaskUtils(microtaskDtoClass)
    val grades: EntityUtils<GradeDTO> = MockGradeUtils(gradeDtoClass)
    val microtaskGrades: EntityUtils<MicrotaskGradeDTO> = MockMicrotaskGradeUtils(microtaskGradeDtoClass)
    val assessments: EntityUtils<AssessmentDTO> = MockAssessmentUtils(microtaskGradeDtoClass, assessmentDtoClass)
    val students: EntityUtils<StudentDTO> = MockStudentUtils(studentDtoClass)
}