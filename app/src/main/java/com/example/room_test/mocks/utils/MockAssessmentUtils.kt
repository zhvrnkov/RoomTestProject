package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.AssessmentUtils
import com.example.room_test.mocks.daos.MockAssessmentDao
import com.example.room_test.mocks.dtos.MockAssessmentDTO
import com.example.room_test.mocks.dtos.MockMicrotaskGradeDTO

internal class MockAssessmentUtils :
    AssessmentUtils<MockMicrotaskGradeDTO, MockAssessmentDTO>(
        MockAssessmentDao(),
        MockAssessmentDTO::class.java,
        MockMicrotaskGradeDTO::class.java)