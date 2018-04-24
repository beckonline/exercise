package com.n26.exercise;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ConditionalOnProperty(value = "jobs.enabled", havingValue = "true",  matchIfMissing = false)
@EnableScheduling
public class ScheduleConfiguration {
}
