package com.wawa.oms.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
public class SecurityConfig { //extends WebSecurityConfigurerAdapter {

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests().antMatchers("/","/static/**","/public/**","/favicon.ico","/dist/**",
                "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                "/configuration/**", "/swagger-ui.html", "/webjars/**",
                "/pub/**", "/api/v1/employee","/api/**").
                permitAll().
                anyRequest()
                .authenticated().and().
                cors().and().
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

          }*/
}
