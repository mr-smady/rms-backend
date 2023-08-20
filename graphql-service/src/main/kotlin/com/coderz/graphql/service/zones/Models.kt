package com.coderz.graphql.service.zones

data class Zone(
    val id: Int?,
    val nameAr: String?,
    val nameEn: String?,
    val zoneTypeId: Int?,
)

data class ZonePoint(
    val id: Int?,
    val latitude: Double?,
    val longitude: Double?,
    val zoneId: Int?,
)