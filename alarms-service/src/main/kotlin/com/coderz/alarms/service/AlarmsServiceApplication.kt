package com.coderz.alarms.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.config.WebFluxConfigurer
import kotlinx.coroutines.flow.Flow

@SpringBootApplication
class AlarmsServiceApplication

fun main(args: Array<String>) {
    runApplication<AlarmsServiceApplication>(*args)
}

@Repository
interface AlarmsRepository : CoroutineCrudRepository<Alarm, Int> {
    fun findByVehicleId(vehicleId: Int): Flow<Alarm>
}

@Repository
interface ZoneTypeRepository : CoroutineCrudRepository<ZoneType, Int>

@Repository
interface AlarmTypeRepository : CoroutineCrudRepository<AlarmType, Int>

@Configuration
class WebFluxConfiguration : WebFluxConfigurer {
    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().maxInMemorySize(512 * 1024)
    }
}