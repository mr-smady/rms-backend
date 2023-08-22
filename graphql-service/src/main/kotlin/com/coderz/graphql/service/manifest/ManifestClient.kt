package com.coderz.graphql.service.manifest

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitBodyOrNull

@Component
class ManifestClient(
    @Qualifier("manifestServiceClient") private val client: WebClient
) {
    suspend fun manifests(page: Int, size: Int): List<Manifest> =
        client.get()
            .uri("?page=$page&size=$size")
            .retrieve()
            .awaitBody()

    suspend fun wasteProducer(wasteProducerId: Int): WasteProducer? =
        client.get()
            .uri("/waste-producer/$wasteProducerId")
            .retrieve()
            .awaitBodyOrNull()

    suspend fun manifestStatus(statusId: Int): ManifestStatus =
        client.get()
            .uri("/manifest-status/$statusId")
            .retrieve()
            .awaitBody()

    suspend fun manifestsCount(): Long =
        client.get()
            .uri("/count")
            .retrieve()
            .awaitBody()

    suspend fun vehicleManifests(vehicleId: Int): List<Manifest>? =
        client.get()
            .uri("/vehicle-manifests/$vehicleId")
            .retrieve()
            .awaitBodyOrNull()
}