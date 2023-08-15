package com.coderz.graphql.service.config

import com.coderz.graphql.service.GraphqlServiceProperties
import com.coderz.graphql.service.alarms.AlarmType
import com.coderz.graphql.service.alarms.ZoneType
import com.coderz.graphql.service.manifest.ManifestStatus
import com.coderz.graphql.service.manifest.WasteProducer
import com.coderz.graphql.service.vehicles.VehicleDescription
import com.coderz.graphql.service.vehicles.VehicleMovementStatus
import com.coderz.graphql.service.vehicles.WasteType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import kotlin.reflect.KClass


@Configuration(proxyBeanMethods = false)
class RedisConfiguration(
    private val properties: GraphqlServiceProperties,
) {
    @Bean
    fun reactiveRedisConnectionFactory() =
        LettuceConnectionFactory(properties.redisHost, properties.redisPort)

    @Bean
    fun vehicleDescriptionRedisTemplate(
        factory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, VehicleDescription> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer(VehicleDescription::class.java)
        val builder = RedisSerializationContext.newSerializationContext<String, VehicleDescription>(keySerializer)
        val context = builder.value(valueSerializer).build()
        return ReactiveRedisTemplate(factory, context)
    }

    @Bean
    fun wasteTypeRedisTemplate(
        factory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, WasteType> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer(WasteType::class.java)
        val builder = RedisSerializationContext.newSerializationContext<String, WasteType>(keySerializer)
        val context = builder.value(valueSerializer).build()
        return ReactiveRedisTemplate(factory, context)
    }


    @Bean
    fun wasteProducerRedisTemplate(
        factory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, WasteProducer> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer(WasteProducer::class.java)
        val builder = RedisSerializationContext.newSerializationContext<String, WasteProducer>(keySerializer)
        val context = builder.value(valueSerializer).build()
        return ReactiveRedisTemplate(factory, context)
    }


    @Bean
    fun manifestStatusRedisTemplate(
        factory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, ManifestStatus> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer(ManifestStatus::class.java)
        val builder = RedisSerializationContext.newSerializationContext<String, ManifestStatus>(keySerializer)
        val context = builder.value(valueSerializer).build()
        return ReactiveRedisTemplate(factory, context)
    }

    @Bean
    fun vehicleMovementStatusRedisTemplate(
        factory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, VehicleMovementStatus> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer(VehicleMovementStatus::class.java)
        val builder = RedisSerializationContext.newSerializationContext<String, VehicleMovementStatus>(keySerializer)
        val context = builder.value(valueSerializer).build()
        return ReactiveRedisTemplate(factory, context)
    }

    @Bean
    fun zoneTypeStatusRedisTemplate(
        factory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, ZoneType> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer(ZoneType::class.java)
        val builder = RedisSerializationContext.newSerializationContext<String, ZoneType>(keySerializer)
        val context = builder.value(valueSerializer).build()
        return ReactiveRedisTemplate(factory, context)
    }

    @Bean
    fun alarmTypeStatusRedisTemplate(
        factory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, AlarmType> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer(AlarmType::class.java)
        val builder = RedisSerializationContext.newSerializationContext<String, AlarmType>(keySerializer)
        val context = builder.value(valueSerializer).build()
        return ReactiveRedisTemplate(factory, context)
    }

}

fun <T : Any> getRedisKey(id: Int, clazz: KClass<T>): String {
    return getRedisKey("$id", clazz)
}

fun <T : Any> getRedisKey(id: String, clazz: KClass<T>): String {
    return "${id}_" + clazz.qualifiedName
}