package com.example.room_test

import com.example.room_test.entities.Instructor
import com.example.room_test.entity_utils.InstructorUtils
import com.example.room_test.utils.GenericMapMethodsTest
import com.example.room_test.mocks.entity_generator.MockEntityGenerator
import com.example.room_test.mocks.daos.MockInstructorDao
import com.example.room_test.mocks.dtos.MockInstructorDTO
import com.example.room_test.mocks.utils.MockInstrctorUtils

internal class InstructorMapMethodsTest :
        GenericMapMethodsTest<Instructor, Instructor, MockInstructorDTO>()
{
    private val utils = MockInstrctorUtils(MockInstructorDTO::class.java)
    override val mapEntity: (Instructor) -> MockInstructorDTO
        get() = { utils.realization.mapEntities(listOf(it)).first() }
    override val mapFields: (MockInstructorDTO) -> Instructor
        get() = { utils.realization.mapFields(it).first() }
    override val newEntityWithRelations: Instructor
        get() = MockEntityGenerator.instructor()
    override val newDTO: MockInstructorDTO
        get() = MockInstructorDTO.new(Long.newId())
}
