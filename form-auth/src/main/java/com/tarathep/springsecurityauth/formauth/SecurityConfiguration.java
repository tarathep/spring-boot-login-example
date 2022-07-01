package com.tarathep.springsecurityauth.formauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    //https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
    
    // use form default
    @Bean
    public SecurityFilterChain configurFilterChain(HttpSecurity http) throws Exception {
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
    public InMemoryUserDetailsManager configureInMemoryUserDetailsManager() {

        //password is not work!!
        UserDetails user = User.withUsername("user")
            .password("{bcrypt}$2a$10$DDm1uVxfiZiPmK5amD0ZVu1YdVuLqYHZaw8/NzeuRkZ62/pNLbi/q")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
