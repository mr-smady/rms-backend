package com.coderz.graphql.service.alarms

import java.time.LocalDateTime

data class ZoneType(
    val id: Int? = null,
    val nameAr: String? = null,
    val nameEn: String? = null,
    val deleted:Boolean? = null,
)

data class AlarmType(
    val id: Int? = null,
    val nameAr: String? = null,
    val nameEn: String? = null,
    val deleted:Boolean? = null,
)

data class Alarm(
    val id: Int?,
    val typeId: Int?,
    val number: Int?,
    val zoneTypeId: Int?,
    val vehicleId: Int?,
    val companyId: Int?,
    val time: LocalDateTime?,
    val statusId: Int?,
)