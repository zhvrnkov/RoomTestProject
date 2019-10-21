package com.example.room_test

import com.example.room_test.entity_utils.InstructorUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.Mapper
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.helpers.MockInstructorDao
import com.example.room_test.mock_dtos.MockInstructorDTO
import com.example.room_test.mock_dtos.newId

class MockInstrctorUtils(dao: MockInstructorDao) :
        InstructorUtils<MockInstructorDTO>(
            dao,
            MockInstructorDTO::class.java
), Mapper<Instructor, Instructor, MockInstructorDTO>

class InstructorMapMethodsTest :
        GenericMapMethodsTest<Instructor, Instructor, MockInstructorDTO, MockInstrctorUtils>()
{
    override val utils = MockInstrctorUtils(MockInstructorDao())

    override val newEntityWithRelations: Instructor
        get() = MockEntityGenerator.instructor()

    override val newDTO: MockInstructorDTO
        get() = MockInstructorDTO(id = Long.newId())
}
