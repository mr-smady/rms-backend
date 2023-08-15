package com.coderz.graphql.service.manifest

import com.coderz.graphql.service.vehicles.Vehicle
import com.coderz.graphql.service.vehicles.VehiclesService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class ManifestResolver(
    private val manifestService: ManifestService,
    private val vehiclesService: VehiclesService,
) {
    @QueryMapping
    suspend fun manifestsCount() =
        manifestService.manifestsCount()

    @QueryMapping
    suspend fun manifests(@Argument page: Int?, @Argument size: Int?) =
        manifestService.manifests(page ?: 0, size ?: 100)

    @SchemaMapping(typeName = "Manifest", field = "wasteProducer")
    suspend fun wasteProducer(manifest: Manifest) =
        manifestService.wasteProducer(manifest.wasteProducerId)

    @SchemaMapping(typeName = "Manifest", field = "status")
    suspend fun manifestStatus(manifest: Manifest) =
        manifestService.manifestStatus(manifest.statusId)

    @SchemaMapping(typeName = "Manifest", field = "wasteType")
    suspend fun wasteType(manifest: Manifest) =
        vehiclesService.wasteType(manifest.wasteTypeId)

    @SchemaMapping(typeName = "Manifest", field = "vehicle")
    suspend fun vehicle(manifest: Manifest) =
        vehiclesService.vehicle(manifest.vehicleId)

}