package com.example.room_test.entity_utils

import androidx.room.Dao
import androidx.room.*
import com.example.room_test.Tables
import com.example.room_test.entities.Instructor

interface InstructorFields {
    val id: Long
    val address: String
    val address2: String
    val avatar: String
    val city: String
    val country: String
    val credentials: String
    val depiction: String
    val email: String
    val firstName: String
    val lang: String
    val lastName: String
    val loginUsername: String
    val nauticedStatus: String
    val phone: String
    val phoneStudent: String
    val state: String
    val zip: String
    val schoolId: Long
    val assessmentIds: List<Long>
    val studentIds: List<Long>
    val gradeColors: List<String>
    val flags: List<String>
    val fbid: Map<Int, Int>
}

@Dao
internal abstract class InstructorDao : BaseDao<Instructor, Instructor> {
    override fun getAll(): List<Instructor> = pGet()
    override fun get(ids: List<Long>): List<Instructor> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(entity: Instructor) = pUpdate(entity)
    override fun insert(entity: Instructor) = pInsert(entity)

    @Query("delete from ${Tables.instructors} where id in (:ids)")
    abstract fun pDelete(ids: List<Long>)

    @Update
    abstract fun pUpdate(entity: Instructor)

    @Insert
    abstract fun pInsert(entity: Instructor)

    @Query("select * from ${Tables.instructors} where id in (:ids)")
    abstract fun pGet(ids: List<Long>): List<Instructor>

    @Query("select * from ${Tables.instructors}")
    abstract fun pGet(): List<Instructor>
}

internal open class InstructorUtils<InstructorDTO : InstructorFields>(
    dao: BaseDao<Instructor, Instructor>,
    private val dtoClass: Class<InstructorDTO>
) : BaseUtils<
        InstructorDTO,
        Instructor,
        Instructor,
        BaseDao<Instructor, Instructor>>()
{
    internal companion object {
        fun <InstructorDTO : InstructorFields> staticMapFields(
            fields: InstructorDTO): Instructor {
            return Instructor(
                id = fields.id,
                address = fields.address,
                address2 = fields.address2,
                avatar = fields.avatar,
                city = fields.city,
                country = fields.country,
                credentials = fields.credentials,
                depiction = fields.depiction,
                email = fields.email,
                firstName = fields.firstName,
                lang = fields.lang,
                lastName = fields.lastName,
                loginUsername = fields.loginUsername,
                nauticedStatus = fields.nauticedStatus,
                phone = fields.phone,
                phoneStudent = fields.phoneStudent,
                state = fields.state,
                zip = fields.zip,
                schoolId = fields.schoolId,
                assessmentIds = fields.assessmentIds,
                studentIds = fields.studentIds,
                gradeColors = fields.gradeColors,
                flags = fields.flags,
                fbid = fields.fbid
            )
        }

        fun <InstructorDTO : InstructorFields> staticMapEntity(
            entity: Instructor,
            dtoClass: Class<InstructorDTO>
        ): InstructorDTO {
            return dtoClass.constructors.first().newInstance(
                entity.id,
                entity.address,
                entity.address2,
                entity.avatar,
                entity.city,
                entity.country,
                entity.credentials,
                entity.depiction,
                entity.email,
                entity.firstName,
                entity.lang,
                entity.lastName,
                entity.loginUsername,
                entity.nauticedStatus,
                entity.phone,
                entity.phoneStudent,
                entity.state,
                entity.zip,
                entity.schoolId,
                entity.assessmentIds,
                entity.studentIds,
                entity.gradeColors,
                entity.flags,
                entity.fbid
            ) as InstructorDTO
        }
    }

    override val realization = object : EntityUtilsRealization<
            Instructor,
            Instructor,
            InstructorDTO,
            BaseDao<Instructor, Instructor>>
    {
        override val dao = dao
        override fun mapEntity(entity: Instructor) = staticMapEntity(
            entity, dtoClass)

        override fun mapFields(fields: InstructorDTO) = staticMapFields(fields)
    }
}