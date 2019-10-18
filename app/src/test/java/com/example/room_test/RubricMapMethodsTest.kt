package com.example.room_test

import com.example.room_test.entity_utils.GradeFields
import com.example.room_test.entity_utils.RubricDao
import com.example.room_test.entity_utils.RubricUtils
import com.example.room_test.entity_utils.SkillSetFields
import com.example.room_test.helpers.GenericMapMethodsTest
import com.example.room_test.helpers.Mapper
import com.example.room_test.mock_dtos.*
import java.util.*

fun rubricMock(): Rubric = Rubric(Long.newId(), "Lorem")
fun gradeMocks(rubricId: Long): List<Grade> = (0..10).map {
    Grade(Long.newId(), score = it, title = "$it", rubricId = rubricId)
}
fun skillSetMocks(rubricId: Long): List<SkillSet> = (0..10).map {
    SkillSet(Long.newId(), "$it", rubricId)
}

class MockRubricUtils(dao: RubricDao) :
    RubricUtils<MockGradeDTO, MockRubricDTO>(dao, MockRubricDTO::class.java, MockGradeDTO::class.java),
    Mapper<Rubric, RubricWithRelations, MockRubricDTO>

class MockRubricDao : RubricDao() {
    private val storage = mutableListOf<RubricWithRelations>()

    override fun pDelete(ids: List<Long>) {
        val newItems = storage.filter { !ids.contains(it.rubric.id) }
        storage.clear()
        storage.addAll(newItems)
    }

    override fun pUpdate(entity: Rubric) {
        val index = storage.indexOfFirst { it.rubric.id == entity.id }
        val storedItem = storage[index]
        val newItem = RubricWithRelations(entity, storedItem.grades, storedItem.skillSets)
        storage[index] = newItem
    }

    override fun pInsert(entity: Rubric) {
        storage.add(RubricWithRelations(entity, emptyList(), emptyList()))
    }

    override fun pGet(ids: List<Long>): List<RubricWithRelations> {
        return storage.filter { ids.contains(it.rubric.id) }
    }
}

class RubricMapMethodsTest :
    GenericMapMethodsTest<Rubric, RubricWithRelations, MockRubricDTO, MockRubricUtils>()
{
    override val utils: MockRubricUtils = (fun(): MockRubricUtils {
        return MockRubricUtils(dao = MockRubricDao())
    })()

    override val newEntityWithRelations: RubricWithRelations
        get() {
            val rubric = rubricMock()
            val grades = gradeMocks(rubric.id)
            val skillSets = skillSetMocks(rubric.id)
            return RubricWithRelations(rubric, grades, emptyList())
        }

    override val newDTO: MockRubricDTO
        get() {
            val rubric = MockRubricDTO.new()
            rubric.grades = (0..10).map { MockGradeDTO.new(rubric.id) }
            return rubric
        }
}