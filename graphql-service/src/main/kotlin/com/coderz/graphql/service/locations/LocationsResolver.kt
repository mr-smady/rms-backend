package com.coderz.graphql.service.locations

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class LocationsResolver(
    private val locationsService: LocationsService,
) {
    @QueryMapping
    suspend fun locations(@Argument vehicleId: Int, @Argument page: Int?, @Argument size: Int?) =
        locationsService.locations(vehicleId,page ?: 0, size ?: 100)
}