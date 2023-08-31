package com.coderz.graphql.service.zones

import com.coderz.graphql.service.alarms.AlarmsService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class ZonesResolver(
    private val zonesService: ZonesService,
    private val alarmsService: AlarmsService,
) {

    @QueryMapping
    suspend fun zones(@Argument zoneTypeId: Int) =
        zonesService.zones(zoneTypeId)

    @QueryMapping
    suspend fun zone(@Argument id: Int) =
        zonesService.zone(id)

    @QueryMapping
    suspend fun zonesType() = zonesService.zonesType()


    @SchemaMapping(typeName = "Zone", field = "zoneType")
    suspend fun zoneType(zone: Zone) =
        alarmsService.zoneType(zone.zoneTypeId)

    @SchemaMapping(typeName = "Zone", field = "points")
    suspend fun points(zone: Zone) =
        zonesService.points(zone.id)

    @SchemaMapping(typeName = "ZonesType" , field = "zones" )
    suspend fun categoryZones(zonesType: ZonesType) = zonesService.zones(zonesType.id)







}