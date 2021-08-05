package com.bpsw.cabs.utils

class SpatialUtils {

    companion object {
        const val EARTH_RADIUS_M: Double = 6378137.0
        const val SRID: Int = 4326
    }

    fun getLocationFromSourceAndDistance(
        sourceLatitude: Double,
        sourceLongitude: Double,
        distanceMeters: Double
    ): DoubleArray {
        val azimuth = Math.random() * 360.0

        val deltaMetersN = distanceMeters * Math.sin(azimuth)
        val deltaMetersE = distanceMeters * Math.cos(azimuth)

        val offsetLatRadians: Double = deltaMetersN / EARTH_RADIUS_M
        val offsetLongRadians: Double =
            deltaMetersE / (EARTH_RADIUS_M * Math.cos(Math.toRadians(sourceLatitude)))

        val newLatitude = sourceLatitude + Math.toDegrees(offsetLatRadians)
        val newLongitude = sourceLongitude + Math.toDegrees(offsetLongRadians)

        return doubleArrayOf(
            newLatitude, newLongitude
        )
    }
}
