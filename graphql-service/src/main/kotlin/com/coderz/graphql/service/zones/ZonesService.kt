package com.coderz.graphql.service.zones

import org.springframework.stereotype.Service

@Service
class ZonesService(
    private val zonesClient: ZonesClient
) {
    suspend fun zones(typeId: Int) =
        zonesClient.zones(typeId)

    suspend fun zone(zoneId: Int) =
        zonesClient.zone(zoneId)

    suspend fun points(zoneId: Int?) = if (zoneId != null)
        zonesClient.points(zoneId)
    else null

   suspend fun zonesType() = zonesClient.zonesType()


}