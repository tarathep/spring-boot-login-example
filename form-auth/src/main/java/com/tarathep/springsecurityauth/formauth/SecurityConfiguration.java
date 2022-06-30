package com.tarathep.springsecurityauth.formauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    
    // use form default
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> {
                try {
                    authz
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .and()
                        .httpBasic();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            );
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager configure() {
        
        UserDetails user = User.withUsername("user")
            .password("{bcrypt}$2a$10$DDm1uVxfiZiPmK5amD0ZVu1YdVuLqYHZaw8/NzeuRkZ62/pNLbi/q")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
