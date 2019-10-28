package com.example.room_test.mock_dtos

import com.example.room_test.entities.Instructor
import com.example.room_test.entity_utils.InstructorFields

data class MockInstructorDTO(
    override val id: Long,
    override val address: String = "address",
    override val address2: String = "address2",
    override val avatar: String = "avatar",
    override val city: String = "city",
    override val country: String = "country",
    override val credentials: String = "credentials",
    override val depiction: String = "depiction",
    override val email: String = "email",
    override val firstName: String = "firstName",
    override val lang: String = "lang",
    override val lastName: String = "lastName",
    override val loginUsername: String = "loginUsername",
    override val nauticedStatus: String = "nauticedStatus",
    override val phone: String = "phone",
    override val phoneStudent: String = "phoneStudent",
    override val state: String = "state",
    override val zip: String = "zip",
    override val schoolId: Long = -1,
    override val assessmentIds: List<Long> = emptyList(),
    override val studentIds: List<Long> = emptyList(),
    override val gradeColors: List<String> = emptyList(),
    override val flags: List<String> = emptyList(),
    override val fbid: Map<Int, Int> = emptyMap()
) : InstructorFields {
    companion object {
        fun new(id: Long) = MockInstructorDTO(id = id)
    }
    override fun equals(other: Any?): Boolean {
        return when(other) {
            is Instructor -> {
                listOf(
                    id == other.id,
                    address == other.address,
                    address2 == other.address2,
                    avatar == other.avatar,
                    city == other.city,
                    country == other.country,
                    credentials == other.credentials,
                    depiction == other.depiction,
                    email == other.email,
                    firstName == other.firstName,
                    lang == other.lang,
                    lastName == other.lastName,
                    loginUsername == other.loginUsername,
                    nauticedStatus == other.nauticedStatus,
                    phone == other.phone,
                    phoneStudent == other.phoneStudent,
                    state == other.state,
                    zip == other.zip,
                    schoolId == other.schoolId,
                    assessmentIds == other.assessmentIds,
                    studentIds == other.studentIds,
                    gradeColors == other.gradeColors,
                    flags == other.flags,
                    fbid == other.fbid
                ).all { it }
            }
            else -> super.equals(other)
        }
    }
}
