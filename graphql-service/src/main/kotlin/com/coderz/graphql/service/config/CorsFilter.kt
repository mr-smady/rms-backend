package com.coderz.graphql.service.config

import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class CorsFilter : WebFilter {

    override fun filter(ctx: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        ctx.response.headers.add("Access-Control-Allow-Origin", "*")
//        ctx.response.headers.add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
        ctx.response.headers.add("Access-Control-Allow-Methods", "*")
//        ctx.response.headers.add("Access-Control-Allow-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range")
        ctx.response.headers.add("Access-Control-Allow-Headers", "*")
        return when (ctx.request.method) {
            HttpMethod.OPTIONS -> {
                ctx.response.headers.add("Access-Control-Max-Age", "1728000")
                ctx.response.statusCode = HttpStatus.NO_CONTENT
                Mono.empty()
            }
            else -> {
                ctx.response.headers.add(
                    "Access-Control-Expose-Headers",
                    "DNT,X-B3-TraceId,X-B3-SpanId,X-B3-Sampled,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range"
                )
                chain.filter(ctx)
            }
        }
    }

}