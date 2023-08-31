package com.example.airportsystemspringsecurity.Config;

import com.example.airportsystemspringsecurity.Service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder()); // encripring password

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/auth/**").permitAll() // allow any one to reach this endpoint
                .requestMatchers(HttpMethod.GET,"/api/v1/ticket/get-ticket").hasAuthority("PASSENGER")
                .requestMatchers(HttpMethod.POST,"/api/v1/ticket/add-ticket").hasAuthority("PASSENGER")
                .requestMatchers(HttpMethod.PUT,"/api/v1/ticket/update-ticket/{ticket_number}").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/v1/ticket/delete-ticket/{ticket_number}").hasAuthority("PASSENGER")
                .requestMatchers(HttpMethod.PUT,"/api/v1/ticket/{flight_number}/assign/{ticket_number}").hasAuthority("PASSENGER")
                .requestMatchers(HttpMethod.GET,"/api/v1/passenger/**").hasAnyAuthority("ADMIN","PASSENGER")
//                .requestMatchers(HttpMethod.POST,"/api/v1/passenger/add").hasAuthority("PASSENGER")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/passenger/update/{id}").hasAuthority("PASSENGER")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/passenger/delete/{id}").hasAuthority("PASSENGER")
//                .requestMatchers(HttpMethod.GET,"/api/v1/passenger/get-by-id/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/flight/**").hasAuthority("PASSENGER")
                .requestMatchers("/api/v1/plane/**").hasAuthority("ADMIN")
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }

//

}

