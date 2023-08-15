package com.coderz.alarms.service

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class ZoneType(
    @Id val id: Int?,
    val nameAr: String?,
    val nameEn: String?,
    val deleted:Boolean?,
)

data class AlarmType(
    @Id val id: Int?,
    val nameAr: String?,
    val nameEn: String?,
)

data class Alarm(
    @Id val id: Int?,
    val typeId: Int?,
    val number: Int?,
    val zoneTypeId: Int?,
    val vehicleId: Int?,
    val companyId: Int?,
    val time: LocalDateTime?,
    val statusId: Int?,
)