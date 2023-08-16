package com.coderz.graphql.service.locations

import java.time.LocalDateTime

data class Location(
     val id: Int?,
    val vehicleId: Int?,
    val latitude: Double?,
    val longitude: Double?,
    val speed: Double?,
    val ignition: Boolean?,
    val receivedServerTime: LocalDateTime?,
)