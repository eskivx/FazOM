package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static javax.management.Query.and;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(c -> c.disable())
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/usuario/add").permitAll();
                            authorizeConfig.requestMatchers("/usuario/buscar").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/usuario/alterar").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/usuario/alterar/senha").permitAll();
                            authorizeConfig.requestMatchers("/usuario/excluir/**").hasRole("ADMIN");

                            authorizeConfig.requestMatchers("/projeto/buscar").permitAll();
                            authorizeConfig.requestMatchers("/projeto/add").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/projeto/alterar").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/projeto/excluir").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/projeto/alterardescricao/**").hasRole("ADMIN");

                            authorizeConfig.requestMatchers("/item/buscar").permitAll();
                            authorizeConfig.requestMatchers("/item/add").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/item/alterar/**").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/item/excluir").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/item/status/**").hasRole("ADMIN");

                            authorizeConfig.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();

                            authorizeConfig.anyRequest().authenticated();

                        }
                ).httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}