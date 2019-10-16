package com.example.room_test.entity_utils

interface BaseDao<Entity, EntityWithRelations> {
    fun get(ids: List<Long>): List<EntityWithRelations>
    fun delete(ids: List<Long>)
    fun update(entity: Entity)
    fun insert(entity: Entity)
}

interface EntityUtils<EntityFields> {
    fun get(ids: List<Long>): List<EntityFields>
    fun delete(ids: List<Long>)
    fun update(item: EntityFields)
    fun insert(item: EntityFields)
}

interface Foo {
    fun bar(): Int
}

internal interface EntityUtilsRealization<
        Entity, EntityWithRelations, EntityFields, Dao: BaseDao<Entity, EntityWithRelations>>
{
    val dao: Dao

    fun mapEntity(entity: EntityWithRelations): EntityFields
    fun mapFields(fields: EntityFields): Entity

    fun pGet(ids: List<Long>): List<EntityFields> {
        return dao.get(ids).map { mapEntity(it) }
    }

    fun pDelete(ids: List<Long>) {
        dao.delete(ids)
    }

    fun pUpdate(item: EntityFields) {
        dao.update(mapFields(item))
    }

    fun pInsert(item: EntityFields) {
        dao.insert(mapFields(item))
    }
}

abstract class BaseUtils<
        DTO,
        Entity, EntityWithRelations,
        DAO : BaseDao<Entity, EntityWithRelations>> :
    EntityUtils<DTO>,
    EntityUtilsRealization<Entity, EntityWithRelations, DTO, DAO>
{
    override fun get(ids: List<Long>): List<DTO> = pGet(ids)
    override fun delete(ids: List<Long>) = pDelete(ids)
    override fun update(item: DTO) = pUpdate(item)
    override fun insert(item: DTO) = pInsert(item)
}
