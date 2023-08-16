package com.coderz.zones.service

import kotlinx.coroutines.flow.Flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.config.WebFluxConfigurer

@SpringBootApplication
class ZonesServiceApplication

fun main(args: Array<String>) {
    runApplication<ZonesServiceApplication>(*args)
}

@Repository
interface ZonesRepository : CoroutineCrudRepository<Zone, Int> {
    fun findAllByZoneTypeId(zoneTypeId: Int): Flow<Zone>
}

@Repository
interface ZonePointsRepository : CoroutineCrudRepository<ZonePoint, Int> {
    fun findAllByZoneId(zoneId: Int): Flow<ZonePoint>
}

@Configuration
class WebFluxConfiguration : WebFluxConfigurer {
    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().maxInMemorySize(512 * 1024)
    }
}