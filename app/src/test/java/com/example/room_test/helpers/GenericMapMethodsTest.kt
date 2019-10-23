package com.example.room_test.helpers

import com.example.room_test.entity_utils.EntityUtils
import com.example.room_test.entity_utils.EntityUtilsRealization
import org.junit.Assert.*

import org.junit.Test

abstract class GenericMapMethodsTest
<Entity, EntityWithRelations, DTO> {
    abstract val mapEntity: (EntityWithRelations) -> DTO
    abstract val mapFields: (DTO) -> Entity
    abstract val newEntityWithRelations: EntityWithRelations
    abstract val newDTO: DTO

    @Test
    fun testMapEntity() {
        val entity = newEntityWithRelations
        val dto = mapEntity(entity)
        assertTrue(dto?.equals(entity) ?: false)
    }

    @Test
    fun testMapFields() {
        val dto = newDTO
        val entity = mapFields(dto)
        assertTrue(dto?.equals(entity) ?: false)
    }
}