package com.coderz.vehicles.service

import org.springframework.stereotype.Service

@Service
class VehicleDescriptionService(
    private val vehicleDescriptionRepository:VehicleDescriptionRepository,
) {
    suspend fun findById(id: Int) = vehicleDescriptionRepository.findById(id)
}