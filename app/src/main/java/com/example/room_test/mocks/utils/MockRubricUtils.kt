package com.example.room_test.mocks.utils

import com.example.room_test.entity_utils.*
import com.example.room_test.entity_utils.RubricUtils
import com.example.room_test.mocks.daos.MockRubricDao
import com.example.room_test.mocks.dtos.MockGradeDTO
import com.example.room_test.mocks.dtos.MockMicrotaskDTO
import com.example.room_test.mocks.dtos.MockRubricDTO
import com.example.room_test.mocks.dtos.MockSkillSetDTO

internal class MockRubricUtils
<T: GradeFields, Y: MicrotaskFields, S: SkillSetFields<Y>, R: RubricFields<T, Y, S>>(
    closure: (TypeOfGet) -> List<S>,
    gradeClass: Class<T>,
    microtaskClass: Class<Y>,
    skillSetClass: Class<S>,
    rubricClass: Class<R>
) : RubricUtils<T, Y, S, R>(
        MockRubricDao(),
        rubricClass,
        gradeClass,
        skillSetClass,
        microtaskClass,
        closure
    )