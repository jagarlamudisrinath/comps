package org.comps.config;

import org.comps.auth.AppAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private static String[] staticResources = {"/test"};
    @Autowired private AppAuthSuccessHandler authSuccessHandler;

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
                .and()
                .logout().logoutSuccessUrl("/logout");
    }
}
