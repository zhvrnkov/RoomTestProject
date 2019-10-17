package com.example.room_test.mock_dtos

import com.example.room_test.entity_utils.GradeFields

data class MockGradeDTO(
    override var id: Long = -1,
    override var title: String = "Bad title",
    override var rubricId: Long = -1,
    override var score: Int = 0
) : GradeFields