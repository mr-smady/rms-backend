package com.coderz.vehicles.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class VehiclesController(
    private val vehiclesService: VehiclesService,
    private val vehicleDescriptionService: VehicleDescriptionService,
) {

    @GetMapping
    suspend fun vehicles(
        @RequestParam(
            required = false,
            defaultValue = "0"
        ) page: Int,
        @RequestParam(
            required = false,
            defaultValue = "100"
        ) size: Int,
    ) =        vehiclesService.vehicles(page, size )

    @GetMapping("/{id}")
    suspend fun vehicle(@PathVariable id: Int) =
        vehiclesService.vehicle(id)

    @GetMapping("/vehicle-description/{id}")
    suspend fun findVehicleDescriptionById(@PathVariable id: Int) =
        vehicleDescriptionService.findById(id)


    @GetMapping("/waste-type/{wasteTypeId}")
    suspend fun wasteType(@PathVariable wasteTypeId: Int) =
        vehiclesService.wasteType(wasteTypeId)

    @GetMapping("/movement-status/{movementStatusId}")
    suspend fun movementStatus(@PathVariable movementStatusId: Int) =
        vehiclesService.movementStatus(movementStatusId)


    @GetMapping("/count")
    suspend fun vehiclesCount() =
        vehiclesService.vehiclesCount()
}