package com.coderz.zones.service

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Zone(
    @Id val id: Int?,
    val nameAr: String?,
    val nameEn: String?,
    val zoneTypeId: Int?,
    val lastUpdateDate : LocalDateTime ,
    val creationDate : LocalDateTime

    )

data class ZonePoint(
    @Id val id: Int?,
    val latitude: Double?,
    val longitude: Double?,
    val zoneId: Int?,
)

data class ZoneType(
    @Id val id: Int?,
    val nameAr: String?,
    val nameEn : String ? ,
    val deleted : Boolean ,
    val zonesCount : Int ? = 0
)