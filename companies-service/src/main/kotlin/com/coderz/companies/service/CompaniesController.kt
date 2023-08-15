package com.coderz.companies.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CompaniesController(
    private val service: CompaniesService
) {

    @GetMapping
    suspend fun companies() = service.companies()

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Int) = service.findById(id)

}