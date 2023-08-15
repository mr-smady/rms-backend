package com.coderz.companies.service

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

//private val log = KotlinLogging.logger {}

@Service
class CompaniesService(
    private val repository: CompaniesRepository
) {
    suspend fun companies(): Flow<Company> = repository.findAll()
    suspend fun findById(id: Int) = repository.findById(id)
}