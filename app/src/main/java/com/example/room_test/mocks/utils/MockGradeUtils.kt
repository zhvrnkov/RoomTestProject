package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.GradeUtils
import com.example.room_test.mocks.daos.MockGradeDao
import com.example.room_test.mocks.dtos.MockGradeDTO

internal class MockGradeUtils :
    GradeUtils<MockGradeDTO>(MockGradeDao(), MockGradeDTO::class.java)