package ru.dbelokursky.auth.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = {"ru.dbelokursky.auth.client"})
@Configuration
public class FeignConfig {
}
