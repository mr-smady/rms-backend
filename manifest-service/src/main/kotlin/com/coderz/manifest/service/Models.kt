package com.coderz.manifest.service

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Manifest(
    @Id val id: Int?,
    val number: Int?,
    val wasteProducerId: Int?,
    val wasteTypeId: Int?,
    val statusId: Int?,
    val vehicleId: Int?,
    val assignmentDate: LocalDateTime?,
    val latitude: Double?,
    val longitude: Double?,
)

data class ManifestStatus(
    @Id val id: Int?,
    val nameAr: String?,
    val nameEn: String?
)

data class WasteProducer(
    @Id val id: Int?,
    val nameAr: String?,
    val nameEn: String?
)