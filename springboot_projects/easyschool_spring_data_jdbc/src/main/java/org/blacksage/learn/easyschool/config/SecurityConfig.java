package org.blacksage.learn.easyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

        @Bean
        SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

                // Enable csrf.
                // Ignore csrf on /saveMsg because it's a public form.
                http.csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer
                                .ignoringRequestMatchers("/saveMsg")
                );

                // All routes except dashboard are public (don't require login).
                http.authorizeHttpRequests(
                                (requests) ->
                                        requests
                                                .requestMatchers("/dashboard").authenticated()
                                                .requestMatchers("/displayMessages", "/closeMsg/**").hasRole("ADMIN")
                                                .requestMatchers(
                                                        "/", "/home",
                                                        "/courses",
                                                        "/about",
                                                        "/holidays/**",
                                                        "/contact",
                                                        "/assets/**",
                                                        "/saveMsg",
                                                        "/login",
                                                        "/logout"
                                                )
                                                .permitAll()
                        )
                        .formLogin(
                                formLoginCustomizer ->
                                        formLoginCustomizer
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/dashboard")
                                                .failureUrl("/login?error=true")
                                                .permitAll()
                        )
                        .httpBasic(withDefaults());

                return http.build();
        }

        @Bean
        public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
                UserDetails user = User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build();

                UserDetails admin = User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("USER", "ADMIN")
                        .build();

                return new InMemoryUserDetailsManager(user, admin);
        }
}
