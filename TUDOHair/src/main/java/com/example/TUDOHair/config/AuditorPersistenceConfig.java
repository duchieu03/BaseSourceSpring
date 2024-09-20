package com.example.TUDOHair.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider", auditorAwareRef = "auditorProvider", modifyOnCreate = false)
public class AuditorPersistenceConfig {
    @Bean(name = "auditorProvider")
    public AuditorAware<String> auditorProvider() {
        return () -> {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (authentication == null) {
//                return Optional.of("ANONYMOUS");
//            } else {
//                try {
//                    User userPrincipal = (User) authentication.getPrincipal();
//                    return Optional.of(userPrincipal.getName());
//                } catch (Exception e) {
//                    return Optional.of("ANONYMOUS");
//                }
//            }
            return Optional.of("ANONYMOUS");
        };
    }

    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(Instant.now());
    }

}
