package com.coderz.graphql.service.alarms

import com.coderz.graphql.service.config.getRedisKey
import com.coderz.graphql.service.vehicles.VehicleMovementStatus
import com.coderz.graphql.service.vehicles.WasteType
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

@Service
class AlarmsService(
    private val alarmsClient: AlarmsClient,
    zoneTypeTemplate: ReactiveRedisTemplate<String, ZoneType>,
    alarmTypeTemplate: ReactiveRedisTemplate<String, AlarmType>

) {

    val zoneTypeOps = zoneTypeTemplate.opsForValue()
    val alarmTypeOps = alarmTypeTemplate.opsForValue()

    suspend fun alarms() = alarmsClient.alarms()
    suspend fun alarms(vehicleId: Int) = alarmsClient.alarms(vehicleId)

    suspend fun zoneType(zoneTypeId: Int?) :ZoneType?{
        if (zoneTypeId != null) {
            val key = getRedisKey(zoneTypeId, ZoneType::class)
            var zoneType = zoneTypeOps.get(key).awaitSingleOrNull()
            if (zoneType == null) {
                zoneType = alarmsClient.zoneType(zoneTypeId)
                if (zoneType != null) {
                    zoneTypeOps.set(key, zoneType).subscribe()
                }
            }
            return zoneType
        }
        return null
    }

    suspend fun alarmType(typeId: Int?) :AlarmType? {
        if (typeId != null) {
            val key = getRedisKey(typeId, AlarmType::class)
            var alarmType = alarmTypeOps.get(key).awaitSingleOrNull()
            if (alarmType == null) {
                alarmType = alarmsClient.alarmType(typeId)
                if (alarmType != null) {
                    alarmTypeOps.set(key, alarmType).subscribe()
                }
            }
            return alarmType
        }
        return null
    }
}