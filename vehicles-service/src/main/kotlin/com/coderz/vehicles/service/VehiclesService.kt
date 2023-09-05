package com.coderz.vehicles.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

//private val log = KotlinLogging.logger {}

@Service
class VehiclesService(
    private val vehiclesRepository: VehiclesRepository,
    private val wasteTypeRepository: WasteTypeRepository,
    private val movementStatusRepository: MovementStatusRepository,
    private  val avlLastDataRepository : AvlLastDataRepository
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
    suspend fun findAllAvlLastData(page : Int, size : Int) =
        avlLastDataRepository.findAllBy(PageRequest.of(page, size))

    suspend fun vehicleAvlLastData(plateNumber: String)
    = avlLastDataRepository.findByPlateNumber(plateNumber).toList().firstOrNull()

    suspend fun vehicleByPlateNumber(plateNumber: String)
    = vehiclesRepository.findByPlateNumber(plateNumber).toList().firstOrNull()
}