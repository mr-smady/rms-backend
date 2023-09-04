package com.coderz.graphql.service.vehicles

import com.coderz.graphql.service.alarms.AlarmsService
import com.coderz.graphql.service.manifest.ManifestService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

//private val log = KotlinLogging.logger {}

@Controller
class VehiclesResolver(
    private val vehiclesService: VehiclesService,
    private val vehicleDescriptionService: VehicleDescriptionService,
    private val alarmsService: AlarmsService,
    private val manifestService: ManifestService,
) {

    @QueryMapping
    suspend fun vehiclesCount() =
        vehiclesService.vehiclesCount()

    @QueryMapping
    suspend fun vehicles(@Argument page: Int?, @Argument size: Int?) =
        vehiclesService.vehicles(page ?: 0, size ?: 100)

    @QueryMapping
    suspend fun vehicle(@Argument id: Int) = vehiclesService.vehicle(id)

    @SchemaMapping(typeName = "Vehicle", field = "vehicleDescription")
    suspend fun vehicleDescription(vehicle: Vehicle) =
        vehicleDescriptionService.vehicleDescription(vehicle.vehicleDescriptionId)

    @SchemaMapping(typeName = "Vehicle", field = "alarms")
    suspend fun alarms(vehicle: Vehicle) =
        alarmsService.alarms(vehicle.id)

    @SchemaMapping(typeName = "Vehicle", field = "wasteType")
    suspend fun wasteType(vehicle: Vehicle) =
        vehiclesService.wasteType(vehicle.wasteTypeId)

    @SchemaMapping(typeName = "Vehicle", field = "movementStatus")
    suspend fun movementStatus(vehicle: Vehicle) =
        vehiclesService.movementStatus(vehicle.movementStatusId)

    @SchemaMapping(typeName = "Vehicle", field = "manifests")
    suspend fun manifests(vehicle: Vehicle) =
        manifestService.vehicleManifests(vehicle.id)

  /*  @SchemaMapping(typeName = "Vehicle" , field = "avlLastData")
    suspend fun vehiclesLastUpdate(@Argument page: Int?, @Argument size: Int?){
        vehicleLastDataService.vehiclesLastUpdate(page ?: 0, size ?: 100)
    }*/
    @QueryMapping()
    suspend fun avlLastData(@Argument page: Int?, @Argument size: Int?) =
      vehiclesService.avlLastData(page ?: 0, size ?: 100)

    @QueryMapping()
    suspend fun vehicleAvlLastData(@Argument plateNumber : String) =
        vehiclesService.vehicleAvlLastData(plateNumber)
}