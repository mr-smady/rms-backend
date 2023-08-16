package com.coderz.locations.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LocationsController(
    private val locationsService: LocationsService,
) {
    @GetMapping("/{vehicleId}")
    suspend fun locations(
        @PathVariable vehicleId: Int,
        @RequestParam(
            required = false,
            defaultValue = "0"
        ) page: Int,
        @RequestParam(
            required = false,
            defaultValue = "100"
        ) size: Int,

        ) = locationsService.locations(vehicleId, page, size)

}