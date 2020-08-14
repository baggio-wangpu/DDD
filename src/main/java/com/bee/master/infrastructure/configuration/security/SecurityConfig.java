package com.bee.master.infrastructure.configuration.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static java.util.Collections.singletonList;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JWTConfigurerAdapter jwtConfigurerAdapter;

  @Override
  protected void configure(HttpSecurity security) throws Exception {
    security.csrf().disable()
      .cors().and()
      .httpBasic().disable()
      .authorizeRequests()
      .antMatchers(
        "/v2/api-docs",
        "/swagger-resources/**",
        "/swagger-ui.html",
        "/webjars/**",
        "/swagger.json")
      .permitAll()
      .antMatchers(HttpMethod.POST, "/tokens", "/users")
      .permitAll()
      .anyRequest().authenticated()
      .and().apply(jwtConfigurerAdapter);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(singletonList("*"));
    configuration.setAllowedMethods(singletonList("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
