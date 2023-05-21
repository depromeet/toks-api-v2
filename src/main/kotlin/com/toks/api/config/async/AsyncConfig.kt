package com.toks.api.config.async

import com.toks.api.common.async.ExecutorGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurerSupport
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
@EnableAsync
class AsyncConfig : AsyncConfigurerSupport() {
    @Bean("taskExecutor")
    fun taskExecutor(): ThreadPoolTaskExecutor {
        return ExecutorGenerator(threadName = "taskExecutor").generate()
    }

    @Bean("systemActionLogExecutor")
    fun systemActionLogExecutor(): ThreadPoolTaskExecutor {
        return ExecutorGenerator(threadName = "systemActionLogExecutor").generate()
    }
}
