package com.coderz.graphql.service.locations

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class LocationsClient(
    @Qualifier("locationsServiceClient") private val client: WebClient
) {
    suspend fun locations(vehicleId: Int, page: Int, size: Int): List<Location> =
        client.get()
            .uri("/$vehicleId?page=$page&size=$size")
            .retrieve()
            .awaitBody()

}