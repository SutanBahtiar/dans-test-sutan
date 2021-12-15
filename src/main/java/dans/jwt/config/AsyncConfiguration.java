package dans.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration {

    private final AsyncConfigurationProperties asyncConfigurationProperties;

    @Autowired
    public AsyncConfiguration(AsyncConfigurationProperties configurationProperties) {
        this.asyncConfigurationProperties = configurationProperties;
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncConfigurationProperties.getCorePoolSize());
        executor.setMaxPoolSize(asyncConfigurationProperties.getMaxPoolSize());
        executor.setQueueCapacity(asyncConfigurationProperties.getQueueCapacity());
        executor.setThreadNamePrefix(asyncConfigurationProperties.getThreadNamePrefix());
        executor.initialize();
        return executor;
    }
}
