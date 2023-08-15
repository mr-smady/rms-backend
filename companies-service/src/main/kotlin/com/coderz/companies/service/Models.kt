package com.coderz.companies.service

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Company(
    @Id val id: Int?,
    val nameAr: String?,
    val nameEn: String?,
)