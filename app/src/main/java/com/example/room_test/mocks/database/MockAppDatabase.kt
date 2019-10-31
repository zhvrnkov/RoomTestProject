package com.example.room_test.mocks.database

import com.example.room_test.*
import com.example.room_test.entities.Grade
import com.example.room_test.entity_utils.EntityUtils
import com.example.room_test.entity_utils.*
import com.example.room_test.mocks.dtos.*
import com.example.room_test.mocks.utils.*

object MockAppDatabase {
    fun <InstructorDTO : InstructorFields> getInstructors(
        type: Class<InstructorDTO>
    ): EntityUtils<InstructorDTO> = MockInstrctorUtils(type)

    fun <MicrotaskDTO : MicrotaskFields, SkillSetDTO : SkillSetFields<MicrotaskDTO>>
            getSkillSets(mType: Class<MicrotaskDTO>,
                         sType: Class<SkillSetDTO>
    ): EntityUtils<SkillSetDTO> = MockSkillSetUtils(mType, sType)

    fun <MicrotaskDTO : MicrotaskFields,
            SkillSetDTO : SkillSetFields<MicrotaskDTO>,
            GradeDTO : GradeFields,
            RubricDTO : RubricFields<GradeDTO, MicrotaskDTO, SkillSetDTO>>
            getRubrics(mType: Class<MicrotaskDTO>,
                       sType: Class<SkillSetDTO>,
                       gType: Class<GradeDTO>,
                       rType: Class<RubricDTO>,
                       skillSetsFetch: (TypeOfGet) -> List<SkillSetDTO>
    ): EntityUtils<RubricDTO> = MockRubricUtils(skillSetsFetch, gType, mType, sType, rType)

    fun <MicrotaskDTO : MicrotaskFields> getMicrotasks(
        mType: Class<MicrotaskDTO>
    ): EntityUtils<MicrotaskDTO> = MockMicrotaskUtils(mType)

    fun <GradeDTO : GradeFields> getGrades(
        gType: Class<GradeDTO>
    ): EntityUtils<GradeDTO> = MockGradeUtils(gType)

    fun <MicrotaskGradeDTO : MicrotaskGradeFields> getMicrotaskGrades(
        mgType: Class<MicrotaskGradeDTO>
    ): EntityUtils<MicrotaskGradeDTO> = MockMicrotaskGradeUtils(mgType)

    fun <MicrotaskGradeDTO : MicrotaskGradeFields,
            AssessmentDTO : AssessmentFields<MicrotaskGradeDTO>>
            getAssessments(mgType: Class<MicrotaskGradeDTO>,
                           aType: Class<AssessmentDTO>
    ): EntityUtils<AssessmentDTO> = MockAssessmentUtils(mgType, aType)

    fun <StudentDTO : StudentFields> getStudents(
        sType: Class<StudentDTO>
    ): EntityUtils<StudentDTO> = MockStudentUtils(sType)
}