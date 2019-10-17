package com.example.room_test.helpers

import com.example.room_test.entity_utils.EntityUtils
import com.example.room_test.entity_utils.EntityUtilsRealization
import org.junit.Assert.*

import org.junit.Test

interface Mapper<Entity, EntityWithRelations, DTO> {
    fun mapEntity(entity: EntityWithRelations): DTO
    fun mapFields(fields: DTO): Entity
}

abstract class GenericMapMethodsTest
<Entity, EntityWithRelations, DTO, Utils : Mapper<Entity, EntityWithRelations, DTO>> {
    abstract val utils: Utils
    abstract val newEntityWithRelations: EntityWithRelations
    abstract val newEntity: Entity
    abstract val newDTO: DTO
    abstract fun compareEntity(entity: Entity, dto: DTO): Boolean
    abstract fun compareEntityWithRelations(entity: EntityWithRelations, dto: DTO): Boolean

    @Test
    fun testMapEntity() {
        val entity = newEntityWithRelations
        val dto = utils.mapEntity(entity)
        assertTrue(compareEntityWithRelations(entity, dto))
    }

    @Test
    fun testMapFields() {
        val dto = newDTO
        val entity = utils.mapFields(dto)
        assertTrue(compareEntity(entity, dto))
    }
}
