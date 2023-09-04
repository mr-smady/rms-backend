package com.coderz.graphql.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import java.util.function.Supplier
import java.util.stream.Stream
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
    lateinit var locationsServiceEndpoint: String
    lateinit var zonesServiceEndpoint: String
    lateinit var redisHost: String
    var redisPort by Delegates.notNull<Int>()
}
