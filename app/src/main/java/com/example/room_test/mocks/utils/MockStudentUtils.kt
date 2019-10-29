package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.StudentUtils
import com.example.room_test.mocks.daos.MockStudentDao
import com.example.room_test.mocks.dtos.MockStudentDTO

internal class MockStudentUtils :
    StudentUtils<MockStudentDTO>(MockStudentDao(), MockStudentDTO::class.java)