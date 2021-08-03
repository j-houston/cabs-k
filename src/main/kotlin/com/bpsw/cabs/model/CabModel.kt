package com.bpsw.cabs.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object CabTable : UUIDTable() {
    val latitude: Column<Float> = float("latitude")
    val longitude: Column<Float> = float("longitude")
}

class CabModel(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<CabModel>(CabTable)
    var latitude by CabTable.latitude
    var longitude by CabTable.longitude
}

