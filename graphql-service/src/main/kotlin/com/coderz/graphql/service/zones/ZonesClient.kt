package com.coderz.graphql.service.zones

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class ZonesClient(
    @Qualifier("zonesServiceClient") private val client: WebClient
) {
    suspend fun zones(zoneTypeId: Int): List<Zone>? =
        client.get()
            .uri("?zoneTypeId=$zoneTypeId")
            .retrieve()
            .awaitBody()

    suspend fun zone(zoneId: Int): Zone? =
        client.get()
            .uri("/$zoneId")
            .retrieve()
            .awaitBody()

    suspend fun points(zoneId: Int): List<ZonePoint>? =
        client.get()
            .uri("/points/$zoneId")
            .retrieve()
            .awaitBody()

}