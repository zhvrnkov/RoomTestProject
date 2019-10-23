package com.example.room_test.entity_utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room_test.entities.Student

interface StudentFields {
    var id: Long
    var name: String
    var email: String
    var level: String
    var logbookPass: String
    var qualifiedDays: Int
    var rank: String

    var instructorId: Long
    var assessmentIds: List<Long>
    var microtaskGradeIds: List<Long>
}

@Dao
abstract class StudentDao : BaseDao<Student, Student> {
    override fun get(ids: List<Long>): List<Student> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(entity: Student) = pUpdate(entity)
    override fun insert(entity: Student) = pInsert(entity)

    @Query("delete from students where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: Student)

    @Insert
    abstract fun pInsert(entity: Student)

    @Query("select * from students where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<Student>
}

open class StudentUtils<StudentDTO : StudentFields>(
    override val dao: BaseDao<Student, Student>,
    private val dtoClass: Class<StudentDTO>
) : BaseUtils<StudentDTO, Student, Student, BaseDao<Student, Student>>()
{
    companion object {
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
            entity: Student, dtoClass: Class<StudentDTO>
        ): StudentDTO {
            val dto = dtoClass.newInstance()
            dto.id = entity.id
            dto.name = entity.name
            dto.email = entity.email
            dto.level = entity.level
            dto.logbookPass = entity.logbookPass
            dto.qualifiedDays = entity.qualifiedDays
            dto.rank = entity.rank
            dto.instructorId = entity.instructorId
            dto.assessmentIds = entity.assessmentIds
            dto.microtaskGradeIds = entity.microtaskGradeIds

            return dto
        }
    }

    override fun mapFields(fields: StudentDTO) = staticMapFields(fields)
    override fun mapEntity(entity: Student) = staticMapEntity(entity, dtoClass)
}