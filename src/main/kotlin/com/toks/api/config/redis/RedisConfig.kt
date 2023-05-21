package com.toks.api.config.redis

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.toks.api.common.extension.isProd
import com.toks.api.common.extension.isStaging
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@EnableCaching
@Configuration
@EnableConfigurationProperties(RedisProperties::class)
class RedisConfig(
    private val environment: Environment,
    private val properties: RedisProperties
) : CachingConfigurerSupport() {
    private val host: String = "localhost"
    private val port: Int = 6379

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        if (environment.isProd() || environment.isStaging()) {
            return LettuceConnectionFactory(properties.host, properties.port)
        }

        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            this.setConnectionFactory(redisConnectionFactory())
            this.keySerializer = StringRedisSerializer()
            this.valueSerializer = GenericJackson2JsonRedisSerializer()
            this.hashKeySerializer = StringRedisSerializer()
            this.hashValueSerializer = GenericJackson2JsonRedisSerializer()
        }
    }

    @Bean
    override fun cacheManager(): CacheManager? {
        val serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(
            GenericJackson2JsonRedisSerializer()
        )

        val defaultConfig = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeValuesWith(serializationPair)
            .entryTtl(Duration.ofSeconds(30L))

        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory())
            .cacheDefaults(defaultConfig)
            .build()
    }

    @Bean
    fun toksCacheManager(): CacheManager? {
        val objectMapper = ObjectMapper()
            .activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
            ).registerModule(
                KotlinModule.Builder()
                    .withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, false)
                    .configure(KotlinFeature.StrictNullChecks, false)
                    .build()
            ).registerModule(JavaTimeModule())

        val serializer = GenericJackson2JsonRedisSerializer(objectMapper)

        val defaultConfig = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
            .entryTtl(caching1Day())

        return RedisCacheManager
            .RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory())
            .cacheDefaults(defaultConfig)
            .build()
    }

    private fun caching1Min(): Duration {
        return Duration.ofSeconds(60L)
    }

    private fun caching1Hour(): Duration {
        return Duration.ofHours(1L)
    }

    private fun caching1Day(): Duration {
        return Duration.ofDays(1L)
    }
}
