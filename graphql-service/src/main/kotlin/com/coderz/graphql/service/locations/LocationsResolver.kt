package com.coderz.graphql.service.locations

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.time.LocalDateTime

@Controller
class LocationsResolver(
    private val locationsService: LocationsService,
) {
    @QueryMapping
    suspend fun locations(
        @Argument vehicleId: Int,
        @Argument from: LocalDateTime,
        @Argument to: LocalDateTime,
        @Argument page: Int?,
        @Argument size: Int?) =
        locationsService.locations(vehicleId,from, to,page ?: 0, size ?: 100)


    @QueryMapping
    suspend fun locationRowData(
        @Argument vehicleId: Int,
        ) =  locationsService.locationRowData(vehicleId)
}