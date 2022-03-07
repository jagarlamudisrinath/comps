package org.comps.config;

import org.comps.auth.AppAuthFailureHandler;
import org.comps.auth.AppAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private static String[] staticResources = {"/ws", "/chat", "/**/websocket", "/assignments/**/file"};
    @Autowired private AppAuthSuccessHandler authSuccessHandler;
    @Autowired private AppAuthFailureHandler authFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(staticResources).permitAll()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin()
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and().cors(Customizer.withDefaults())
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor(getRestAuthenticationEntryPoint(), new AntPathRequestMatcher("/**"));
    }

    private AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("GET","POST", "HEAD", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setExposedHeaders(List.of("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
