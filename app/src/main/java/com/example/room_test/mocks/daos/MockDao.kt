package com.example.room_test.utils

import com.example.room_test.entities.*
import com.example.room_test.entity_utils.*

abstract class MockDao<Entity, EntityWithRelations> :
    BaseDao<Entity, EntityWithRelations> {
    private val storage = mutableListOf<EntityWithRelations>()
    abstract val deleteFilterPredicate: (ids: List<Long>, item: EntityWithRelations) -> Boolean
    abstract val getEntityPredicate:
                (entity: Entity, entityWithRelation: EntityWithRelations) -> Boolean
    abstract val replaceEntityWithRelations: (
        entity: Entity, old: EntityWithRelations
    ) -> EntityWithRelations
    abstract val getEntityWithRelation: (entity: Entity) -> EntityWithRelations

    override fun delete(ids: List<Long>) {
        val newItems = storage.filter { deleteFilterPredicate(ids, it) }
        storage.clear()
        storage.addAll(newItems)
    }

    override fun update(entity: Entity) {
        val index = storage.indexOfFirst { getEntityPredicate(entity, it) }
        val storedItem = storage[index]
        val newItem = replaceEntityWithRelations(entity, storedItem)
        storage[index] = newItem
    }

    override fun insert(entity: Entity) {
        storage.add(getEntityWithRelation(entity))
    }

    override fun get(ids: List<Long>): List<EntityWithRelations> {
        return storage.filter { !deleteFilterPredicate(ids, it) }
    }

    override fun getAll(): List<EntityWithRelations> {
        return storage
    }
}

internal class MockRubricDao : MockDao<Rubric, RubricWithRelations>() {
    override val deleteFilterPredicate: (ids: List<Long>, item: RubricWithRelations) -> Boolean
        get() = { ids, item -> item.rubric.id !in ids }

    override val getEntityPredicate:
                (entity: Rubric, entityWithRelation: RubricWithRelations) -> Boolean
        get() = { entity, entityWR -> entity.id == entityWR.rubric.id }

    override val replaceEntityWithRelations: (
        entity: Rubric, old: RubricWithRelations) -> RubricWithRelations
        get() = { entity, old -> RubricWithRelations(entity, old.grades, old.skillSets) }

    override val getEntityWithRelation: (entity: Rubric) -> RubricWithRelations
        get() = { entity -> RubricWithRelations(entity, emptyList(), emptyList()) }
}

internal class MockSkillSetDao : MockDao<SkillSet, SkillSetWithRelations>() {
    override val deleteFilterPredicate: (ids: List<Long>, item: SkillSetWithRelations) -> Boolean
        get() = { ids, item -> item.skillSet.id !in ids }

    override val getEntityPredicate: (
        entity: SkillSet, entityWithRelation: SkillSetWithRelations) -> Boolean
        get() = { entity, entityWR -> entity.id == entityWR.skillSet.id }

    override val replaceEntityWithRelations: (
        entity: SkillSet, old: SkillSetWithRelations) -> SkillSetWithRelations
        get() = { entity, old -> SkillSetWithRelations(entity, old.microtasks) }

    override val getEntityWithRelation: (entity: SkillSet) -> SkillSetWithRelations
        get() = { entity -> SkillSetWithRelations(entity, emptyList()) }
}

internal class MockMicrotaskDao : MockDao<Microtask, Microtask>() {
    override val deleteFilterPredicate: (ids: List<Long>, item: Microtask) -> Boolean
        get() = { ids, item -> item.id in ids }

    override val getEntityPredicate: (entity: Microtask, entityWithRelation: Microtask) -> Boolean
        get() = { entity, entityWR -> entity.id == entityWR.id }

    override val replaceEntityWithRelations: (entity: Microtask, old: Microtask) -> Microtask
        get() = { entity, _ -> entity }

    override val getEntityWithRelation: (entity: Microtask) -> Microtask
        get() = { entity -> entity }
}

internal class MockInstructorDao : MockDao<Instructor, Instructor>() {
    override val deleteFilterPredicate: (ids: List<Long>, item: Instructor) -> Boolean
        get() = { ids, item -> item.id in ids }

    override val getEntityPredicate:
                (entity: Instructor, entityWithRelation: Instructor) -> Boolean
        get() = { entity, entityWR -> entity.id == entityWR.id }

    override val replaceEntityWithRelations: (entity: Instructor, old: Instructor) -> Instructor
        get() = { entity, _ -> entity }

    override val getEntityWithRelation: (entity: Instructor) -> Instructor
        get() = { entity -> entity }
}

internal class MockAssessmentDao : MockDao<Assessment, AssessmentWithRelations>() {
    override val deleteFilterPredicate: (ids: List<Long>, item: AssessmentWithRelations) -> Boolean
        get() = { ids, item -> item.assessment.id in ids }

    override val getEntityPredicate:
                (entity: Assessment, entityWithRelation: AssessmentWithRelations) -> Boolean
        get() = { entity, entityWR -> entity.id == entityWR.assessment.id }

    override val replaceEntityWithRelations:
                (entity: Assessment, old: AssessmentWithRelations) -> AssessmentWithRelations
        get() = { entity, old -> AssessmentWithRelations(entity, old.microtaskGrades) }

    override val getEntityWithRelation: (entity: Assessment) -> AssessmentWithRelations
        get() = { entity -> AssessmentWithRelations(entity, emptyList()) }
}

internal class MockMicrotaskGradeDao : MockDao<MicrotaskGrade, MicrotaskGrade>() {
    override val deleteFilterPredicate: (ids: List<Long>, item: MicrotaskGrade) -> Boolean
        get() = { ids, item -> item.id in ids }

    override val getEntityPredicate:
                (entity: MicrotaskGrade, entityWithRelation: MicrotaskGrade) -> Boolean
        get() = { entity, entityWR -> entity.id == entityWR.id }

    override val replaceEntityWithRelations:
                (entity: MicrotaskGrade, old: MicrotaskGrade) -> MicrotaskGrade
        get() = { entity, _ -> entity }

    override val getEntityWithRelation: (entity: MicrotaskGrade) -> MicrotaskGrade
        get() = { entity -> entity }
}

internal class MockGradeDao : MockDao<Grade, Grade>() {
    override val deleteFilterPredicate: (ids: List<Long>, item: Grade) -> Boolean
        get() = { ids, item -> item.id in ids }

    override val getEntityPredicate:
                (entity: Grade, entityWithRelation: Grade) -> Boolean
        get() = { entity, entityWR -> entity.id == entityWR.id }

    override val replaceEntityWithRelations:
                (entity: Grade, old: Grade) -> Grade
        get() = { entity, _ -> entity }

    override val getEntityWithRelation: (entity: Grade) -> Grade
        get() = { entity -> entity }
}

internal class MockStudentDao : MockDao<Student, Student>() {
    override val deleteFilterPredicate: (ids: List<Long>, item: Student) -> Boolean
        get() = { ids, item -> item.id in ids }

    override val getEntityPredicate:
                (entity: Student, entityWithRelation: Student) -> Boolean
        get() = { entity, entityWR -> entity.id == entityWR.id }

    override val replaceEntityWithRelations:
                (entity: Student, old: Student) -> Student
        get() = { entity, _ -> entity }

    override val getEntityWithRelation: (entity: Student) -> Student
        get() = { entity -> entity }
}