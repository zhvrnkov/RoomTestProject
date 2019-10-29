package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.SkillSetUtils
import com.example.room_test.mocks.daos.MockSkillSetDao
import com.example.room_test.mocks.dtos.MockMicrotaskDTO
import com.example.room_test.mocks.dtos.MockSkillSetDTO

internal class MockSkillSetUtils :
    SkillSetUtils<MockMicrotaskDTO, MockSkillSetDTO>(
        MockSkillSetDao(),
        MockSkillSetDTO::class.java,
        MockMicrotaskDTO::class.java
    )