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
    abstract val newDTO: DTO

    @Test
    fun testMapEntity() {
        val entity = newEntityWithRelations
        val dto = utils.mapEntity(entity)
        assertTrue(dto?.equals(entity) ?: false)
    }

    @Test
    fun testMapFields() {
        val dto = newDTO
        val entity = utils.mapFields(dto)
        assertTrue(dto?.equals(entity) ?: false)
    }
}