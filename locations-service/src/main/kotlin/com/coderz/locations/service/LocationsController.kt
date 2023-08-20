package com.coderz.locations.service

import com.coderz.locations.service.Const.Companion.DATE_TIME_FORMATTER
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class LocationsController(
    private val locationsService: LocationsService,
) {
    @GetMapping("/{vehicleId}")
    suspend fun locations(
        @PathVariable vehicleId: Int,
        @RequestParam from: String,
        @RequestParam to: String,
        @RequestParam(
            required = false,
            defaultValue = "0"
        ) page: Int,
        @RequestParam(
            required = false,
            defaultValue = "100"
        ) size: Int,

        ): Flow<Location> {
        println(from)
        return locationsService.locations(
            vehicleId,
            LocalDateTime.parse(from, DATE_TIME_FORMATTER),
            LocalDateTime.parse(to, DATE_TIME_FORMATTER),
            page,
            size
        )
    }

    @GetMapping("/row-data/{vehicleId}")
    suspend fun locations(
        @PathVariable vehicleId: Int,
    ): Flow<Location> {
        return locationsService.rowData(
            vehicleId,
        )
    }

}