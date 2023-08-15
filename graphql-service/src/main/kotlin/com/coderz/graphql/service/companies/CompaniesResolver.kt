package com.coderz.graphql.service.companies

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class CompaniesResolver(private val companiesService: CompaniesService) {

    @QueryMapping
    suspend fun companies() = companiesService.companies()
}