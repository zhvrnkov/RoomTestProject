package com.example.room_test

import com.example.room_test.entities.Student
import com.example.room_test.entity_utils.StudentDao
import com.example.room_test.entity_utils.StudentUtils
import com.example.room_test.utils.GenericMapMethodsTest
import com.example.room_test.mocks.entity_generator.MockEntityGenerator
import com.example.room_test.mocks.daos.MockStudentDao
import com.example.room_test.mocks.dtos.MockStudentDTO
import com.example.room_test.mocks.utils.MockStudentUtils

internal class StudentMapMethodsTest :
        GenericMapMethodsTest<
                Student,
                Student,
                MockStudentDTO>()
{
    private val instructorId = Long.newId()

    private val utils = MockStudentUtils(MockStudentDTO::class.java)
    override val mapEntity: (Student) -> MockStudentDTO
        get() = { utils.realization.mapEntities(listOf(it)).first() }
    override val mapFields: (MockStudentDTO) -> Student
        get() = { utils.realization.mapFields(it).first() }
    override val newEntityWithRelations: Student
        get() {
            return MockEntityGenerator.students(instructorId).random()
        }
    override val newDTO: MockStudentDTO
        get() {
            return MockStudentDTO.new()
        }
}