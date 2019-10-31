package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.StudentFields
import com.example.room_test.entity_utils.StudentUtils
import com.example.room_test.mocks.daos.MockStudentDao
import com.example.room_test.mocks.dtos.MockStudentDTO

internal class MockStudentUtils<S: StudentFields>(
    dtoClass: Class<S>
) : StudentUtils<S>(MockStudentDao(), dtoClass)