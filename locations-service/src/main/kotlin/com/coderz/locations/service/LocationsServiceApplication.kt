package com.coderz.locations.service

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
class LocationsServiceApplication

fun main(args: Array<String>) {
    runApplication<LocationsServiceApplication>(*args)
}

@Repository
interface LocationsRepository : CoroutineCrudRepository<Location, Int> {
    fun findAllByVehicleId(vehicleId: Int, pageable: Pageable): Flow<Location>
}

@Configuration
class WebFluxConfiguration : WebFluxConfigurer {
    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().maxInMemorySize(512 * 1024)
    }
}