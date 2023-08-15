package com.coderz.graphql.service.companies

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class CompaniesClient(
    @Qualifier("companiesServiceClient") private val client: WebClient
) {
    suspend fun companies(): List<Company> =
        client.get()
            .retrieve()
            .awaitBody()

    suspend fun findById(id: Int): Company? =
        client.get()
            .uri("/${id}")
            .retrieve()
            .awaitBody()
}