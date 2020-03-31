/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.rent.memovie.security;

import javax.annotation.security.PermitAll;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Recurso para validacion de token y configuracion acceso de rutas
 *
 * @author MARIO MENCIA
 */
@Configuration
@EnableResourceServer
public class ResourceServeConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/").permitAll()
                //                .antMatchers("/movies/**").permitAll() // FIXMEEEE!!
                .antMatchers(HttpMethod.GET, "/movies/all").permitAll()
                .antMatchers(HttpMethod.GET, "/movies/{id}/detail").permitAll()
                .antMatchers(HttpMethod.GET, "/movies/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/movies/admin/{id}/{enable}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/movies/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/movies/admin/{id}/cast").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

}
