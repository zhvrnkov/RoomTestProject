package com.example.room_test.helpers

import com.example.room_test.Rubric
import com.example.room_test.RubricWithRelations
import com.example.room_test.SkillSet
import com.example.room_test.SkillSetWithRelations
import com.example.room_test.entity_utils.*

abstract class MockDao<Entity, EntityWithRelations> :
    BaseDao<Entity, EntityWithRelations>
{
    private val storage = mutableListOf<EntityWithRelations>()
    abstract val deleteFilterPredicate: (ids: List<Long>, item: EntityWithRelations) -> Boolean
    abstract val getEntityPredicate: (entity: Entity, entityWithRelation: EntityWithRelations) -> Boolean
    abstract val replaceEntityWithRelations: (entity: Entity, old: EntityWithRelations) -> EntityWithRelations
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
}

class MockRubricDao : MockDao<Rubric, RubricWithRelations>() {
    override val deleteFilterPredicate: (ids: List<Long>, item: RubricWithRelations) -> Boolean
        get() = { ids, item -> item.rubric.id !in ids }

    override val getEntityPredicate: (entity: Rubric, entityWithRelation: RubricWithRelations) -> Boolean
        get() = { entity, entityWR -> entity.id == entityWR.rubric.id }

    override val replaceEntityWithRelations: (entity: Rubric, old: RubricWithRelations) -> RubricWithRelations
        get() = { entity, old -> RubricWithRelations(entity, old.grades, old.skillSets) }

    override val getEntityWithRelation: (entity: Rubric) -> RubricWithRelations
        get() = { entity -> RubricWithRelations(entity, emptyList(), emptyList()) }
}

class MockSkillSetDao : MockDao<SkillSet, SkillSetWithRelations>() {
    override val deleteFilterPredicate: (ids: List<Long>, item: SkillSetWithRelations) -> Boolean
        get() = { ids, item -> item.skillSet.id !in ids }

    override val getEntityPredicate: (entity: SkillSet, entityWithRelation: SkillSetWithRelations) -> Boolean
        get() = { entity, entityWR -> entity.id == entityWR.skillSet.id }

    override val replaceEntityWithRelations: (entity: SkillSet, old: SkillSetWithRelations) -> SkillSetWithRelations
        get() = { entity, old -> SkillSetWithRelations(entity, old.microtasks) }

    override val getEntityWithRelation: (entity: SkillSet) -> SkillSetWithRelations
        get() = { entity -> SkillSetWithRelations(entity, emptyList()) }
}