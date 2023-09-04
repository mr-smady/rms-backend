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
data class AvlLastData(
    @Id val id: Int?,
    val imei: String?,
    val movementTime: LocalDateTime?,
    val priority: Boolean?,
    val longitude: Float?,
    val latitude: Float?,
    val altitude : Float?,
    val angle: Float?,
    val speed: Float?,
    val movement: Boolean?,
    val digitalInput1: String?,
    val analogInput1: String?,
    val ignition: Boolean?,
    val distance: Float?,
    val totalDistance: Float?,
    val greenDrivingType: Int?,
    val eventId: Int?,
    val tag: String?,
    val lastTag: String?,
    val wasteCollectionTime: LocalDateTime?,
    val wasteLatitude: Float?,
    val wasteLongitude: Float?,
    val grossWeight: Float?,
    val ed0: Float?,
    val ed1: Float?,
    val input1: Float?,
    val input2: Float?,
    val input3: Float?,
    val input4: Float?,
    val input5: Float?,
    val plateNumber: String?,
    val updateDate: LocalDateTime?
)