package com.bpsw.cabs.view

import java.util.UUID

class CabRep(
    val id: String?,
    latitude: Double,
    longitude: Double
): LatLongRep(latitude, longitude) {

    fun getOrCreateId() : String {
        return id ?: newId()
    }

    companion object {
        fun newId() : String = UUID.randomUUID().toString()
    }

}
