package com.example.room_test.entity_utils

interface AssessmentFields<MicrotaskGradeDTO : MicrotaskGradeFields> {
    var id: Long
    var date: Long
    var isAddedToServer: Boolean
    var isSynced: Boolean

    var schooldId: Long
    var instructorId: Long
    var rubricId: Long
    var microtaskGrades: List<MicrotaskGradeDTO>
    var studentIds: List<Long>
}