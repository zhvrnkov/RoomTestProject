package com.example.room_test.entity_utils

interface MicrotaskGradeFields {
    var id: Long
    var isSynced: Boolean
    var lastUpdate: Long

    var assessmentId: Long
    var gradeId: Long
    var microtaskId: Long
    var studentId: Long
}