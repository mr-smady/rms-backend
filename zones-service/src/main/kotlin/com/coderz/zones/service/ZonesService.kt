package com.coderz.zones.service

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

//private val log = KotlinLogging.logger {}

@Service
class ZonesService(
    private val zonesRepository: ZonesRepository,
    private val zonePointsRepository: ZonePointsRepository ,
    private val zoneTypeRepository: ZoneTypeRepository

) {
    suspend fun zones(zoneTypeId: Int): Flow<Zone> =
        zonesRepository.findAllByZoneTypeId(zoneTypeId)

    suspend fun zone(zoneId: Int) =
        zonesRepository.findById(zoneId)

    suspend fun points(zoneId: Int) =
        zonePointsRepository.findByZoneId(zoneId)

   suspend fun zonesType(): Flow<ZoneType> = zoneTypeRepository.findAll()


}