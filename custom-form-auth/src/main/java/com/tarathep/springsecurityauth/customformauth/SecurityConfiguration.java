package com.tarathep.springsecurityauth.customformauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain configureFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> {
                try {
                    authz
                    .antMatchers("/public/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                    .loginPage("/login.html")
                    .failureUrl("/login-error.html")
                    .permitAll();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            );
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager configureInMemoryUserDetailsManager() {
        // PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // // outputs {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
        // // remember the password that is printed out and use in the next step
        // System.out.println("==============> "+encoder.encode("password"));

        UserDetails user = User.withUsername("user")
            .password("{bcrypt}$2a$10$DDm1uVxfiZiPmK5amD0ZVu1YdVuLqYHZaw8/NzeuRkZ62/pNLbi/q")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
