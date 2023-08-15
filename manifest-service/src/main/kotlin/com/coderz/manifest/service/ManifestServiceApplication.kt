package com.coderz.manifest.service

import kotlinx.coroutines.flow.Flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.config.WebFluxConfigurer

@SpringBootApplication
class ManifestServiceApplication

fun main(args: Array<String>) {
    runApplication<ManifestServiceApplication>(*args)
}

@Repository
interface ManifestRepository : CoroutineCrudRepository<Manifest, Int> {
    fun findAllBy(pageable: Pageable): Flow<Manifest>
    fun findAllByVehicleId(vehicleId: Int): Flow<Manifest>
}

@Repository
interface ManifestStatusRepository : CoroutineCrudRepository<ManifestStatus, Int>

@Repository
interface WasteProducerRepository : CoroutineCrudRepository<WasteProducer, Int>

@Configuration
class WebFluxConfiguration : WebFluxConfigurer {
    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().maxInMemorySize(512 * 1024)
    }
}