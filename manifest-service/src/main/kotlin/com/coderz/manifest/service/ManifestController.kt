package com.coderz.manifest.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ManifestController(
    private val manifestService: ManifestService,
) {
    @GetMapping
    suspend fun manifests(
        @RequestParam(
            required = false,
            defaultValue = "0"
        ) page: Int,
        @RequestParam(
            required = false,
            defaultValue = "100"
        ) size: Int,
    ) = manifestService.manifests(page, size)

    @GetMapping("/manifest-status/{id}")
    suspend fun manifestStatus(@PathVariable id: Int) =
        manifestService.manifestStatus(id)

    @GetMapping("/waste-producer/{id}")
    suspend fun wasteProducer(@PathVariable id: Int) =
        manifestService.wasteProducer(id)

    @GetMapping("/count")
    suspend fun manifestCount() =
        manifestService.manifestCount()

    @GetMapping("/vehicle-manifests/{vehicleId}")
    suspend fun vehicleManifests(@PathVariable vehicleId: Int) =
        manifestService.vehicleManifests(vehicleId)


}