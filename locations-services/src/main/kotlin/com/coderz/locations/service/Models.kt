package com.coderz.locations.service

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Location(
    @Id val id: Int?,
    val vehicleId: Int?,
    val latitude: Double?,
    val longitude: Double?,
    val speed: Double?,
    val ignition: Boolean?,
    val receivedServerTime: LocalDateTime?,
)