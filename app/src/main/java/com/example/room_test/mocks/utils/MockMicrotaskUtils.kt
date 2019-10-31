package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.MicrotaskFields
import com.example.room_test.entity_utils.MicrotaskUtils
import com.example.room_test.mocks.daos.MockMicrotaskDao
import com.example.room_test.mocks.dtos.MockMicrotaskDTO

internal class MockMicrotaskUtils<T: MicrotaskFields>(
    dtoClass: Class<T>
) :
    MicrotaskUtils<T>(
        MockMicrotaskDao(),
        dtoClass)