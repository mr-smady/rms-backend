package com.coderz.manifest.service

import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

//private val log = KotlinLogging.logger {}

@Service
class ManifestService(
    private val manifestRepository: ManifestRepository,
    private val manifestStatusRepository: ManifestStatusRepository,
    private val wasteProducerRepository: WasteProducerRepository,
) {
    suspend fun manifests(page: Int, size: Int): Flow<Manifest> =
        manifestRepository.findAllBy(PageRequest.of(page, size))

    suspend fun manifestStatus(id: Int): ManifestStatus? =
        manifestStatusRepository.findById(id)

    suspend fun wasteProducer(id: Int): WasteProducer? =
        wasteProducerRepository.findById(id)

    suspend fun manifestCount() =
        manifestRepository.count()

    suspend fun vehicleManifests(vehicleId: Int): Flow<Manifest> =
        manifestRepository.findAllByVehicleId(vehicleId)

}