package com.coderz.graphql.service.alarms

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitBodyOrNull

@Component
class AlarmsClient(
    @Qualifier("alarmsServiceClient") private val client: WebClient
) {
    suspend fun alarms(): List<Alarm> =
        client.get()
            .retrieve()
            .awaitBody()

    suspend fun alarms(vehicleId: Int): List<Alarm> =
        client.get()
            .uri("/vehicle-alarms/${vehicleId}")
            .retrieve()
            .awaitBody()

   suspend fun zoneType(zoneTypeId: Int) :ZoneType? =
       client.get()
           .uri("/zone-type/${zoneTypeId}")
           .retrieve()
           .awaitBodyOrNull()

    suspend fun alarmType(typeId: Int): AlarmType =
        client.get()
            .uri("/alarm-type/${typeId}")
            .retrieve()
            .awaitBody()
}