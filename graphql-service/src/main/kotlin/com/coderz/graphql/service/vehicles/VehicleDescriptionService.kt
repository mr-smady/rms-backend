package com.coderz.graphql.service.vehicles

import com.coderz.graphql.service.config.getRedisKey
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

@Service
class VehicleDescriptionService(
    private val vehiclesClient: VehiclesClient,
    vehicleDescriptionTemplate: ReactiveRedisTemplate<String, VehicleDescription>
) {
    val vehicleDescriptionOps = vehicleDescriptionTemplate.opsForValue()

    suspend fun vehicleDescription(id: Int?): VehicleDescription? {
        if (id != null) {
            val key = getRedisKey(id, VehicleDescription::class)
            var vehicleDescription = vehicleDescriptionOps.get(key).awaitSingleOrNull()
            if (vehicleDescription == null) {
                vehicleDescription = vehiclesClient.vehicleDescription(id)
                if (vehicleDescription != null) {
                    vehicleDescriptionOps.set(key, vehicleDescription).subscribe()
                }
            }
            return vehicleDescription
        }
        return null
    }

}