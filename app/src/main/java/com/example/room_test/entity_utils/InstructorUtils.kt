package com.example.room_test.entity_utils

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

    var gradeColors: Array<String>
    var flags: Array<String>
    var fbid: Map<Int, Int>
}