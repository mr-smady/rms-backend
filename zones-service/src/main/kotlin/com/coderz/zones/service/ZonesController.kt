package com.coderz.zones.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ZonesController(
    private val service: ZonesService
) {

    @GetMapping()
    suspend fun zones(
        @RequestParam zoneTypeId: Int,
    ) = service.zones(zoneTypeId)

    @GetMapping("/{zoneId}")
    suspend fun zone(@PathVariable zoneId: Int) = service.zone(zoneId)

    @GetMapping("/points/{zoneId}")
    suspend fun points(@PathVariable zoneId: Int) = service.points(zoneId)

}