package com.coderz.graphql.service.locations

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LocationsService(
    private val locationsClient: LocationsClient
) {
    suspend fun locations(
        vehicleId: Int, from: LocalDateTime,
        to: LocalDateTime, page: Int, size: Int
    ) = locationsClient.locations(vehicleId, from, to, page, size)

    suspend fun locationRowData(vehicleId: Int) = locationsClient.locationRowData(vehicleId)
}