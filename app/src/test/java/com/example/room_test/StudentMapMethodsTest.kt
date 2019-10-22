package com.example.room_test

import com.example.room_test.entity_utils.StudentUtils
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.Mapper
import com.example.room_test.helpers.MockEntityGenerator
import com.example.room_test.helpers.MockStudentDao
import com.example.room_test.mock_dtos.MockStudentDTO
import com.example.room_test.mock_dtos.newId

class MockStudentUtils(dao: MockStudentDao) :
        StudentUtils<MockStudentDTO>(
            dao, MockStudentDTO::class.java),
        Mapper<Student, Student, MockStudentDTO>

class StudentMapMethodsTest :
        GenericMapMethodsTest<
                Student,
                Student,
                MockStudentDTO,
                MockStudentUtils>()
{
    private val instructorId = Long.newId()

    override val utils = MockStudentUtils(MockStudentDao())
    override val newEntityWithRelations: Student
        get() {
            return MockEntityGenerator.students(instructorId).random()
        }
    override val newDTO: MockStudentDTO
        get() {
            return MockStudentDTO.new()
        }
}