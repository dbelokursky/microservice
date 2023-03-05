package ru.dbelokursky.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.dbelokursky.auth.security.JwtTokenProvider;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

  private final static String LOGIN_ENDPOINT = "/api/v1/login";

  private final static String ADMIN_ENDPOINT = "/api/v1/admin";

  private final JwtTokenProvider jwtTokenProvider;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests()
        .requestMatchers(LOGIN_ENDPOINT).permitAll()
        .requestMatchers(ADMIN_ENDPOINT).hasRole("ROLE_ADMIN")
        .anyRequest().authenticated()
        .and()
        .apply(new JwtConfig(jwtTokenProvider));

    return httpSecurity.build();
  }
}
