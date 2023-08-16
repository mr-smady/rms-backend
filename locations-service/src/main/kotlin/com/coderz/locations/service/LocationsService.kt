package com.coderz.locations.service

import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

//private val log = KotlinLogging.logger {}

@Service
class LocationsService(
    private val locationsRepository: LocationsRepository,
) {
    suspend fun locations(vehicleId: Int, page: Int, size: Int): Flow<Location> =
        locationsRepository.findAllByVehicleId(vehicleId, PageRequest.of(page, size))
}