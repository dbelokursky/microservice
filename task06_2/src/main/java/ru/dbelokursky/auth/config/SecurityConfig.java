package ru.dbelokursky.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.dbelokursky.auth.security.JwtTokenFilter;
import ru.dbelokursky.auth.security.JwtTokenProvider;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private final static String LOGIN_ENDPOINT = "/api/v1/auth/login";
  private final static String ACTUATOR_ENDPOINT = "/actuator/**";
  private final static String H2_CONSOLE_ENDPOINT = "/h2-console/**";
  private final static String ADMIN_ENDPOINT = "/api/v1/auth/admin";
  private final JwtTokenProvider jwtTokenProvider;
  private final JwtTokenFilter filter;

  @Override
  public void configure(HttpSecurity httpSecurity) {
    JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
    httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests()
        .requestMatchers(LOGIN_ENDPOINT, ACTUATOR_ENDPOINT, H2_CONSOLE_ENDPOINT).permitAll()
        .requestMatchers(ADMIN_ENDPOINT).hasRole("ROLE_ADMIN")
        .anyRequest().authenticated()
        .and()
        .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class).build();
  }
}
