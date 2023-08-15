package com.coderz.graphql.service.config

import graphql.GraphQLContext
import graphql.execution.CoercedVariables
import graphql.language.StringValue
import graphql.language.Value
import graphql.scalars.ExtendedScalars
import graphql.schema.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer
import java.time.LocalDateTime
import java.util.*

@Configuration
class GraphQlConfig {



    @Bean
    fun runtimeWiringConfigurer() =
        RuntimeWiringConfigurer {
            it.scalar(ExtendedScalars.GraphQLLong)
                .scalar(
                    GraphQLScalarType.newScalar()
                        .name("LocalDateTime")
                        .description("LocalDateTime as scalar")
                        .coercing(LocalDateTimeCoercing())
                        .build()
                )
                .build()
        }

}

internal class LocalDateTimeCoercing : Coercing<LocalDateTime, String> {
    override fun serialize(
        dataFetcherResult: Any,
        graphQLContext: GraphQLContext,
        locale: Locale
    ) = runCatching {
        dataFetcherResult.toString()
    }.getOrElse {
        throw CoercingSerializeException("Data fetcher result $dataFetcherResult cannot be serialized to a String")
    }

    override fun parseValue(
        input: Any,
        graphQLContext: GraphQLContext,
        locale: Locale
    ): LocalDateTime? = runCatching {
        LocalDateTime.parse(
            serialize(
                input,
                graphQLContext,
                locale,
            )
        )
    }.getOrElse {
        throw CoercingParseValueException("Expected valid LocalDateTime but was $input")
    }

    override fun parseLiteral(
        input: Value<*>,
        variables: CoercedVariables,
        graphQLContext: GraphQLContext,
        locale: Locale
    ): LocalDateTime? {
        val localDateTimeString = (input as? StringValue)?.value
        return runCatching {
            LocalDateTime.parse(localDateTimeString)
        }.getOrElse {
            throw CoercingParseLiteralException("Expected valid LocalDateTime literal but was $localDateTimeString")
        }
    }

}