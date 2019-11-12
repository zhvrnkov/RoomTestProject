package com.example.room_test.entity_utils

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.room_test.Tables
import com.example.room_test.entities.Student
import com.example.room_test.entities.StudentWithRelations

interface StudentFields {
    val id: Long
    val name: String
    val email: String
    val level: String
    val logbookPass: String
    val qualifiedDays: Int
    val rank: String
    val instructorId: Long
    val microtaskGradeIds: List<Long>
}

@Dao
internal abstract class StudentDao : BaseDao<Student, StudentWithRelations> {
    override val tableName: String
        get() = Tables.students

    @Query("select * from ${Tables.students}")
    abstract override fun getAll(): List<StudentWithRelations>

    @Query("select * from ${Tables.students} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<StudentWithRelations>

    @Query("delete from ${Tables.students} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(vararg entity: Student)

    @Insert
    abstract override fun insert(vararg entity: Student)

    @RawQuery
    abstract override fun pget(query: SupportSQLiteQuery): List<StudentWithRelations>
}

internal open class StudentUtils<StudentDTO : StudentFields>(
    dao: BaseDao<Student, StudentWithRelations>,
    private val dtoClass: Class<StudentDTO>
) : BaseUtils<StudentDTO, Student, StudentWithRelations, BaseDao<Student, StudentWithRelations>>()
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
                fields.instructorId)
        }

        fun <StudentDTO : StudentFields> staticMapEntity(
            dtoClass: Class<StudentDTO>
        ): (StudentWithRelations) -> StudentDTO = {
            dtoClass.constructors.first().newInstance(
                it.student.id,
                it.student.name,
                it.student.email,
                it.student.level,
                it.student.logbookPass,
                it.student.qualifiedDays,
                it.student.rank,
                it.student.instructorId,
                it.microtaskGradeIds
            ) as StudentDTO
        }
    }

    override val realization =
        object: EntityUtilsRealization<
            Student,
            StudentWithRelations,
            StudentDTO,
            BaseDao<Student, StudentWithRelations>>
        {
            override val dao = dao
            override fun mapFields(vararg fields: StudentDTO) =
                fields.map(::staticMapFields).toTypedArray()

            override fun mapEntities(entities: List<StudentWithRelations>) = entities.map(staticMapEntity(dtoClass))
        }
}