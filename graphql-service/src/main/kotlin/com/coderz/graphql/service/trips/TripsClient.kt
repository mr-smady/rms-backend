package com.coderz.graphql.service.trips

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class TripsClient(
    @Qualifier("tripsServiceClient") private val client: WebClient
) {
    suspend fun trips(vehicleId: Int, page: Int, size: Int): List<Trip> =
        client.get()
            .uri("/$vehicleId?page=$page&size=$size")
            .retrieve()
            .awaitBody()

    suspend fun tripsCount(): Long =
        client.get()
            .uri("/count")
            .retrieve()
            .awaitBody()
}