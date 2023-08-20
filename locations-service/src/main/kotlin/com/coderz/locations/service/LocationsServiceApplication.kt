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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootApplication
class LocationsServiceApplication

fun main(args: Array<String>) {
    runApplication<LocationsServiceApplication>(*args)
}

class Const {
    companion object {
        val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    }
}

@Repository
interface LocationsRepository : CoroutineCrudRepository<Location, Int> {
    fun findAllByVehicleIdAndReceivedServerTimeBetween(
        vehicleId: Int,
        from: LocalDateTime,
        toLocalDateTime: LocalDateTime,
        pageable: Pageable,
    ): Flow<Location>

    fun findByVehicleId(vehicleId: Int, pageable: Pageable): Flow<Location>
}

@Configuration
class WebFluxConfiguration : WebFluxConfigurer {
    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().maxInMemorySize(512 * 1024)
    }
}