package com.coderz.task.service

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.time.LocalTime

@Table("Position")
data class Position(
    @Id
    @Column("Id")
    val id: Long?,
    @Column("VehicleId")
    val vehicleId: Long?,
    @Column("Latitude")
    val latitude: Double?,
    @Column("Longitude")
    val longitude: Double?,
    @Column("Speed")
    val speed: Double?,
    @Column("Ignition")
    val ignition: Boolean?,
    @Column("ReceivedServerTime")
    val receivedServerTime: LocalDateTime?,
)

@Table("Position")
data class PositionVehicle(
    @Column("VehicleId")
    val vehicleId: Long,
)