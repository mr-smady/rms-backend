package com.coderz.graphql.service.trips

import java.time.LocalDateTime

data class Trip(
    val id: Int?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val distanceCovered: Int?,
    val vehicleId: Int?,
    val avgSpeed: Int?,
    val maxSpeed: Int?,
    val minSpeed: Int?,
)
