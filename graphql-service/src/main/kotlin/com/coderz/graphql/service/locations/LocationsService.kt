package com.coderz.graphql.service.locations

import org.springframework.stereotype.Service

@Service
class LocationsService(
    private val locationsClient: LocationsClient
) {
    suspend fun locations(vehicleId: Int, page: Int, size: Int) =
        locationsClient.locations(vehicleId, page, size)
}