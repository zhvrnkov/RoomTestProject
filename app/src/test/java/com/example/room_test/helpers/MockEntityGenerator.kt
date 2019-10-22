package com.example.room_test.helpers

import com.example.room_test.*
import com.example.room_test.mock_dtos.MockInstructorDTO
import com.example.room_test.mock_dtos.newId

object MockEntityGenerator {
    fun rubricMock(): Rubric = Rubric(Long.newId(), "Lorem")
    fun gradeMocks(rubricId: Long): List<Grade> = (0..10).map {
        Grade(Long.newId(), score = it, title = "$it", rubricId = rubricId)
    }
    fun skillSetMocks(rubricId: Long): List<SkillSet> = (0..10).map {
        SkillSet(Long.newId(), "$it", rubricId)
    }
    fun microtaskMocks(skillSetId: Long): List<Microtask> = (0..10).map {
        Microtask(Long.newId(), "$it", "lorem #$it", skillSetId)
    }
    fun instructor(): Instructor = Instructor(
        id = -1,
        address = "address",
        address2 = "address2",
        avatar = "avatar",
        city = "city",
        country = "country",
        credentials = "credentials",
        depiction = "depiction",
        email = "email",
        firstName = "firstName",
        lang = "lang",
        lastName = "lastName",
        loginUsername = "loginUsername",
        nauticedStatus = "nauticedStatus",
        phone = "phone",
        phoneStudent = "phoneStudent",
        state = "state",
        zip = "zip",
        schoolId = -1,
        assessmentIds = emptyList(),
        studentIds = emptyList(),
        gradeColors = emptyList(),
        flags = emptyList(),
        fbid = emptyMap()
    )
    fun assessment() = Assessment(
        id = Long.newId(),
        date = Long.newId(),
        isAddedToServer = true,
        isSynced = true,
        schooldId = Long.newId(),
        instructorId = Long.newId(),
        rubricId = Long.newId(),
        studentIds = (0..10).map { Long.newId() }
    )
    fun microtaskGradeMocks(assessmentId: Long) = (0..10).map {
        MicrotaskGrade(
            id = Long.newId(),
            isSynced = true,
            lastUpdate = Long.newId(),
            assessmentId = assessmentId,
            gradeId = Long.newId(),
            microtaskId = Long.newId(),
            studentId = Long.newId()
        )
    }
}
