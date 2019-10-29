package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.MicrotaskGradeUtils
import com.example.room_test.mocks.daos.MockMicrotaskGradeDao
import com.example.room_test.mocks.dtos.MockMicrotaskGradeDTO

internal class MockMicrotaskGradeUtils :
    MicrotaskGradeUtils<MockMicrotaskGradeDTO>(
        MockMicrotaskGradeDao(), MockMicrotaskGradeDTO::class.java)