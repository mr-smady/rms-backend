package com.coderz.trips.service

import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

//private val log = KotlinLogging.logger {}

@Service
class TripsService(
    private val tripsRepository: TripsRepository,
) {
    suspend fun trips(vehicleId: Int, page: Int, size: Int): Flow<Trip> =
        tripsRepository.findAllByVehicleId(vehicleId, PageRequest.of(page, size))

    suspend fun tripsCount() =
        tripsRepository.count()
}