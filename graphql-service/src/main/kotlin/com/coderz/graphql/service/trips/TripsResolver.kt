package com.coderz.graphql.service.trips

import com.coderz.graphql.service.companies.CompaniesService
import com.coderz.graphql.service.vehicles.VehiclesService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class TripsResolver(
    private val tripsService: TripsService,
) {
    @QueryMapping
    suspend fun tripsCount() =
        tripsService.tripsCount()

    @QueryMapping
    suspend fun trips(@Argument vehicleId: Int, @Argument page: Int?, @Argument size: Int?) =
        tripsService.trips(vehicleId,page ?: 0, size ?: 100)
}