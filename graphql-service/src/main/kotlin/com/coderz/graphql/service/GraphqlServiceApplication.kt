package com.coderz.graphql.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import kotlin.properties.Delegates

@EnableConfigurationProperties(GraphqlServiceProperties::class)
@SpringBootApplication
class GraphqlServiceApplication

fun main(args: Array<String>) {
    runApplication<GraphqlServiceApplication>(*args)
}

@ConfigurationProperties(prefix = "rms.graphql")
class GraphqlServiceProperties {
    lateinit var vehiclesServiceEndpoint: String
    lateinit var alarmsServiceEndpoint: String
    lateinit var companiesServiceEndpoint: String
    lateinit var tripsServiceEndpoint: String
    lateinit var manifestServiceEndpoint: String
    lateinit var redisHost: String
    var redisPort by Delegates.notNull<Int>()
}
