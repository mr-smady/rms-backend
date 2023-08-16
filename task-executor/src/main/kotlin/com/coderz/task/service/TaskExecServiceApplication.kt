package com.coderz.task.service

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.config.WebFluxConfigurer
import java.io.File
import java.io.PrintWriter

private val log = KotlinLogging.logger {}

@SpringBootApplication
class TaskExecServiceApplication

fun main(args: Array<String>) {
    runApplication<TaskExecServiceApplication>(*args)
}

@Repository
interface PositionRepository : CoroutineCrudRepository<Position, Long> {
    fun findAllByVehicleId(vehicleId: Long, of: PageRequest): Flow<Position>
}

//@Repository
//interface PositionVehicleRepository : CoroutineCrudRepository<PositionVehicle, Long> {
//    //    fun findAllBy(pageable: Pageable): Flow<PositionVehicle>
//    fun findDistinctBy(): Flow<PositionVehicle>
//}

@Configuration
class WebFluxConfiguration : WebFluxConfigurer {
    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().maxInMemorySize(512 * 1024)
    }
}

@Component
class TaskApplicationRunner(
    private val positionRepository: PositionRepository,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) = runBlocking {
        fetchPostions()
    }

    private suspend fun fetchPostions() {
        //PageRequest.of(page, size)
        log.info { "fetchPostions started" }
        val positionVehicles = File("F:/abdulsalam/positionVehicles.txt").bufferedReader().readLines()
        log.info { "positionVehicles finished" }

        File("F:/abdulsalam/positions.txt").printWriter().use { out ->
            positionVehicles.forEach {
                try {
                    saveData(it.toLong(), out)
                }catch (e:Exception){
                    log.error { "ERROR in $it" }
                }

            }
        }
    }

    private suspend fun saveData(vehicleId: Long, out: PrintWriter) {
        log.info { "Start vehicle $vehicleId" }
        val postions = positionRepository.findAllByVehicleId(
            vehicleId,
            PageRequest.of(0, 10000, Sort.by(Position::receivedServerTime.name))
        ).toList()
        var prevLatitude:Double? = 0.0
        var prevLongitude:Double? = 0.0
        postions.forEach {
            if (prevLatitude != it.latitude || prevLongitude != it.longitude){
                out.println(
                    "${it.id},${it.vehicleId},${it.latitude},${it.longitude}," +
                            "${it.speed},${it.ignition},${it.receivedServerTime}"
                )
                prevLatitude = it.latitude
                prevLongitude = it.longitude
            }
        }
        out.flush()
        log.info { "finished vehicle $vehicleId" }
    }

}
