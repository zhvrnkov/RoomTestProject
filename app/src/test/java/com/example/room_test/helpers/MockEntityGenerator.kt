package com.example.room_test.helpers

import com.example.room_test.*
import com.example.room_test.mock_dtos.newId

object MockEntityGenerator {
    fun rubricMock(): Rubric = Rubric(Long.newId(), "Lorem")
    fun gradeMocks(rubricId: Long): List<Grade> = (0..10).map {
        Grade(Long.newId(), score = it, title = "$it", rubricId = rubricId)
    }
    fun skillSetMocks(rubricId: Long): List<SkillSet> = (0..10).map {
        SkillSet(Long.newId(), "$it", rubricId)
    }
    fun microtaskMocks(skillSetId: Long): List<Microtask> = (0..10).map {
        Microtask(Long.newId(), "$it", "lorem #$it", skillSetId)
    }
}
