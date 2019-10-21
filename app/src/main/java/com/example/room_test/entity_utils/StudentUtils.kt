package com.example.room_test.entity_utils

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