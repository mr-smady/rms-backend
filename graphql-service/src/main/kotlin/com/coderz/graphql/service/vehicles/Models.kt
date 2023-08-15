package com.coderz.graphql.service.vehicles

import java.time.LocalDateTime

data class Vehicle(
    val id: Int,
    val lastLatitude: Float?,
    val lastLongitude: Float?,
    val lastSpeed: Float?,
    val lastPositionTime: LocalDateTime?,
    val lastTimeUpdate: LocalDateTime?,
    val lastCourse: Float?,
    val plateNumber: String?,
    val yearOfMade: String?,
    val vehicleBrand: String?,
    val imei: String?,
    val vehicleModel: String?,
    val accountId: Long?,
    val isDeleted: Boolean,
    val isActive: Boolean,
    val vehicleDescriptionId: Int?,
    val areaId: Long?,
    val vehiclesAvlProviderId: Long?,
    val projectTypeId: Int?,
    val cleaningProjectId: Int?,
    val updatedDate: LocalDateTime?,
    val licenseNumber: String?,
    val licenseDate: LocalDateTime?,
    val vehicleProjectId: Int?,
    val departmentId: Int?,
    val wasteTypeId: Int?,
    val movementStatusId: Int?,
)

data class VehicleDescription(
    val id: Int? = null,
    val nameAr: String? = null,
    val nameEn: String? = null,
)

data class WasteType(
    val id: Int? = null,
    val nameAr: String? = null,
    val nameEn: String? = null,
)

data class VehicleMovementStatus(
    val id: Int? = null,
    val nameAr: String? = null,
    val nameEn: String? = null,
)