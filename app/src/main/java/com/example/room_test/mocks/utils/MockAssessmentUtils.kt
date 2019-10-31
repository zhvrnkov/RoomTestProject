package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.AssessmentFields
import com.example.room_test.entity_utils.AssessmentUtils
import com.example.room_test.entity_utils.GradeFields
import com.example.room_test.entity_utils.MicrotaskGradeFields
import com.example.room_test.mocks.daos.MockAssessmentDao
import com.example.room_test.mocks.dtos.MockAssessmentDTO
import com.example.room_test.mocks.dtos.MockMicrotaskGradeDTO

internal class MockAssessmentUtils
<T: MicrotaskGradeFields, N: AssessmentFields<T>>(
    gradeDtoClass: Class<T>,
    assessmentDtoClass: Class<N>
) : AssessmentUtils<T, N>(
    MockAssessmentDao(),
    assessmentDtoClass,
    gradeDtoClass)