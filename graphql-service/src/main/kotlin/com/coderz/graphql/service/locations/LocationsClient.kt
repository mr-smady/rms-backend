package com.coderz.graphql.service.locations

import com.coderz.graphql.service.config.Const.Companion.DATE_TIME_FORMATTER
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

@Component
class LocationsClient(
    @Qualifier("locationsServiceClient") private val client: WebClient
) {
    suspend fun locations(
        vehicleId: Int, from: LocalDateTime,
        to: LocalDateTime, page: Int, size: Int
    ): List<Location> =
        client.get()
            .uri(
                "/$vehicleId?from=${from.format(DATE_TIME_FORMATTER)}&to=${to.format(DATE_TIME_FORMATTER)}&page=$page&size=$size"
            )
            .retrieve()
            .awaitBody()

    suspend fun locationRowData(vehicleId: Int): List<Location> =
        client.get()
            .uri(
                "/row-data/$vehicleId"
            )
            .retrieve()
            .awaitBody()

}