package br.com.fourmart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class AppConfig {

    @Bean
    public Clock defaultClock() {
        return Clock.systemDefaultZone();
    }
}
