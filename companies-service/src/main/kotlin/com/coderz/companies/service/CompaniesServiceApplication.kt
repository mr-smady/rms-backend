package com.coderz.companies.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.config.WebFluxConfigurer

@SpringBootApplication
class CompaniesServiceApplication

fun main(args: Array<String>) {
	runApplication<CompaniesServiceApplication>(*args)
}

@Repository
interface CompaniesRepository : CoroutineCrudRepository<Company, Int>

@Configuration
class WebFluxConfiguration : WebFluxConfigurer {
	override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
		configurer.defaultCodecs().maxInMemorySize(512 * 1024)
	}
}