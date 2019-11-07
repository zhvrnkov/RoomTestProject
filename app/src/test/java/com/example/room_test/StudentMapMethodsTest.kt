package com.example.room_test

import com.example.room_test.entities.MicrotaskGrade
import com.example.room_test.entities.Student
import com.example.room_test.entities.StudentWithRelations
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
                StudentWithRelations,
                MockStudentDTO>()
{
    private val instructorId = Long.newId()

    private val utils = MockStudentUtils(MockStudentDTO::class.java)
    override val mapEntity: (StudentWithRelations) -> MockStudentDTO
        get() = { utils.realization.mapEntities(listOf(it)).first() }
    override val mapFields: (MockStudentDTO) -> Student
        get() = { utils.realization.mapFields(it).first() }
    override val newEntityWithRelations: StudentWithRelations
        get() {
            val student = MockEntityGenerator.students(instructorId).random()
            val grades = (0..3).fold(emptyList<Long>()) { acc, _ ->
                acc + MockEntityGenerator
                    .microtaskGradeMocks(Long.newId(), student.id)
                    .map(MicrotaskGrade::id::get)
            }
            return StudentWithRelations(student, grades)
        }
    override val newDTO: MockStudentDTO
        get() {
            return MockStudentDTO.new()
        }
}