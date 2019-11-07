package com.example.room_test.mocks.entity_generator

import com.example.room_test.entities.*
import com.example.room_test.newId

internal object MockEntityGenerator {
    fun rubricMock() = Rubric(Long.newId(), "Lorem", 0, 0, true)
    fun gradeMocks(rubricId: Long) = (0..10).map {
        Grade(Long.newId(), score = it, title = "$it", rubricId = rubricId)
    }
    fun skillSetMocks(rubricId: Long) = (0..10).map {
        SkillSet(Long.newId(), "$it", rubricId)
    }
    fun microtaskMocks(skillSetId: Long) = (0..10).map {
        Microtask(Long.newId(), "$it", "lorem #$it", skillSetId)
    }
    fun instructor() = Instructor(
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
    fun students(instructorId: Long) = (0..10).map {
        Student(
            id = Long.newId(),
            name = "$it student",
            email = "@$it.com",
            level = "$it",
            logbookPass = "${it * 6}",
            qualifiedDays = it.toInt(),
            rank = "$it",
            instructorId = instructorId,
            assessmentIds = emptyList(),
            microtaskGradeIds = emptyList()
        )
    }
}
