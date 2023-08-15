package com.coderz.trips.service

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Trip(
    @Id val id: Int?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val distanceCovered: Int?,
    val vehicleId: Int?,
    val avgSpeed: Int?,
    val maxSpeed: Int?,
    val minSpeed: Int?,
)