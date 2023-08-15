package com.coderz.graphql.service.alarms

import com.coderz.graphql.service.companies.CompaniesService
import com.coderz.graphql.service.vehicles.VehiclesService
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class AlarmsResolver(
    private val alarmsService: AlarmsService,
    private val vehiclesService: VehiclesService,
    private val companiesService: CompaniesService,
) {

    @QueryMapping
    suspend fun alarms() = alarmsService.alarms()

    @SchemaMapping(typeName = "Alarm", field = "vehicle")
    suspend fun vehicle(alarm: Alarm) = vehiclesService.findById(alarm.vehicleId)

    @SchemaMapping(typeName = "Alarm", field = "company")
    suspend fun company(alarm: Alarm) = companiesService.findById(alarm.companyId)

    @SchemaMapping(typeName = "Alarm", field = "zoneType")
    suspend fun zoneType(alarm: Alarm) = alarmsService.zoneType(alarm.zoneTypeId)

    @SchemaMapping(typeName = "Alarm", field = "alarmType")
    suspend fun alarmType(alarm: Alarm) = alarmsService.alarmType(alarm.typeId)
}