package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.RubricUtils
import com.example.room_test.entity_utils.TypeOfGet
import com.example.room_test.mocks.daos.MockRubricDao
import com.example.room_test.mocks.dtos.MockGradeDTO
import com.example.room_test.mocks.dtos.MockMicrotaskDTO
import com.example.room_test.mocks.dtos.MockRubricDTO
import com.example.room_test.mocks.dtos.MockSkillSetDTO

internal class MockRubricUtils(closure: (TypeOfGet) -> List<MockSkillSetDTO>) :
    RubricUtils<MockGradeDTO, MockMicrotaskDTO, MockSkillSetDTO, MockRubricDTO>(
        MockRubricDao(),
        MockRubricDTO::class.java,
        MockGradeDTO::class.java,
        MockSkillSetDTO::class.java,
        MockMicrotaskDTO::class.java,
        closure
    )