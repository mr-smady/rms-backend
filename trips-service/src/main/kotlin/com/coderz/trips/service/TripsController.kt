package com.coderz.trips.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TripsController(
    private val tripsService: TripsService,
) {

    @GetMapping("/{vehicleId}")
    suspend fun trips(
        @PathVariable vehicleId: Int,
        @RequestParam(
            required = false,
            defaultValue = "0"
        ) page: Int,
        @RequestParam(
            required = false,
            defaultValue = "100"
        ) size: Int,
    ) = tripsService.trips(vehicleId, page, size)

    @GetMapping("/count")
    suspend fun tripsCount() =
        tripsService.tripsCount()
}