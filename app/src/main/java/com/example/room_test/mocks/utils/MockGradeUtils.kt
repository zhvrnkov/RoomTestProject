package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.GradeFields
import com.example.room_test.entity_utils.GradeUtils
import com.example.room_test.mocks.daos.MockGradeDao
import com.example.room_test.mocks.dtos.MockGradeDTO

internal class MockGradeUtils<T: GradeFields>(
    dtoClass: Class<T>
) :
    GradeUtils<T>(MockGradeDao(), dtoClass)