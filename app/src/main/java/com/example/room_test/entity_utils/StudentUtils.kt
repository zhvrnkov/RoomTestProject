package com.example.room_test.entity_utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room_test.Tables
import com.example.room_test.entities.Student

interface StudentFields {
    val id: Long
    val name: String
    val email: String
    val level: String
    val logbookPass: String
    val qualifiedDays: Int
    val rank: String
    val instructorId: Long
    val assessmentIds: List<Long>
    val microtaskGradeIds: List<Long>
}

@Dao
internal abstract class StudentDao : BaseDao<Student, Student> {
    @Query("select * from ${Tables.students}")
    abstract override fun getAll(): List<Student>

    @Query("select * from ${Tables.students} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<Student>

    @Query("delete from ${Tables.students} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(vararg entity: Student)

    @Insert
    abstract override fun insert(vararg entity: Student)
}

internal open class StudentUtils<StudentDTO : StudentFields>(
    dao: BaseDao<Student, Student>,
    private val dtoClass: Class<StudentDTO>
) : BaseUtils<StudentDTO, Student, Student, BaseDao<Student, Student>>()
{
    internal companion object {
        fun <StudentDTO : StudentFields> staticMapFields(
            fields: StudentDTO
        ): Student {
            return Student(
                fields.id,
                fields.name,
                fields.email,
                fields.level,
                fields.logbookPass,
                fields.qualifiedDays,
                fields.rank,
                fields.instructorId,
                fields.assessmentIds,
                fields.microtaskGradeIds)
        }

        fun <StudentDTO : StudentFields> staticMapEntity(
            dtoClass: Class<StudentDTO>
        ): (Student) -> StudentDTO = {
            dtoClass.constructors.first().newInstance(
                it.id,
                it.name,
                it.email,
                it.level,
                it.logbookPass,
                it.qualifiedDays,
                it.rank,
                it.instructorId,
                it.assessmentIds,
                it.microtaskGradeIds
            ) as StudentDTO
        }
    }

    override val realization =
        object: EntityUtilsRealization<
            Student,
            Student,
            StudentDTO,
            BaseDao<Student, Student>>
        {
            override val dao = dao
            override fun mapFields(vararg fields: StudentDTO) =
                fields.map(::staticMapFields).toTypedArray()

            override fun mapEntities(entities: List<Student>) = entities.map(staticMapEntity(dtoClass))
        }
}