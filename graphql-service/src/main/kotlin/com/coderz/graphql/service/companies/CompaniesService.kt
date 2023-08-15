package com.coderz.graphql.service.companies

import org.springframework.stereotype.Service

@Service
class CompaniesService(
    private val companiesClient: CompaniesClient
) {
    suspend fun companies() = companiesClient.companies()
    suspend fun findById(companyId: Int?): Company? {
        if (companyId != null) {
            return companiesClient.findById(companyId)
        }
        return null
    }
}