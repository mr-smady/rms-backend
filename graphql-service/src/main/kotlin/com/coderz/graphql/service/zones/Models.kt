package com.coderz.graphql.service.zones

import java.time.LocalDateTime

data class Zone(
    val id: Int?,
    val nameAr: String?,
    val nameEn: String?,
    val zoneTypeId: Int?,
    val lastUpdateDate : LocalDateTime?,
    val creationDate : LocalDateTime

)

data class ZonePoint(
    val id: Int?,
    val latitude: Double?,
    val longitude: Double?,
    val zoneId: Int?,
)

data class ZonesType(
    val id: Int ,
    val nameAr: String ? ,
    val nameEn: String ? ,
    val deleted: Boolean ? ,
    val zonesCount : Int  ? = 0  ,
   )