package com.coderz.graphql.service.manifest

import com.coderz.graphql.service.config.getRedisKey
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

@Service
class ManifestService(
    private val manifestClient: ManifestClient,
    wasteProducerTemplate: ReactiveRedisTemplate<String, WasteProducer>,
    manifestStatusTemplate:ReactiveRedisTemplate<String, ManifestStatus>,

) {
    val wasteProducerOps = wasteProducerTemplate.opsForValue()
    val manifestStatusOps = manifestStatusTemplate.opsForValue()

    suspend fun manifests(page: Int, size: Int) =
        manifestClient.manifests(page, size)

    suspend fun wasteProducer(wasteProducerId: Int?): WasteProducer? {
        if (wasteProducerId != null) {
            val key = getRedisKey(wasteProducerId, WasteProducer::class)
            var wasteProducer = wasteProducerOps.get(key).awaitSingleOrNull()
            if (wasteProducer == null) {
                wasteProducer = manifestClient.wasteProducer(wasteProducerId)
                if (wasteProducer != null) {
                    wasteProducerOps.set(key, wasteProducer).subscribe()
                }
            }
            return wasteProducer
        }
        return null
    }

    suspend fun manifestStatus(statusId: Int?): ManifestStatus? {
        if (statusId != null) {
            val key = getRedisKey(statusId, ManifestStatus::class)
            var manifestStatus = manifestStatusOps.get(key).awaitSingleOrNull()
            if (manifestStatus == null) {
                manifestStatus = manifestClient.manifestStatus(statusId)
                if (manifestStatus != null) {
                    manifestStatusOps.set(key, manifestStatus).subscribe()
                }
            }
            return manifestStatus
        }
        return null
    }

    suspend fun manifestsCount() =
        manifestClient.manifestsCount()

    suspend fun vehicleManifests(vehicleId: Int) =
        manifestClient.vehicleManifests(vehicleId)
}