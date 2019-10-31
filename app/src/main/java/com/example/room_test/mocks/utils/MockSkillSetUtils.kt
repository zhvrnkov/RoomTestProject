package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.MicrotaskFields
import com.example.room_test.entity_utils.SkillSetFields
import com.example.room_test.entity_utils.SkillSetUtils
import com.example.room_test.mocks.daos.MockSkillSetDao
import com.example.room_test.mocks.dtos.MockMicrotaskDTO
import com.example.room_test.mocks.dtos.MockSkillSetDTO

internal class MockSkillSetUtils
<M: MicrotaskFields, S: SkillSetFields<M>>(
    microtaskClass: Class<M>,
    skillSetClass: Class<S>
) : SkillSetUtils<M, S>(
    MockSkillSetDao(),
    skillSetClass,
    microtaskClass)