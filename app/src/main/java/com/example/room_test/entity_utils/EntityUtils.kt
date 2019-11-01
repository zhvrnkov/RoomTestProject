package com.example.room_test.entity_utils

interface BaseDao<in Entity, out EntityWithRelations> {
    fun getAll(): List<EntityWithRelations>
    fun get(ids: List<Long>): List<EntityWithRelations>
    fun delete(ids: List<Long>)
    fun update(vararg entity: Entity)
    fun insert(vararg entity: Entity)
}

interface EntityUtils<EntityFields> {
    fun get(type: TypeOfGet): List<EntityFields>
    fun delete(ids: List<Long>)
    fun update(vararg item: EntityFields)
    fun insert(vararg item: EntityFields)
}

internal interface EntityUtilsRealization
<Entity, EntityWithRelations, EntityFields, Dao: BaseDao<Entity, EntityWithRelations>>
{
    val dao: Dao

    fun mapEntities(entities: List<EntityWithRelations>): List<EntityFields>
    fun mapFields(vararg fields: EntityFields): Array<Entity>

    fun pGet(type: TypeOfGet) = mapEntities(
        when(type) {
            is TypeOfGet.All -> dao.getAll()
            is TypeOfGet.Certain -> dao.get(type.ids)
        }
    )

    fun pDelete(ids: List<Long>) {
        dao.delete(ids)
    }

    fun pUpdate(vararg item: EntityFields) {
        dao.update(*mapFields(*item))
    }

    fun pInsert(vararg item: EntityFields) {
        dao.insert(*mapFields(*item))
    }
}

abstract class BaseUtils<
        DTO,
        Entity, EntityWithRelations,
        DAO : BaseDao<Entity, EntityWithRelations>> :
    EntityUtils<DTO>
{
    internal abstract val realization: EntityUtilsRealization<Entity, EntityWithRelations, DTO, DAO>
    override fun get(type: TypeOfGet): List<DTO> = realization.pGet(type)
    override fun delete(ids: List<Long>) = realization.pDelete(ids)
    override fun update(vararg item: DTO) = realization.pUpdate(*item)
    override fun insert(vararg item: DTO) = realization.pInsert(*item)
}

sealed class TypeOfGet {
    object All: TypeOfGet()
    data class Certain(val ids: List<Long>): TypeOfGet()
}