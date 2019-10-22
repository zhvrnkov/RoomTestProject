package com.example.room_test.mock_dtos

import com.example.room_test.Instructor
import com.example.room_test.entity_utils.InstructorFields

data class MockInstructorDTO(
    override var id: Long = -1,
    override var address: String = "address",
    override var address2: String = "address2",
    override var avatar: String = "avatar",
    override var city: String = "city",
    override var country: String = "country",
    override var credentials: String = "credentials",
    override var depiction: String = "depiction",
    override var email: String = "email",
    override var firstName: String = "firstName",
    override var lang: String = "lang",
    override var lastName: String = "lastName",
    override var loginUsername: String = "loginUsername",
    override var nauticedStatus: String = "nauticedStatus",
    override var phone: String = "phone",
    override var phoneStudent: String = "phoneStudent",
    override var state: String = "state",
    override var zip: String = "zip",
    override var schoolId: Long = -1,
    override var assessmentIds: List<Long> = emptyList(),
    override var studentIds: List<Long> = emptyList(),
    override var gradeColors: List<String> = emptyList(),
    override var flags: List<String> = emptyList(),
    override var fbid: Map<Int, Int> = emptyMap()
) : InstructorFields {
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
