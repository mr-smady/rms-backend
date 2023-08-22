package com.coderz.vehicles.service

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Vehicle(
    @Id val id: Int,
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
    val accountId: Int?,
    val isDeleted: Boolean,
    val isActive: Boolean,
    val vehicleDescriptionId: Int?,
    val areaId: Int?,
    val vehiclesAvlProviderId: Int?,
    val projectTypeId: Int?,
    val cleaningProjectId: Int?,
    val updatedDate: LocalDateTime?,
    val licenseNumber: String?,
    val licenseDate: LocalDateTime?,
    val vehicleProjectId: Int?,
    val departmentId: Int?,
    val wasteTypeId: Int?,
    val movementStatusId: Int?,
    val alarmsCount :Int?,
)

data class VehicleDescription(
    @Id val id: Int?,
    val nameAr: String?,
    val nameEn: String?,
)

data class VehicleMovementStatus(
    @Id val id: Int?,
    val nameAr: String?,
    val nameEn: String?,
)

data class WasteType(
    @Id val id: Int?,
    val nameAr: String?,
    val nameEn: String?,
)