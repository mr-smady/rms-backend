package com.coderz.locations.service

import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

//private val log = KotlinLogging.logger {}

@Service
class LocationsService(
    private val locationsRepository: LocationsRepository,
) {
    suspend fun locations(
        vehicleId: Int,
        from: LocalDateTime,
        to: LocalDateTime,
        page: Int,
        size: Int,
    ): Flow<Location> =
        locationsRepository.findAllByVehicleIdAndReceivedServerTimeBetween(
            vehicleId, from, to, PageRequest.of(page, size)
        )

    suspend fun rowData(vehicleId: Int): Flow<Location> =locationsRepository.findByVehicleId(
        vehicleId,
        PageRequest.of(
            0, 5,
            Sort.by(Location::receivedServerTime.name).descending()
        )
    )

}