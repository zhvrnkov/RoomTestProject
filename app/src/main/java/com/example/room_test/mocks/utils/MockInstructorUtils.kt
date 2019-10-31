package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.InstructorFields
import com.example.room_test.entity_utils.InstructorUtils
import com.example.room_test.mocks.daos.MockInstructorDao
import com.example.room_test.mocks.dtos.MockInstructorDTO

internal class MockInstrctorUtils<DTO: InstructorFields>(
    dtoType: Class<DTO>
) : InstructorUtils<DTO>(MockInstructorDao(), dtoType)