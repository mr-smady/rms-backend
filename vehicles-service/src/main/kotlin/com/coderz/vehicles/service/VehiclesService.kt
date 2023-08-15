package com.coderz.vehicles.service

import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

//private val log = KotlinLogging.logger {}

@Service
class VehiclesService(
    private val vehiclesRepository: VehiclesRepository,
    private val wasteTypeRepository: WasteTypeRepository,
    private val movementStatusRepository: MovementStatusRepository,
) {
    suspend fun vehicles(page: Int, size: Int): Flow<Vehicle> =
        vehiclesRepository.findAllBy(PageRequest.of(page, size))

    suspend fun vehicle(id: Int) =
        vehiclesRepository.findById(id)

    suspend fun wasteType(id: Int) =
        wasteTypeRepository.findById(id)

    suspend fun vehiclesCount() =
        vehiclesRepository.count()

    suspend fun movementStatus(movementStatusId: Int) =
        movementStatusRepository.findById(movementStatusId)

}