package com.coderz.vehicles.service

import kotlinx.coroutines.flow.Flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.config.WebFluxConfigurer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@SpringBootApplication
class VehiclesServiceApplication

fun main(args: Array<String>) {
    runApplication<VehiclesServiceApplication>(*args)
}

@Repository
interface VehiclesRepository : CoroutineCrudRepository<Vehicle, Int> {
     fun findAllBy(pageable: Pageable) : Flow<Vehicle>
   fun findByPlateNumber(plateNumber: String): Flow<Vehicle>
}

@Repository
interface VehicleDescriptionRepository : CoroutineCrudRepository<VehicleDescription, Int>

@Repository
interface WasteTypeRepository : CoroutineCrudRepository<WasteType, Int>

@Repository
interface MovementStatusRepository : CoroutineCrudRepository<VehicleMovementStatus, Int>

@Repository
interface  AvlLastDataRepository : CoroutineCrudRepository<AvlLastData , Int>{
    fun findAllBy(pageable: Pageable) : Flow<AvlLastData>
    fun findByPlateNumber (plateNumber : String) : Flow<AvlLastData>
}

@Repository
interface AvlLastUpdatesRepository : CoroutineCrudRepository<AvlData , Int>{
    fun findTop5ByPlateNumberOrderByIdDesc(plateNumber: String ) : Flow<AvlData>
}
@Configuration
class WebFluxConfiguration : WebFluxConfigurer {
    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().maxInMemorySize(512 * 1024)
    }
}