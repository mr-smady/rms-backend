package com.coderz.graphql.service.config

import com.coderz.graphql.service.GraphqlServiceProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@Configuration(proxyBeanMethods = false)
class ServiceClientConfig(
    private val properties: GraphqlServiceProperties,
) {

    private val defaultHeaders: (HttpHeaders) -> Unit = {
        it.accept = listOf(MediaType.APPLICATION_JSON)
        it.contentType = MediaType.APPLICATION_JSON
    }

    @Bean
    fun objectMapper(): ObjectMapper = JsonMapper
        .builder()
        .findAndAddModules()
        .build()

    @Bean("alarmsServiceClient")
    fun alarmsServiceClient(builder: WebClient.Builder): WebClient = builder
        .baseUrl(properties.alarmsServiceEndpoint)
        .defaultHeaders(defaultHeaders)
        .build()

    @Bean("companiesServiceClient")
    fun companiesServiceClient(builder: WebClient.Builder): WebClient = builder
        .baseUrl(properties.companiesServiceEndpoint)
        .defaultHeaders(defaultHeaders)
        .build()


    @Bean("tripsServiceClient")
    fun tripsServiceClient(builder: WebClient.Builder): WebClient = builder
        .baseUrl(properties.tripsServiceEndpoint)
        .defaultHeaders(defaultHeaders)
        .build()

    @Bean("manifestServiceClient")
    fun manifestServiceClient(builder: WebClient.Builder): WebClient = builder
        .baseUrl(properties.manifestServiceEndpoint)
        .defaultHeaders(defaultHeaders)
        .build()


    @Bean("vehiclesServiceClient")
    fun vehiclesServiceClient(builder: WebClient.Builder): WebClient {

//        val sslContext = SslContextBuilder
//            .forClient()
//            .trustManager(InsecureTrustManagerFactory.INSTANCE)
//            .build()
//
//        val httpClient = HttpClient.create()
//            .secure { t -> t.sslContext(sslContext) }

        return builder
//            .clientConnector(ReactorClientHttpConnector(httpClient))
//            .filter(ClientContextFilterFunction())
            .baseUrl(properties.vehiclesServiceEndpoint)
            .defaultHeaders(defaultHeaders)
            .exchangeStrategies(ExchangeStrategies.builder()
                .codecs { it.defaultCodecs().maxInMemorySize(10 * 1024 * 1024) }
                .build())
            .build()
    }

}