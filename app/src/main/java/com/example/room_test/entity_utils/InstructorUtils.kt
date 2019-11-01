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
    @Query("select * from ${Tables.instructors}")
    abstract override fun getAll(): List<Instructor>

    @Query("select * from ${Tables.instructors} where id in (:ids)")
    abstract override fun get(ids: List<Long>): List<Instructor>

    @Query("delete from ${Tables.instructors} where id in (:ids)")
    abstract override fun delete(ids: List<Long>)

    @Update
    abstract override fun update(vararg entity: Instructor)

    @Insert
    abstract override fun insert(vararg entity: Instructor)
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
            dtoClass: Class<InstructorDTO>
        ): (Instructor) -> InstructorDTO = {
            dtoClass.constructors.first().newInstance(
                it.id,
                it.address,
                it.address2,
                it.avatar,
                it.city,
                it.country,
                it.credentials,
                it.depiction,
                it.email,
                it.firstName,
                it.lang,
                it.lastName,
                it.loginUsername,
                it.nauticedStatus,
                it.phone,
                it.phoneStudent,
                it.state,
                it.zip,
                it.schoolId,
                it.assessmentIds,
                it.studentIds,
                it.gradeColors,
                it.flags,
                it.fbid
            ) as InstructorDTO
        }
    }

    override val realization =
        object : EntityUtilsRealization<
                Instructor,
                Instructor,
                InstructorDTO,
                BaseDao<Instructor, Instructor>>
        {
            override val dao = dao
            override fun mapEntities(entities: List<Instructor>) = entities.map(staticMapEntity(dtoClass))
            override fun mapFields(vararg fields: InstructorDTO) = fields.map(::staticMapFields).toTypedArray()
        }
}