package com.wawa.oms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class SpringAsyncConfig {


    @Value("${executor-pool-size}")
    int executorPoolSize;
    @Value("${executor-queue-size}")
    int executorQueueSize;
    @Value("${executor-core-size}")
    int executorCorePoolSize;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(executorCorePoolSize);
        executor.setMaxPoolSize(executorPoolSize);
        executor.setQueueCapacity(executorQueueSize);
        executor.setThreadNamePrefix("SPA-");
        executor.initialize();
        return executor;
    }

}
