package com.coderz.graphql.service.vehicles

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitBodyOrNull

@Component
class VehiclesClient(
    @Qualifier("vehiclesServiceClient") private val client: WebClient
) {
    suspend fun vehicles(page: Int, size: Int): List<Vehicle> =
        client.get()
            .uri("?page=$page&size=$size")
            .retrieve()
            .awaitBody()

    suspend fun avlLastData(page: Int, size: Int): List<AvlLastData> =
        client.get()
            .uri("/avl-last-data?page=$page&size=$size")
            .retrieve()
            .awaitBody()



    suspend fun vehicle(id: Int): Vehicle? =
        client.get()
            .uri("/${id}")
            .retrieve()
            .awaitBodyOrNull()


    suspend fun vehicleDescription(id: Int): VehicleDescription? =
        client.get()
            .uri("/vehicle-description/${id}")
            .retrieve()
            .awaitBodyOrNull()

    suspend fun wasteType(wasteTypeId: Int): WasteType? =
        client.get()
            .uri("/waste-type/${wasteTypeId}")
            .retrieve()
            .awaitBodyOrNull()

    suspend fun vehiclesCount(): Long =
        client.get()
            .uri("/count")
            .retrieve()
            .awaitBody()

    suspend fun movementStatus(movementStatusId: Int): VehicleMovementStatus? =
        client.get()
            .uri("/movement-status/${movementStatusId}")
            .retrieve()
            .awaitBodyOrNull()
}