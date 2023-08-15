package com.coderz.alarms.service

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

//private val log = KotlinLogging.logger {}

@Service
class AlarmsService(
    private val alarmsRepository: AlarmsRepository,
    private val zoneTypeRepository: ZoneTypeRepository,
    private val alarmTypeRepository: AlarmTypeRepository,
) {
    suspend fun alarms(): Flow<Alarm> =
        alarmsRepository.findAll()

    suspend fun vehicleAlarms(vehicleId: Int) =
        alarmsRepository.findByVehicleId(vehicleId)

    suspend fun zoneType(zoneTypeId: Int) =
        zoneTypeRepository.findById(zoneTypeId)

    suspend fun alarmType(typeId: Int) =
        alarmTypeRepository.findById(typeId)
}