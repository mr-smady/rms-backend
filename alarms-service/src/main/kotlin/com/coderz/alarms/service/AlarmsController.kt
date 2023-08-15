package com.coderz.alarms.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class AlarmsController(
    private val service: AlarmsService
) {

    @GetMapping
    suspend fun alarms() = service.alarms()

    @GetMapping("/vehicle-alarms/{vehicleId}")
    suspend fun vehicleAlarms(@PathVariable vehicleId: Int) = service.vehicleAlarms(vehicleId)


    @GetMapping("/zone-type/{zoneTypeId}")
    suspend fun zoneType(@PathVariable zoneTypeId: Int) = service.zoneType(zoneTypeId)

    @GetMapping("/alarm-type/{typeId}")
    suspend fun alarmType(@PathVariable typeId: Int) = service.alarmType(typeId)




}