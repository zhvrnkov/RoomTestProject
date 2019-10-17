package com.example.room_test.mock_dtos

import com.example.room_test.entity_utils.RubricFields

data class MockRubricDTO(
    override var id: Long = -1,
    override var title: String = "Bad title",
    override var grades: List<MockGradeDTO> = emptyList()
) : RubricFields<MockGradeDTO>