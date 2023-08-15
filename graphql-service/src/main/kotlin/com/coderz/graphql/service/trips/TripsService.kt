package com.coderz.graphql.service.trips

import org.springframework.stereotype.Service

@Service
class TripsService(
    private val tripsClient: TripsClient
) {
    suspend fun trips(vehicleId: Int, page: Int, size: Int) =
        tripsClient.trips(vehicleId, page, size)

    suspend fun tripsCount() =
        tripsClient.tripsCount()
}