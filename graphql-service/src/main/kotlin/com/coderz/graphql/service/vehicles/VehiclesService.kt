package com.coderz.graphql.service.vehicles

import com.coderz.graphql.service.config.getRedisKey
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

@Service
class VehiclesService(
    private val vehiclesClient: VehiclesClient,
    wasteTypeTemplate: ReactiveRedisTemplate<String, WasteType>,
    movementStatusTemplate: ReactiveRedisTemplate<String, VehicleMovementStatus>
) {
    val wasteTypeOps = wasteTypeTemplate.opsForValue()
    val movementStatusOps = movementStatusTemplate.opsForValue()

    suspend fun vehicles(page: Int, size: Int) =
        vehiclesClient.vehicles(page, size)

    suspend fun avlLastData(page: Int, size: Int) : List<AvlLastData> {
        return vehiclesClient.avlLastData(page, size)
    }

    suspend fun findById(vehicleId: Int?): Vehicle? {
        if (vehicleId != null) {
            return vehiclesClient.vehicle(vehicleId)
        }
        return null
    }

    suspend fun vehicle(id: Int?) =
        if (id == null) null else vehiclesClient.vehicle(id)

    suspend fun wasteType(wasteTypeId: Int?): WasteType? {
        if (wasteTypeId != null) {
            val key = getRedisKey(wasteTypeId, WasteType::class)
            var wasteType = wasteTypeOps.get(key).awaitSingleOrNull()
            if (wasteType == null) {
                wasteType = vehiclesClient.wasteType(wasteTypeId)
                if (wasteType != null) {
                    wasteTypeOps.set(key, wasteType).subscribe()
                }
            }
            return wasteType
        }
        return null
    }

    suspend fun vehiclesCount() =
        vehiclesClient.vehiclesCount()

    suspend fun movementStatus(movementStatusId: Int?): VehicleMovementStatus? {
        if (movementStatusId != null) {
            val key = getRedisKey(movementStatusId, VehicleMovementStatus::class)
            var movementStatus = movementStatusOps.get(key).awaitSingleOrNull()
            if (movementStatus == null) {
                movementStatus = vehiclesClient.movementStatus(movementStatusId)
                if (movementStatus != null) {
                    movementStatusOps.set(key, movementStatus).subscribe()
                }
            }
            return movementStatus
        }
        return null
    }

}