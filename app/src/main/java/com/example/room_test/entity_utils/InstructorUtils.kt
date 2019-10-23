package com.example.room_test.entity_utils

import androidx.room.Dao
import androidx.room.*
import com.example.room_test.Tables
import com.example.room_test.entities.Instructor

interface InstructorFields {
    var id: Long
    var address: String
    var address2: String
    var avatar: String
    var city: String
    var country: String
    var credentials: String
    var depiction: String
    var email: String
    var firstName: String
    var lang: String
    var lastName: String
    var loginUsername: String
    var nauticedStatus: String
    var phone: String
    var phoneStudent: String
    var state: String
    var zip: String

    var schoolId: Long
    var assessmentIds: List<Long>
    var studentIds: List<Long>

    var gradeColors: List<String>
    var flags: List<String>
    var fbid: Map<Int, Int>
}

@Dao
abstract class InstructorDao : BaseDao<Instructor, Instructor> {
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
}

open class InstructorUtils<InstructorDTO : InstructorFields>(
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
            val dto = dtoClass.newInstance()
            dto.id = entity.id
            dto.address = entity.address
            dto.address2 = entity.address2
            dto.avatar = entity.avatar
            dto.city = entity.city
            dto.country = entity.country
            dto.credentials = entity.credentials
            dto.depiction = entity.depiction
            dto.email = entity.email
            dto.firstName = entity.firstName
            dto.lang = entity.lang
            dto.lastName = entity.lastName
            dto.loginUsername = entity.loginUsername
            dto.nauticedStatus = entity.nauticedStatus
            dto.phone = entity.phone
            dto.phoneStudent = entity.phoneStudent
            dto.state = entity.state
            dto.zip = entity.zip
            dto.schoolId = entity.schoolId
            dto.assessmentIds = entity.assessmentIds
            dto.studentIds = entity.studentIds
            dto.gradeColors = entity.gradeColors
            dto.flags = entity.flags
            dto.fbid = entity.fbid

            return dto
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